package com.zk.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zk.mapper.OrderMapperCustom;
import com.zk.pojo.vo.MyOrdersVO;
import com.zk.service.center.MyOrdersService;
import com.zk.utils.PagedGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

    @Resource
    private OrderMapperCustom orderMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrdersVO> list = orderMapperCustom.queryMyOrders(map);

        return setterPagedGrid(list, page);
    }

    /**
     * 分页信息封装, 以提供给前端使用
     *
     * @param list 每页的记录数据data
     * @param page 当前页数
     * @return PagedGridResult
     */
    private PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
