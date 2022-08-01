package caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentMap;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-02-09
 */
public class TestTinyLFU {

    private static final LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
//            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(3)
            .build(k -> DataObject.get("Data for " + k));

    public static void main(String[] args) {
        LocalDateTime stayEndTime = LocalDateTime.now();
        System.out.println(cache.get("A").getData());
        cache.cleanUp();

        cache.get("B");
        cache.cleanUp();

        cache.get("C");
        cache.cleanUp();
        cache.get("C");
        cache.cleanUp();

        cache.get("A");
        cache.cleanUp();

        cache.get("D");
        cache.cleanUp();
//
        cache.get("K");
        cache.cleanUp();

        ConcurrentMap<String, DataObject> stringDataObjectConcurrentMap = cache.asMap();
        System.out.println();
    }
}
