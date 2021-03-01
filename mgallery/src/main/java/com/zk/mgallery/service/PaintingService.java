package com.zk.mgallery.service;

import com.zk.mgallery.dao.PaintingDao;
import com.zk.mgallery.entity.Painting;
import com.zk.mgallery.utils.PageModel;

import java.util.List;

/**
 * PaintingService油画服务类
 */
public class PaintingService {
    private PaintingDao paintingDao = new PaintingDao();

    /**
     * 数据分页查询
     *
     * @param page     页号
     * @param rows     每页记录数
     * @param category 可选参数, 分类编号
     * @return 分页对象
     */
    public PageModel pagination(int page, int rows, String... category) {
        if (rows == 0) {
            throw new RuntimeException("无效的rows参数");
        }
        if (category.length == 0 || category[0] == null) {
            return paintingDao.pagination(page, rows);
        } else {
            return paintingDao.pagination(Integer.parseInt(category[0]), page, rows);
        }
    }

    public void create(Painting painting) {
        paintingDao.create(painting);
    }

    public static void main(String[] args) {
        PaintingService paintingService = new PaintingService();

        PageModel pageModel = paintingService.pagination(2, 6);

        List<Painting> paintingList = pageModel.getPageData();
        for (Painting painting : paintingList) {
            System.out.println(painting.getPname());
        }
        System.out.println(pageModel.getPageStartRow() + ":" + pageModel.getPageEndRow());
    }
}
