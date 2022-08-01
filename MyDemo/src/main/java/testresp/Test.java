package testresp;


import com.alibaba.fastjson.JSON;

/**
 * @description: sss
 * @author: frank.wu
 * @create: 2021-11-05
 */
public class Test {

    public static void main(String[] args) {
        User user = new User(2,"s",3);
        String s = JSON.toJSONString(user);
        User user1 = JSON.parseObject(s, User.class);
        System.out.println(user1);
    }
}

