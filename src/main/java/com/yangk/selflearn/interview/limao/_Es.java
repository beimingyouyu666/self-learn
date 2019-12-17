package com.yangk.selflearn.interview.limao;

public class _Es {
    /**
     *1.es的分布式架构原理能说一下么（es是如何实现分布式的啊）？
     *2.es写入数据的工作原理是什么啊？es查询数据的工作原理是什么啊？
     *3.es在数据量很大的情况下（数十亿级别）如何提高查询性能啊？
     *4.es生产集群的部署架构是什么？每个索引的数据量大概有多少？每个索引大概有多少个分片？
     *
     *
     *2.es写入数据的工作原理是什么啊？es查询数据的工作原理是什么啊？
     （1）es写数据过程

     1）客户端选择一个node发送请求过去，这个node就是coordinating node（协调节点）
     2）coordinating node，对document进行路由，将请求转发给对应的node（有primary shard）
     3）实际的node上的primary shard处理请求，然后将数据同步到replica node
     4）coordinating node，如果发现primary node和所有replica node都搞定之后，就返回响应结果给客户端

     （2）es读数据过程

     查询，GET某一条数据，写入了某个document，这个document会自动给你分配一个全局唯一的id，doc id，
     同时也是根据doc id进行hash路由到对应的primary shard上面去。也可以手动指定doc id，比如用订单id，用户id。

     你可以通过doc id来查询，会根据doc id进行hash，判断出来当时把doc id分配到了哪个shard上面去，从那个shard去查询

     1）客户端发送请求到任意一个node，成为coordinate node
     2）coordinate node对document进行路由，将请求转发到对应的node，此时会使用round-robin随机轮询算法，
     在primary shard以及其所有replica中随机选择一个，让读请求负载均衡
     3）接收请求的node返回document给coordinate node
     4）coordinate node返回document给客户端
     *
     *
     *
     *3.es在数据量很大的情况下（数十亿级别）如何提高查询性能啊？
     *（1）性能优化的杀手锏——filesystem cache
     * os cache，操作系统的缓存
     * 你往es里写的数据，实际上都写到磁盘文件里去了，磁盘文件里的数据操作系统会自动将里面的数据缓存到os cache里面去
     * es的搜索引擎严重依赖于底层的filesystem cache，你如果给filesystem cache更多的内存，
     * 尽量让内存可以容纳所有的indx segment file索引数据文件，那么你搜索的时候就基本都是走内存的，性能会非常高。
     *（2）数据预热
     * 对于那些你觉得比较热的，经常会有人访问的数据，最好做一个专门的缓存预热子系统，就是对热数据，每隔一段时间，
     * 你就提前访问一下，让数据进入filesystem cache里面去。这样期待下次别人访问的时候，一定性能会好一些。
     *（3）冷热分离
     * 关于es性能优化，数据拆分，我之前说将大量不搜索的字段，拆分到别的存储中去，这个就是类似于后面我最后要讲的mysql分库分表的垂直拆分。
     * es可以做类似于mysql的水平拆分，就是说将大量的访问很少，频率很低的数据，单独写一个索引，然后将访问很频繁的热数据单独写一个索引
     * 你最好是将冷数据写入一个索引中，然后热数据写入另外一个索引中，这样可以确保热数据在被预热之后，
     * 尽量都让他们留在filesystem os cache里，别让冷数据给冲刷掉。
     *（4）document模型设计
     *将mysql中多表关联关系查询出来的 放到一个document中，这也是最常用的
     *（5）分页性能优化
     * 1）不允许深度分页/默认深度分页性能很惨
     * 你系统不允许他翻那么深的页，pm，默认翻的越深，性能就越差
     * 2）类似于app里的推荐商品不断下拉出来一页一页的
     * 类似于微博中，下拉刷微博，刷出来一页一页的，你可以用scroll api，自己百度
     * scroll会一次性给你生成所有数据的一个快照，然后每次翻页就是通过游标移动，获取下一页下一页这样子，性能会比上面说的那种分页性能也高很多很多
     *
     * 针对这个问题，你可以考虑用scroll来进行处理，scroll的原理实际上是保留一个数据快照，然后在一定时间内，你如果不断的滑动往后翻页的时候，
     * 类似于你现在在浏览微博，不断往下刷新翻页。那么就用scroll不断通过游标获取下一页数据，这个性能是很高的，比es实际翻页要好的多的多。
     *
     * 但是唯一的一点就是，这个适合于那种类似微博下拉翻页的，不能随意跳到任何一页的场景。同时这个scroll是要保留一段时间内的数据快照的，
     * 你需要确保用户不会持续不断翻页翻几个小时。
     *
     * 无论翻多少页，性能基本上都是毫秒级的
     * 因为scroll api是只能一页一页往后翻的，是不能说，先进入第10页，然后去120页，回到58页，不能随意乱跳页。
     * 所以现在很多产品，都是不允许你随意翻页的，app，也有一些网站，做的就是你只能往下拉，一页一页的翻
     *
     *
     *
     *
     */


}