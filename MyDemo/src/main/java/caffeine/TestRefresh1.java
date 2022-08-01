package caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @description: 测试刷新
 * @author: frank.wu
 * @create: 2021-10-11
 */
public class TestRefresh1 {

    private static Cache cache = Caffeine.newBuilder()
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build(k -> DataObject.get("Data for " + k));
    public static void main(String[] args) {

    }
}
