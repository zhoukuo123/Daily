package com.zk.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.zk.item.mapper.ItemsCommentsMapperCustom;
import com.zk.item.pojo.vo.MyCommentVO;
import com.zk.item.service.ItemCommentsService;
import com.zk.pojo.PagedGridResult;
import com.zk.service.BaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
@RestController
public class ItemCommentsServiceImpl extends BaseService implements ItemCommentsService {

    @Resource
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);

        List<MyCommentVO> list = itemsCommentsMapperCustom.queryMyComments(map);

        return setterPagedGrid(list, page);
    }

    @Override
    public void saveComments(Map<String, Object> map) {
        itemsCommentsMapperCustom.saveComments(map);
    }
}
