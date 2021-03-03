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

    /**
     * 按编号查询油画
     * @param id 油画编号
     * @return 油画对象
     */
    public Painting findById(Integer id) {
        Painting p = paintingDao.findById(id);
        if (p == null) {
            throw new RuntimeException("[id=" + id + "]油画不存在");
        }
        return p;
    }

    /**
     * 更新业务逻辑
     * @param newPainting 新的油画数据
     * @param isPreviewModified 是否修改Preview属性
     */
    public void update(Painting newPainting, Integer isPreviewModified) {
        // 在原始数据基础上覆盖更新!!!
        Painting oldPainting = findById(newPainting.getId());  // 通过findById从全局唯一的data里找到painting对象
        oldPainting.setPname(newPainting.getPname());
        oldPainting.setCategory(newPainting.getCategory());
        oldPainting.setPrice(newPainting.getPrice());
        oldPainting.setDescription(newPainting.getDescription());
        if (isPreviewModified == 1) {
            oldPainting.setPreview(newPainting.getPreview());
        }
        paintingDao.update(oldPainting);
    }

    public void delete(int id) {
        paintingDao.delete(id);
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
