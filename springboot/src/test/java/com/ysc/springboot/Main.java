package com.ysc.springboot;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.ysc.springboot.datastruct.LRUCache;
import com.ysc.springboot.datastruct.LRUCache2;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanshancheng
 * @date 2021/2/22
 */
public class Main {
    public static void main(String[] args) {
       lruTest();
    }

    static void lruTest() {
        LRUCache<Integer, String> cache = new LRUCache2<>(3);
        cache.set(1, "a");
        cache.set(2, "b");
        cache.set(3, "c");
        cache.print();
        cache.get(1);
        cache.print();
        cache.set(2, "bb");
        cache.print();

        cache.set(4, "d");
        cache.print();
    }

    static void cache() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(120, TimeUnit.SECONDS)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .concurrencyLevel(4)
                .build();
        System.out.println("123");
    }

    static class Solution {
        public double myPow(double x, int n) {
            if(x == 0) return 0;
            long b = n;
            double res = 1.0;
            if(b < 0) {
                x = 1 / x;
                b = -b;
            }
            while(b > 0) {
                if((b & 1) == 1) res *= x;
                x *= x;
                b >>= 1;
            }
            return res;
        }
    }

    public static List<List<Integer>> all(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        allDetail(arr, 0, res);
        System.out.println(res);
        return res;
    }

    public static void allDetail(int[] arr, int index, List<List<Integer>> res) {
        if (index >= arr.length - 1) {
            List<Integer> list = new ArrayList<>(arr.length);
            for (int value : arr) {
                // init
                list.add(value);
            }
            res.add(list);
            return;
        }
        for (int i = index; i < arr.length; ++i) {
            swap(arr, index, i);
            allDetail(arr, index + 1, res);
            swap(arr, index, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

}
