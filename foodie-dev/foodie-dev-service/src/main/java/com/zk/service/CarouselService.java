package com.zk.service;

import com.zk.pojo.Carousel;

import java.util.List;

/**
 * @author CoderZk
 */
public interface CarouselService {
    /**
     * 查询所有轮播图列表
     *
     * @param isShow
     */
    List<Carousel> queryAll(Integer isShow);
}
