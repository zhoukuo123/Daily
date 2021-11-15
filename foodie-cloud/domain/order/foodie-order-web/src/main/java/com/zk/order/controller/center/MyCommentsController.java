package com.zk.order.controller.center;

import com.zk.controller.BaseController;
import com.zk.enums.YesOrNo;
import com.zk.item.service.ItemCommentsService;
import com.zk.order.fallback.itemservice.ItemCommentsFeignClient;
import com.zk.order.pojo.OrderItems;
import com.zk.order.pojo.Orders;
import com.zk.order.pojo.bo.center.OrderItemsCommentBO;
import com.zk.order.service.center.MyCommentsService;
import com.zk.order.service.center.MyOrdersService;
import com.zk.pojo.JSONResult;
import com.zk.pojo.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CoderZk
 */
@Api(value = "用户中心评价模块", tags = "用户中心评价模块相关接口")
@RestController
@RequestMapping("/mycomments")
public class MyCommentsController extends BaseController {

    @Resource
    private MyCommentsService myCommentsService;

    @Autowired
    private MyOrdersService myOrdersService;

    @Autowired
//    private ItemCommentsService itemCommentsService;
    private ItemCommentsFeignClient itemCommentsService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "POST")
    @PostMapping("/pending")
    public JSONResult pending(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        // 判断用户和订单是否关联
        JSONResult checkResult = myOrdersService.checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 判断该笔订单是否已经评价过, 评价过了就不再继续
        Orders myOrder = (Orders) checkResult.getData();
        if (myOrder.getIsComment().equals(YesOrNo.YES.type)) {
            return JSONResult.errorMsg("该笔订单已经评价");
        }

        List<OrderItems> list = myCommentsService.queryPendingComment(orderId);

        return JSONResult.ok(list);
    }

    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public JSONResult saveList(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId,
            @RequestBody List<OrderItemsCommentBO> commentList) {

        System.out.println(commentList);

        // 判断用户和订单是否关联
        JSONResult checkResult = myOrdersService.checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 判断评论内容list不能为空
        if (commentList == null || commentList.isEmpty()) {
            return JSONResult.errorMsg("评论内容不能为空");
        }

        myCommentsService.saveComments(userId, orderId, commentList);

        return JSONResult.ok();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @PostMapping("/query")
    public JSONResult query(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = itemCommentsService.queryMyComments(userId, page, pageSize);

        return JSONResult.ok(grid);
    }
}
