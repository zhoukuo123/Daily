package com.zk.item.service;

import com.zk.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author CoderZk
 */
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
