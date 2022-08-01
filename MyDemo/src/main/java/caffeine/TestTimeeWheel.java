package caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-02-08
 */
public class TestTimeeWheel {
    /** 手动填充 */
    private static final LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(100)
//            .expireAfter(new Expiry<String, DataObject>() {
//
//                @Override
//                public long expireAfterCreate(@Nonnull String key, @Nonnull DataObject value, long currentTime) {
//                    return 1073741824;
//                }
//
//                @Override
//                public long expireAfterUpdate(@Nonnull String key, @Nonnull DataObject value, long currentTime, long currentDuration) {
//                    return currentDuration;
//                }
//
//                @Override
//                public long expireAfterRead(@Nonnull String key, @Nonnull DataObject value, long currentTime, long currentDuration) {
//                    return currentDuration;
//                }
//            })
            .build(k -> DataObject.get("Data for " + k));

    public static void main(String[] args) {
        LocalDateTime stayEndTime = LocalDateTime.now();
        System.out.println(JSON.toJSONString(stayEndTime));
        DataObject dataObject = new DataObject("s");
        cache.get("K");
        cache.cleanUp();
        cache.get("K");
//        cache.put("A", dataObject);
        System.out.println(JSON.toJSONString(dataObject));
    }
}
