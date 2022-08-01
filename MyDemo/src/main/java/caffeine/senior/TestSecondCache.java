package caffeine.senior;

import com.github.benmanes.caffeine.cache.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 测试二级缓存+淘汰机制原理
 * @author: frank.wu
 * @create: 2021-11-17
 */
public class TestSecondCache {

    /**
     * 充当二级缓存用，生命周期仅活到下个gc
     */
    private static Map<Integer, WeakReference<Integer>> secondCacheMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // 设置最大缓存个数为1
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(1)
                // 设置put和remove的回调
                .writer(new CacheWriter<Integer, Integer>() {
                    @Override
                    public void write(@NonNull Integer key, @NonNull Integer value) {
                        secondCacheMap.put(key, new WeakReference<>(value));
                        System.out.println("触发CacheWriter.write，将key = " + key + "放入二级缓存中");
                    }

                    @Override
                    public void delete(@NonNull Integer key, @Nullable Integer value, @NonNull RemovalCause cause) {
                        switch (cause) {
                            case EXPLICIT:
                                secondCacheMap.remove(key);
                                System.out.println("触发CacheWriter" + ".delete，清除原因：主动清除，将key = " + key + "从二级缓存清除");
                                break;
                            case SIZE:
                                System.out.println("触发CacheWriter" + ".delete，清除原因：缓存个数超过上限，key = " + key);
                                break;
                            default:
                                break;
                        }
                    }
                }).build(new CacheLoader<Integer, Integer>() {
                    @Nullable
                    @Override
                    public Integer load(@NonNull Integer key) {
                        WeakReference<Integer> value = secondCacheMap.get(key);
                        if (value == null) {
                            return null;
                        }
                        System.out.println("触发CacheLoader.load，从二级缓存读取key = " + key);
                        return value.get();
                    }
                });
        cache.put(1, 1);
        cache.put(2, 2);
        // 由于清除缓存是异步的，因而睡眠1秒等待清除完成
        Thread.sleep(1000);
        // 缓存超上限触发清除后
        System.out.println("从Caffeine中get数据，key为1，value为" + cache.get(1));
    }
}

