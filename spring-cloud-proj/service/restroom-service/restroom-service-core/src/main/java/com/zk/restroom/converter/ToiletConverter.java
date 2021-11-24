package com.zk.restroom.converter;

import com.zk.restroom.entity.ToiletEntity;
import com.zk.restroom.pojo.Toilet;

/**
 * @author CoderZk
 */
public class ToiletConverter {
    public static Toilet convert(ToiletEntity entity) {
        return Toilet.builder()
                .id(entity.getId())
                .clean(entity.isClean())
                .available(entity.isAvailable())
                .build();
    }
}
