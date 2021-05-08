package com.zk.mapper;

import com.zk.pojo.OrderStatus;
import com.zk.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
public interface OrderMapperCustom {
    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

    int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);
}
