package com.zk.mgallery.dao;

import com.zk.mgallery.entity.Painting;
import com.zk.mgallery.utils.PageModel;
import com.zk.mgallery.utils.XmlDataSource;

import java.util.List;

/**
 * 油画数据访问对象
 */
public class PaintingDao {
    /**
     * 分页查询油画数据
     * @param page 页号
     * @param rows 每页记录数
     * @return PageModel 分页对象
     */
    public PageModel pagination(int page, int rows) {
        // Painting油画对象集合
        List<Painting> list = XmlDataSource.getRawData();
        // PageModel分页处理得到分页数据及分页附加
        PageModel pageModel = new PageModel(list, page, rows);
        return pageModel;
    }


    public void append() {

    }

    public void update() {

    }

    public void delete() {

    }

    public void findAll() {

    }
}
