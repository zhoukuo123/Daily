package com.example.tccdemo.service;

import com.example.tccdemo.db131.dao.AccountAMapper;
import com.example.tccdemo.db131.dao.PaymentMsgMapper;
import com.example.tccdemo.db131.model.AccountA;
import com.example.tccdemo.db131.model.PaymentMsg;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class PaymentServcie {

    @Resource
    private AccountAMapper accountAMapper;

    @Resource
    private PaymentMsgMapper paymentMsgMapper;

    @Autowired
    private DefaultMQProducer producer;


    /**
     * 支付接口
     *
     * @param userId
     * @param orderId
     * @param amount
     * @return 0:成功；1:用户不存在;2:余额不足
     */
    @Transactional(transactionManager = "tm131")
    public int pament(int userId, int orderId, BigDecimal amount) {
        //支付操作
        AccountA accountA = accountAMapper.selectByPrimaryKey(userId);
        if (accountA == null) {
            return 1;
        }
        if (accountA.getBalance().compareTo(amount) < 0) {
            return 2;
        }
        accountA.setBalance(accountA.getBalance().subtract(amount));
        accountAMapper.updateByPrimaryKey(accountA);

        PaymentMsg paymentMsg = new PaymentMsg();
        paymentMsg.setOrderId(orderId);
        paymentMsg.setStatus(0); // 未发送
        paymentMsg.setFalureCnt(0); // 失败次数
        paymentMsg.setCreateTime(new Date());
        paymentMsg.setCreateUser(userId);
        paymentMsg.setUpdateTime(new Date());
        paymentMsg.setUpdateUser(userId);

        paymentMsgMapper.insertSelective(paymentMsg);

        return 0;
    }
}
