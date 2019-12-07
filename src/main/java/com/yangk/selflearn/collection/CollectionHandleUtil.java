package com.yangk.selflearn.collection;

import com.alibaba.fastjson.JSONObject;
import com.yangk.selflearn.base.dos.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description 关于集合使用流的一些操作
 * @Author yangkun
 * @Date 2019/11/29
 * @Version 1.0
 * @blame yangkun
 */
public class CollectionHandleUtil {

    /**
     * @Description 排序
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [list]
     * @Return
     **/
    public static List sort(List<User> list) {
//        list.sort(Comparator.comparing(UserDTO::getAge));
        return list.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
    }

    /**
     * @Description 从original集合中移除所有在集合中remove中的元素，返回新集合
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [original, remove]
     * @Return
     **/
    public static <T> List<T> remove(Collection<T> original,Collection<T> remove) {
        return findAll(original, e -> (remove == null) || !remove.contains(e));
    }

    /**
     * @Description 对集合所有元素操作
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [collection, consumer]
     * @Return
     **/
    public static <T> void foreach(Collection<T> collection, Consumer<T> consumer) {
        collection.forEach(consumer);
    }

    /**
     * @Description 从流中找到符合条件的首个元素
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [collection, predicate]
     * @Return
     **/
    public static <T> T findFirst(Collection<T> collection,Predicate<T> predicate) {
        return (T) collection.stream().filter(predicate).findFirst().orElse(null);
    }

    /**
     * @Description 检查集合中是否有符合条件的元素
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [collection, predicate]
     * @Return
     **/
    public static <T> boolean exit(Collection<T> collection,Predicate<T> predicate) {
        return findFirst(collection,predicate) != null;
    }

    /**
     * @Description 根据条件去重
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [collection, function]
     * @Return
     **/
    public static <T,R> List distinct(Collection<T> collection, Function<T,R> function) {
        final Set<R> set = new HashSet<>();
        return findAll(collection,t-> set.add(function.apply((T) t)));

    }

    /**
     * @Description 根据条件查询符合条件的所有数据
     * @Author yangkun
     * @Date 2019/11/29
     * @Param [collection, predicate]
     * @Return
     **/
    public static <T> List findAll(Collection<T> collection, Predicate<T> predicate) {
        return collection == null ? null : (List) collection.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1,1,"11"),
                new User(2,2,"22"),
                new User(1,3,"33"),
                new User(4,4,"4")

        );
        List userDis = distinct(users, user -> user.getId());
        System.out.println("distinct-----------------------------------");
        System.out.println(JSONObject.toJSONString(userDis));

        User first = findFirst(users, user -> user.getId() == 1);
        System.out.println("findFirst-----------------------------------");
        System.out.println(JSONObject.toJSONString(first));

        List all = findAll(users, user -> user.getId() == 1);
        System.out.println("findAll-----------------------------------");
        System.out.println(JSONObject.toJSONString(all));

        boolean exit = exit(users, user -> user.getId() == 1);
        System.out.println("exit-----------------------------------");
        System.out.println(JSONObject.toJSONString(exit));

        foreach(users, user->user.setSex(66));
        System.out.println("foreach-----------------------------------");
        System.out.println(JSONObject.toJSONString(users));

        User user = new User(4, 4, "4");
        user.setSex(66);
        List<User> remove = Arrays.asList(user);
        List<User> result = remove(users, remove);
        System.out.println("remove-----------------------------------");
        System.out.println(JSONObject.toJSONString(result));

        List sort = sort(users);
        System.out.println("sort before-----------------------------------");
        System.out.println(JSONObject.toJSONString(users));
        System.out.println("sort after-----------------------------------");
        System.out.println(JSONObject.toJSONString(sort));
    }

}
