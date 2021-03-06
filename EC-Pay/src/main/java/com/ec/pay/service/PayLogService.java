package com.ec.pay.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ec.pay.dto.PayStateEnum;
import com.ec.pay.filter.LoginInterceptor;
import com.ec.pay.mapper.PayLogMapper;
import com.ec.pay.pojo.PayLog;
import com.ec.pay.utils.PayHelper;

/**
 * @author bystander
 * @date 2018/10/5
 */
@Service
public class PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    @Autowired
    private PayHelper payHelper;

    public void createPayLog(Long orderId, Long actualPay) {
        //创建支付对象
        PayLog payLog = new PayLog();
        payLog.setStatus(PayStateEnum.NOT_PAY.getValue());
        payLog.setPayType(1);
        payLog.setOrderId(orderId);
        payLog.setTotalFee(actualPay);
        payLog.setCreateTime(new Date());
        //获取用户信息
        payLog.setUserId(LoginInterceptor.getLoginUser().getId());

        payLogMapper.insertSelective(payLog);
    }

    @Transactional
    public Integer queryOrderStateByOrderId(Long orderId) {
        //优先去支付日志表中查询信息
        PayLog payLog = payLogMapper.selectByPrimaryKey(orderId);
        if (payLog == null || PayStateEnum.NOT_PAY.getValue() == payLog.getStatus()) {
            //未支付，调用微信接口查询订单支付状态
            return payHelper.queryPayState(orderId).getValue();
        }

        if (PayStateEnum.SUCCESS.getValue() == payLog.getStatus()) {
            //支付成功，返回1
            return PayStateEnum.SUCCESS.getValue();
        }

        //如果是其他状态，返回失败
        return PayStateEnum.FAIL.getValue();
    }
}
