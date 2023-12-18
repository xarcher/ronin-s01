package ronin.s01.hw02;

import com.github.benmanes.caffeine.cache.Cache;

import java.util.concurrent.TimeUnit;

public class MainApplication {
    public static void main(String[] args) throws InterruptedException {
        // create cache manager for AirPortModel with ttl = 10s
        Cache<String, AirPortModel> cacheManager = CacheManager.createCacheManager(10, 10000L);
        var airPortService = new AirPortService(cacheManager);


        System.out.println(airPortService.getByCode("HN1"));

        TimeUnit.SECONDS.sleep(5);
        System.out.println(airPortService.getByCode("HN1"));

        TimeUnit.SECONDS.sleep(5);
        System.out.println(airPortService.getByCode("HN1"));
    }
}
