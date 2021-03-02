package com.zk.mgallery.dao;

import com.zk.mgallery.entity.Painting;
import com.zk.mgallery.utils.PageModel;
import com.zk.mgallery.utils.XmlDataSource;

import java.util.ArrayList;
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

    /**
     * 按类别分页查询
     * @param category 分类编号
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public PageModel pagination(int category, int page, int rows) {
        List<Painting> list = XmlDataSource.getRawData();
        List<Painting> categoryList = new ArrayList<>();
        for (Painting p : list) {
            if (p.getCategory() == category) {
                categoryList.add(p);
            }
        }
        PageModel pageModel = new PageModel(categoryList, page, rows);
        return pageModel;
    }

    /**
     * 数据新增
     */
    public void create(Painting painting) {
        XmlDataSource.append(painting);
    }

    public Painting findById(Integer id) {
        List<Painting> data = XmlDataSource.getRawData();
        Painting painting = null;
        for (Painting p : data) {
            if (p.getId().equals(id)) {
                painting = p;
                break;
            }
        }
        return painting;
    }
}
