package caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description: 测试使用：基本使用 同步加载 异步加载(completableFuture)
 * @author: frank.wu
 * @create: 2021-10-09
 */
public class TestCaffeine1 {

    /** 手动填充 */
    private static final Cache<String, DataObject> cache = Caffeine.newBuilder()
//            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(100)
            .build();

    /** 同步加载 */
    private static LoadingCache<String, DataObject> cache1 = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(k -> DataObject.get("Data for " +System.currentTimeMillis()+ k));

    /** 异步加载(completableFuture) */
    private static AsyncLoadingCache<String, DataObject> cache2 = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .buildAsync(k -> DataObject.get("Data for " + k));



    public static void main(String[] args)  {
        LocalDateTime stayEndTime = LocalDateTime.now();
        System.out.println(JSON.toJSONString(stayEndTime));
        DataObject dataObject = new DataObject("s");
        cache.put("A",dataObject);
        System.out.println(JSON.toJSONString(dataObject));
//        String s = "abcdefahijk";
//        System.out.println(s.length());
//        System.out.println(s.indexOf("m"));
//        System.out.println(s.substring(s.indexOf("k")+1));
//        if(s.length())
//        doit();
//        String key = "A";
//        cache.put(key, new DataObject("1"));
//        DataObject dataObject = cache.getIfPresent(key);
//        System.out.println(dataObject);
////        TimeUnit.SECONDS.sleep(10);
//        System.out.println(cache.getIfPresent(key));
//        TimeUnit.SECONDS.sleep(2);
//        cache.invalidate(key);
//        cache.invalidateAll();
//        DataObject dataObject = cache.getIfPresent(key);
//        DataObject a = cache.get(key, k -> DataObject.get("A"));
//        Assert.notNull(dataObject,"not bull");
    }

    public static void doit(){
        try{
            return;
        }catch (Exception e){

        }finally {
            System.out.println("finally");
        }
    }

    public static void main1(String[] args) throws InterruptedException {
        String key = "A";
        DataObject dataObject = cache1.get(key);
        System.out.println(dataObject);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(cache1.get(key));
        TimeUnit.SECONDS.sleep(10);
        System.out.println(cache1.get(key));
//        DataObject dataObject = cache1.get(key);
//        cache1.put("A",new DataObject());
//        cache1.cleanUp();
//        Map<String, DataObject> dataObjectMap
//                = cache1.getAll(Arrays.asList("A", "B", "C"));
//        System.out.println();
    }

//    public static void main2(String[] args) {
//        String key = "A";
//        CompletableFuture<DataObject> dataObjectCompletableFuture = cache2.get(key);
//        dataObjectCompletableFuture.thenAccept(dataObject -> {
//            System.out.println(dataObject.getData());
//        });
//        cache2.getAll(Arrays.asList("A", "B", "C"))
//                .thenAccept(dataObjectMap -> System.out.println(dataObjectMap.size()));
//        System.out.println();
//    }
}
