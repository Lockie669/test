package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jack.dao.IOrdersDao;
import com.jack.domain.Orders;
import com.jack.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@PreAuthorize("authentication.principal.username == 'tom'") //只有tom用户下才能访问
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;

    //查询订单所有
    @Override
    public List<Orders> findAll (int page,int size ) {

        PageHelper.startPage ( page,size);
        return iOrdersDao.findAll ();
    }

    //查询一个订单的详情，需要多表联查
    @Override
    public Orders findById (String orderId) {

        return iOrdersDao.findById(orderId);
    }
}
