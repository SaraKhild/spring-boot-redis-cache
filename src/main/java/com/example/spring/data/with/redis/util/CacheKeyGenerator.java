package com.example.spring.data.with.redis.util;

public abstract class CacheKeyGenerator {

    public static String generateKey(final Object... params) {
        String key = "PRODUCT_";
        for (final Object o : params) {
            if (o != null)
                key += o.toString() + "_";
        }

        return key;
    }

}
