//package caffeine;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.Expiry;
//import com.github.benmanes.caffeine.cache.LoadingCache;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @link {https://juejin.cn/post/6844903506223038477}
// * @description: 测试回收
// * @author: frank.wu
// * @create: 2021-10-11
// */
//public class TestInvalidate1 {
//
//    /** 基于大小回收 */
//    private static LoadingCache<String, DataObject> cache0 = Caffeine.newBuilder()
//            .maximumSize(1)
//            .build(k -> DataObject.get("Data for " + k));
//
//    /** 基于大小回收-weight */
//    private static LoadingCache<String, DataObject> cache1 = Caffeine.newBuilder()
//            .maximumWeight(10)
//            .weigher((k,v) -> 5)
//            .build(k -> DataObject.get("Data for " + k));
//
//    /** 基于时间回收-访达后 */
//    private static LoadingCache<String, DataObject> cache2 = Caffeine.newBuilder()
//            .expireAfterAccess(5, TimeUnit.MINUTES)
//            .build(k -> DataObject.get("Data for " + k));
//
//    /** 基于时间回收-写入后到期 */
//    private static LoadingCache<String, DataObject> cache3 = Caffeine.newBuilder()
//            .expireAfterWrite(10, TimeUnit.SECONDS)
//            .weakKeys()
//            .weakValues()
//            .build(k -> DataObject.get("Data for " + k));
//
//    /** 基于时间回收-自定义策略 */
//    private static LoadingCache<String, DataObject> cache4 =
//            Caffeine.newBuilder().expireAfter(new Expiry<String, DataObject>() {
//                @Override
//                public long expireAfterCreate(
//                        String key, DataObject value, long currentTime) {
//                    return value.getData().length() * 1000;
//                }
//                @Override
//                public long expireAfterUpdate(
//                        String key, DataObject value, long currentTime, long currentDuration) {
//                    return currentDuration;
//                }
//                @Override
//                public long expireAfterRead(
//                        String key, DataObject value, long currentTime, long currentDuration) {
//                    return currentDuration;
//                }
//            }).build(k -> DataObject.get("Data for " + k));
//
//
//    /** 基于引用回收-弱引用 */
//    private static LoadingCache<String, DataObject> cache5 = Caffeine.newBuilder()
//            .expireAfterWrite(10, TimeUnit.SECONDS)
//            .weakKeys()
//            .weakValues()
//            .build(k -> DataObject.get("Data for " + k));
//
//    public static void main0(String[] args) {
//        System.out.println(cache0.estimatedSize());
//        cache0.get("A");
//        System.out.println(cache0.estimatedSize());
//        cache0.get("B");
//        // 因为回收是异步执行的，在调用size前，先调用cleanUp来执行回收
//        cache0.cleanUp();
//        System.out.println(cache0.estimatedSize());
//    }
//
//    public static void main1(String[] args) {
//        // 理解：每个值的权重为5？
//        cache1.get("A");
//        System.out.println(cache1.estimatedSize());
//
//        cache1.cleanUp();
//        cache1.get("B");
//        System.out.println(cache1.estimatedSize());
//
//        cache1.get("C");
//        cache1.cleanUp();
//        System.out.println(cache1.estimatedSize());
//    }
//
//
//}
