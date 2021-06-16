package com.zk.service.impl;

import com.github.pagehelper.PageHelper;
import com.zk.enums.CommentLevel;
import com.zk.enums.YesOrNo;
import com.zk.mapper.*;
import com.zk.pojo.*;
import com.zk.pojo.vo.CommentLevelCountsVO;
import com.zk.pojo.vo.ItemCommentVO;
import com.zk.pojo.vo.SearchItemsVO;
import com.zk.pojo.vo.ShopcartVO;
import com.zk.service.ItemService;
import com.zk.service.impl.center.BaseService;
import com.zk.utils.DesensitizationUtil;
import com.zk.utils.PagedGridResult;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderZk
 */
@Service
@Slf4j
public class ItemServiceImpl extends BaseService implements ItemService {

    @Resource
    private ItemsMapper itemsMapper;

    @Resource
    private ItemsImgMapper itemsImgMapper;

    @Resource
    private ItemsSpecMapper itemsSpecMapper;

    @Resource
    private ItemsParamMapper itemsParamMapper;

    @Resource
    private ItemsCommentsMapper itemsCommentsMapper;

    @Resource
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private RedissonClient redissonClient;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example itemsImgExp = new Example(ItemsImg.class);
        Example.Criteria criteria = itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(itemsImgExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example itemsSepcExp = new Example(ItemsSpec.class);
        Example.Criteria criteria = itemsSepcExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(itemsSepcExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example itemsParamExp = new Example(ItemsParam.class);
        Example.Criteria criteria = itemsParamExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(itemsParamExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        CommentLevelCountsVO countsVO = new CommentLevelCountsVO();
        countsVO.setTotalCounts(totalCounts);
        countsVO.setGoodCounts(goodCounts);
        countsVO.setNormalCounts(normalCounts);
        countsVO.setBadCounts(badCounts);

        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null) {
            condition.setCommentLevel(level);
        }

        return itemsCommentsMapper.selectCount(condition);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level,
                                              Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);

        /**
         * 分页 PageHelper
         * page: 第几页
         * pageSize: 每页显示条数
         * 都是由前端提供
         */
        PageHelper.startPage(page, pageSize);

        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(map);

        // 敏感信息脱敏
        for (ItemCommentVO vo : list) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }

        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(String keywords, String sort,
                                       Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keywords", keywords);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);

        List<SearchItemsVO> list = itemsMapperCustom.searchItems(map);

        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);

        List<SearchItemsVO> list = itemsMapperCustom.searchItemsByThirdCat(map);

        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String specIds) {
        String[] ids = specIds.split(",");
        List<String> specIdsList = new ArrayList<>();
        Collections.addAll(specIdsList, ids);

        return itemsMapperCustom.queryItemsBySpecIds(specIdsList);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsSpec queryItemSpecById(String specId) {
        return itemsSpecMapper.selectByPrimaryKey(specId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryItemMainImgById(String itemId) {

        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNo.YES.type);
        ItemsImg result = itemsImgMapper.selectOne(itemsImg);
        return result != null ? result.getUrl() : "";
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void decreaseItemSpecStock(String specId, Integer buyCounts) {

        // synchronized 不推荐使用, 集群下无用, 性能低下
        // 锁数据库: 不推荐, 导致数据库性能低下
        // 分布式锁 zookeeper redis

        // lockUtil.getLock(); -- 加锁

        // 1. 查询库存
//        int stock = 10;

        // 2. 判断库存, 是否能够减少到0以下
//        if (stock - buyCounts < 0) {
        // 提示用户库存不够
        // 10 - 3 - 3 - 5 = -1 防止超卖
//        }

        // lockUtil.unLock(); -- 解锁

        // 并发事务下产生的问题: 脏读, 不可重复读, 幻读
        // 脏读: 因为这个事务不存在回滚rollback的情况, 所以不存在脏读的问题
        // 不可重复读: 因为在这个事务中没有读取操作, 也没有读取2次某个数据, 所以不存在不可重复读的问题
        // 幻读: 因为在这个事务中没有插入, 删除操作, 所以不存在幻读的问题

        //  所以, 事务隔离级别没必要设置, 就为DEFAULT即可, 在mysql上InnoDB默认为可重复读 (repeatable-read)
        //

        // DML 语句默认就是一个事务, 是原子操作, 当程序A 对表执行where判断,判断成功了,但还没有对表进行修改.
        // 这时候程序B是无法对表进行where判断的. 因为程序A对表的X锁仍未释放,程序B无法申请获取X锁.
        // 更新操作会给要更新的数据加排它锁(行锁), 所以其他的 update 语句只要是更新同样的这一条记录的操作不会执行,
        int result = itemsMapperCustom.decreaseItemSpecStock(specId, buyCounts);
        if (result != 1) {
            throw new RuntimeException("订单创建失败, 原因: 库存不足!");
        }

        /**
         * 分布式锁 编写业务代码
         * 1. Redisson 是基于 Redis, 使用 Redisson 之前, 项目必须使用 Redis
         * 2. 注意 getLock 方法中的参数, 以 specId 作为参数, 每个 specId 一个 key, 和数据库中的行锁是一致的, 不会是方法级别的锁
         */
        RLock rlock = redissonClient.getLock("SPECID_" + specId);
        try {
            rlock.lock(5, TimeUnit.SECONDS);
            int result2 = itemsMapperCustom.decreaseItemSpecStock(specId, buyCounts);
            if (result2 != 1) {
                throw new RuntimeException("订单创建失败, 原因: 库存不足!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            /**
             * 不管业务是否操作正确, 随后都要释放掉分布式锁
             * 如果不释放, 过了超时时间也会自动释放
             */
            rlock.unlock();
        }


    }
}
