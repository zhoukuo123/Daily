package com.zk.item.service;

import com.zk.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 内部的降级(商品中心), 放到item-service里面来实现, 通过 @HystrixCommand 注解指定降级方法
 *
 * 调用方的降级(订单中心, 调用商品中心的服务), 由订单中心来定义降级逻辑
 *
 * @author CoderZk
 */
@FeignClient("foodie-item-service")
@RequestMapping("/item-comments-api")
public interface ItemCommentsService {

    /**
     * 我的评价查询 分页
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("myComments")
    PagedGridResult queryMyComments(@RequestParam("userId") String userId,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @PostMapping("saveComments")
    void saveComments(@RequestBody Map<String, Object> map);
}
