package com.sxl.springcloud.service.impl;


import com.sxl.springcloud.entities.Payment;
import com.sxl.springcloud.service.PaymentService;
import com.sxl.springcloud.dao.PaymentDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl  implements PaymentService {


    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){



        return paymentDao.create(payment);
    }
    public Payment getPaymentById(@Param("id")Long id){


        return paymentDao.getPaymentById(id);
    }

}
