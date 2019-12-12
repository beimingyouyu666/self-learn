package com.yangk.selflearn.interview.limao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class _Redis2 {
    /**
     *
     *1.所以你要结合这俩场景考虑一下，你为啥要用缓存？
     *2.用了缓存之后会有啥不良的后果？
     *3.来聊聊redis的线程模型吧？为啥单线程还能有很高的效率？
     *4.redis和memcached有什么区别？
     *5.为什么单线程的redis比多线程的memcached效率要高得多（为什么redis是单线程的但是还可以支撑高并发）？
     *6.redis都有哪些数据类型？分别在哪些场景下使用比较合适？
     *7.redis的过期策略都有哪些？
     *8.内存淘汰机制都有哪些？手写一下LRU代码实现？
     *
     *
     *1.所以你要结合这俩场景考虑一下，你为啥要用缓存？
     * 高性能 高并发
     *2.用了缓存之后会有啥不良的后果？
     * 1）缓存与数据库双写不一致
     * 2）缓存雪崩
     * 3）缓存穿透
     * 4）缓存并发竞争
     *
     *
     *3.来聊聊redis的线程模型吧？为啥单线程还能有很高的效率？
     *1）文件事件处理器
     * redis基于reactor模式开发了网络事件处理器，这个处理器叫做文件事件处理器，file event handler。
     * 这个文件事件处理器，是单线程的，redis才叫做单线程的模型，采用IO多路复用机制同时监听多个socket，
     * 根据socket上的事件来选择对应的事件处理器来处理这个事件。
     *
     * 如果被监听的socket准备好执行accept、read、write、close等操作的时候，跟操作对应的文件事件就会产生，
     * 这个时候文件事件处理器就会调用之前关联好的事件处理器来处理这个事件。
     *
     * 文件事件处理器是单线程模式运行的，但是通过IO多路复用机制监听多个socket，可以实现高性能的网络通信模型，
     * 又可以跟内部其他单线程的模块进行对接，保证了redis内部的线程模型的简单性。
     *
     * 文件事件处理器的结构包含4个部分：多个socket，IO多路复用程序，文件事件分派器，
     * 事件处理器（命令请求处理器、命令回复处理器、连接应答处理器，等等）。
     *
     * 多个socket可能并发的产生不同的操作，每个操作对应不同的文件事件，但是IO多路复用程序会监听多个socket，但是会将socket放入一个队列中排队，每次从队列中取出一个socket给事件分派器，事件分派器把socket给对应的事件处理器。
     *
     * 然后一个socket的事件处理完之后，IO多路复用程序才会将队列中的下一个socket给事件分派器。文件事件分派器会根据每个socket当前产生的事件，来选择对应的事件处理器来处理。
     *
     * 2）文件事件
     *
     * 当socket变得可读时（比如客户端对redis执行write操作，或者close操作），或者有新的可以应答的sccket出现时（客户端对redis执行connect操作），socket就会产生一个AE_READABLE事件。
     *
     * 当socket变得可写的时候（客户端对redis执行read操作），socket会产生一个AE_WRITABLE事件。
     *
     * IO多路复用程序可以同时监听AE_REABLE和AE_WRITABLE两种事件，要是一个socket同时产生了AE_READABLE和AE_WRITABLE两种事件，那么文件事件分派器优先处理AE_REABLE事件，然后才是AE_WRITABLE事件。
     *
     * 3）文件事件处理器
     *
     * 如果是客户端要连接redis，那么会为socket关联连接应答处理器
     * 如果是客户端要写数据到redis，那么会为socket关联命令请求处理器
     * 如果是客户端要从redis读数据，那么会为socket关联命令回复处理器
     *
     * 4）客户端与redis通信的一次流程
     *
     * 在redis启动初始化的时候，redis会将连接应答处理器跟AE_READABLE事件关联起来，
     * 接着如果一个客户端跟redis发起连接，此时会产生一个AE_READABLE事件，
     * 然后由连接应答处理器来处理跟客户端建立连接，创建客户端对应的socket，
     * 同时将这个socket的AE_READABLE事件跟命令请求处理器关联起来。
     *
     * 当客户端向redis发起请求的时候（不管是读请求还是写请求，都一样），首先就会在socket产生一个AE_READABLE事件，
     * 然后由对应的命令请求处理器来处理。这个命令请求处理器就会从socket中读取请求相关数据，然后进行执行和处理。
     *
     * 接着redis这边准备好了给客户端的响应数据之后，就会将socket的AE_WRITABLE事件跟命令回复处理器关联起来，
     * 当客户端这边准备好读取响应数据时，就会在socket上产生一个AE_WRITABLE事件，会由对应的命令回复处理器来处理，
     * 就是将准备好的响应数据写入socket，供客户端来读取。
     *
     * 命令回复处理器写完之后，就会删除这个socket的AE_WRITABLE事件和命令回复处理器的关联关系。
     *
     *
     *
     *
     *4.redis和memcached有什么区别？
     *（1）redis和memcached有啥区别
     *
     * 这个事儿吧，你可以比较出N多个区别来，但是我还是采取redis作者给出的几个比较吧
     *
     * 1）Redis支持服务器端的数据操作：Redis相比Memcached来说，拥有更多的数据结构和并支持更丰富的数据操作，
     * 通常在Memcached里，你需要将数据拿到客户端来进行类似的修改再set回去。这大大增加了网络IO的次数和数据体积。
     * 在Redis中，这些复杂的操作通常和一般的GET/SET一样高效。所以，如果需要缓存能够支持更复杂的结构和操作，
     * 那么Redis会是不错的选择。
     * 2）内存使用效率对比：使用简单的key-value存储的话，Memcached的内存利用率更高，
     * 而如果Redis采用hash结构来做key-value存储，由于其组合式的压缩，其内存利用率会高于Memcached。
     * 3）性能对比：由于Redis只使用单核，而Memcached可以使用多核，
     * 所以平均每一个核上Redis在存储小数据时比Memcached性能更高。而在100k以上的数据中，
     * Memcached性能要高于Redis，虽然Redis最近也在存储大数据的性能上进行优化，但是比起Memcached，还是稍有逊色。
     * 4）集群模式：memcached没有原生的集群模式，需要依靠客户端来实现往集群中分片写入数据；
     * 但是redis目前是原生支持cluster模式的，redis官方就是支持redis cluster集群模式的，比memcached来说要更好
     *
     *
     *
     * 5.为什么单线程的redis比多线程的memcached效率要高得多（为什么redis是单线程的但是还可以支撑高并发）？
     *
     *1）纯内存操作
     *2）核心是基于非阻塞的IO多路复用机制
     *3）单线程反而避免了多线程的频繁上下文切换问题（百度）
     *
     * 6.redis都有哪些数据类型？分别在哪些场景下使用比较合适？
     *（1）String
     *（2）Hash  这个是类似map的一种结构，这个一般就是可以将结构化的数据，
     * 比如一个对象（前提是这个对象没嵌套其他的对象）给缓存在redis里，然后每次读写缓存的时候，可以就操作hash里的某个字段。
     *（3）list
     * 有序列表，这个是可以玩儿出很多花样的
     *
     * 微博，某个大v的粉丝，就可以以list的格式放在redis里去缓存
     *
     * key=某大v
     *
     * value=[zhangsan, lisi, wangwu]
     *
     * 比如可以通过list存储一些列表型的数据结构，类似粉丝列表了、文章的评论列表了之类的东西
     *
     * 比如可以通过lrange命令，就是从某个元素开始读取多少个元素，可以基于list实现分页查询，这个很棒的一个功能，
     * 基于redis实现简单的高性能分页，可以做类似微博那种下拉不断分页的东西，性能高，就一页一页走
     *
     * 比如可以搞个简单的消息队列，从list头怼进去，从list尾巴那里弄出来
     *
     *（4）set
     *
     * 无序集合，自动去重
     *
     * 直接基于set将系统里需要去重的数据扔进去，自动就给去重了，如果你需要对一些数据进行快速的全局去重，
     * 你当然也可以基于jvm内存里的HashSet进行去重，但是如果你的某个系统部署在多台机器上呢？
     *
     * 得基于redis进行全局的set去重
     *
     * 可以基于set玩儿交集、并集、差集的操作，比如交集吧，可以把两个人的粉丝列表整一个交集，看看俩人的共同好友是谁？对吧
     * 把两个大v的粉丝都放在两个set中，对两个set做交集
     *
     *（5）sorted set
     *
     * 排序的set，去重但是可以排序，写进去的时候给一个分数，自动根据分数排序，这个可以玩儿很多的花样，最大的特点是有个分数可以自定义排序规则
     *
     * 比如说你要是想根据时间对数据排序，那么可以写入进去的时候用某个时间作为分数，人家自动给你按照时间排序了
     *
     * 排行榜：将每个用户以及其对应的什么分数写入进去，zadd board score username，接着zrevrange board 0 99，就可以获取排名前100的用户；
     * zrank board username，可以看到用户在排行榜里的排名
     *
     * 7.redis的过期策略都有哪些？
     * 定期删除+惰性删除
     *
     *所谓定期删除，指的是redis默认是每隔100ms就随机抽取一些设置了过期时间的key，检查其是否过期，如果过期就删除。
     * 假设redis里放了10万个key，都设置了过期时间，你每隔几百毫秒，就检查10万个key，那redis基本上就死了，cpu负载会很高的，
     * 消耗在你的检查过期key上了。注意，这里可不是每隔100ms就遍历所有的设置过期时间的key，那样就是一场性能上的灾难。
     * 实际上redis是每隔100ms随机抽取一些key来检查和删除的。
     *
     * 但是问题是，定期删除可能会导致很多过期key到了时间并没有被删除掉，那咋整呢？所以就是惰性删除了。
     * 这就是说，在你获取某个key的时候，redis会检查一下 ，这个key如果设置了过期时间那么是否过期了？
     * 如果过期了此时就会删除，不会给你返回任何东西。
     *
     * 并不是key到时间就被删除掉，而是你查询这个key的时候，redis再懒惰的检查一下
     *
     * 通过上述两种手段结合起来，保证过期的key一定会被干掉。
     *
     * 很简单，就是说，你的过期key，靠定期删除没有被删除掉，还停留在内存里，占用着你的内存呢，除非你的系统去查一下那个key，才会被redis给删除掉。
     *
     * 但是实际上这还是有问题的，如果定期删除漏掉了很多过期key，然后你也没及时去查，也就没走惰性删除，此时会怎么样？
     * 如果大量过期key堆积在内存里，导致redis内存块耗尽了，咋整？
     *
     * 答案是：走内存淘汰机制。
     *
     *8.内存淘汰机制都有哪些？手写一下LRU代码实现？
     * 1）noeviction：当内存不足以容纳新写入数据时，新写入操作会报错，这个一般没人用吧，实在是太恶心了
     * 2）allkeys-lru：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的key（这个是最常用的）
     * 3）allkeys-random：当内存不足以容纳新写入数据时，在键空间中，随机移除某个key，这个一般没人用吧，为啥要随机，肯定是把最近最少使用的key给干掉啊
     * 4）volatile-lru：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，移除最近最少使用的key（这个一般不太合适）
     * 5）volatile-random：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，随机移除某个key
     * 6）volatile-ttl：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，有更早过期时间的key优先移除
     *
     *
     *
     *
     *
     *
     */

    public static void main(String[] args) {
        LinkedHashMap list = new LinkedHashMap(2);
        list.put(1,1);
        list.put(2,2);
        list.put(3,3);
        list.put(4,4);
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);
        lruCache.put(5,5);

        Object o = lruCache.get(3);
        System.out.println("=========" + o);
        lruCache.put(6,6);

        System.out.println("=========" + o);

    }
}
