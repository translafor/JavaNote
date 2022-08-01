package mqtt;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: w
 * @author: frank.wu
 * @create: 2021-09-09
 */
public class Test {


    public static Class<?> getClasss(){
        return Test11.class;
    }

    public static void s(){
        Class<?> classs = getClasss();
        Test1 test1 = new Test1();
        test1.setS("ss");

        Object cast = classs.cast(test1);
        System.out.println();
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(9);
        list.add(2);
        list.sort(
                (processor1, processor2) -> {
                    return Integer.compare(processor1, processor2);
                });


        BigDecimal bigDecimal1 = new BigDecimal("122.12314561232").multiply(new BigDecimal(1000000)).setScale(0, BigDecimal.ROUND_HALF_UP);

        BigDecimal bigDecimal0 = new BigDecimal("122.123456789");
        BigDecimal bigDecimal2 = bigDecimal0.multiply(new BigDecimal(1000000));
        bigDecimal2 = bigDecimal2.setScale(0,BigDecimal.ROUND_HALF_UP);

        BigDecimal.ZERO.toBigInteger();
        System.out.println(Long.MAX_VALUE);
        BigDecimal bigDecimal = new BigDecimal("3333");
        System.out.println(Long.valueOf(bigDecimal.toPlainString()));
        System.out.println(String.join("_","wo","ni","ta"));
//        s();
//        List<Test1> list1 = new ArrayList<>();
        Test1 test1 = new Test1();
        test1.setId(1111L);
        test1.setS("eee");
//        list1.add(test1);

//        List<Test2> list2 = (List<Test2>) JSON.parse(JSON.toJSONString(list1));
//        Test2 test2 = new Test2();
        Test2 test2 = JSON.parseObject(JSON.toJSONString(test1),Test2.class);
//        BeanUtils.copyProperties(list1,list2);
        System.out.println();
    }
}
