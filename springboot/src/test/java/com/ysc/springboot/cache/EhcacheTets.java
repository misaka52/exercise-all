package com.ysc.springboot.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.impl.config.persistence.CacheManagerPersistenceConfiguration;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanshancheng
 * @date 2021/4/18
 */
public class EhcacheTets {
    private static final int ONE_MB = 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {
//        ehcacheNumber();
        ehcacheSize();
    }

    // 磁盘缓存
    static void disk() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
                .defaultPool("default", 1, 10).build())
                .with(new CacheManagerPersistenceConfiguration(new File("./bak")))
                .build(true);
        CacheConfigurationBuilder<String, Byte[]> cacheConfigurationBuilder = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class,
                        Byte[].class,
                        // 设置缓存条目数量，按照LRU回收
                        ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(2, MemoryUnit.MB))
                .withDiskStoreThreadPool("default", 5)
                .withDispatcherConcurrency(4)
                // 设置TTL
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(100, TimeUnit.SECONDS)))
                .withSizeOfMaxObjectGraph(3)
                .withSizeOfMaxObjectSize(1, MemoryUnit.KB);

    }

    // 堆外缓存
    static void ehcacheSize() throws InterruptedException {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .build(true);
        CacheConfigurationBuilder<String, Byte[]> cacheConfigurationBuilder = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class,
                Byte[].class,
                        // 设置缓存条目数量，按照LRU回收
                        ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(2, MemoryUnit.MB))
                .withDispatcherConcurrency(4)
                // 设置TTL
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(100, TimeUnit.SECONDS)))
                // 设置TTI
                .withExpiry(Expirations.timeToIdleExpiration(Duration.of(3, TimeUnit.SECONDS)))
                .withSizeOfMaxObjectGraph(3)
                .withSizeOfMaxObjectSize(1, MemoryUnit.KB);

        Cache<String, Byte[]> cache = cacheManager.createCache("mycache", cacheConfigurationBuilder);
        cache.put("1", new Byte[ONE_MB]);
        printCache(cache);
        cache.put("2", new Byte[ONE_MB]);
        printCache(cache);
    }

    // 堆内缓存
    static void ehcacheNumber() throws InterruptedException {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .build(true);
        CacheConfigurationBuilder<String, String> cacheConfigurationBuilder = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class,
                        String.class,
                        // 设置缓存条目数量，按照LRU回收
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2, EntryUnit.ENTRIES))
                .withDispatcherConcurrency(4)
                // 设置TTL
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(10, TimeUnit.SECONDS)))
                // 设置TTI
                .withExpiry(Expirations.timeToIdleExpiration(Duration.of(3, TimeUnit.SECONDS)));

        Cache<String, String> cache = cacheManager.createCache("mycache", cacheConfigurationBuilder);
        cache.put("1","1");
        cache.put("2","1");
        printCache(cache);
        cache.put("3","1");
        printCache(cache);
        System.out.println(cache.get("1"));
        System.out.println(cache.get("2"));
        cache.remove("1");
        cache.remove("2");
        printCache(cache);
        Thread.sleep(3000);
        printCache(cache);
    }

    static void printCache(Cache<?, ?> cache) {
        System.out.println("----print cache start---");
        for (Cache.Entry<?, ?> entry : cache) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("----print cache end---");
    }
}
