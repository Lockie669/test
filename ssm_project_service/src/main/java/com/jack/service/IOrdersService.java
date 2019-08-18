package com.jack.service;

import com.jack.domain.Orders;

import java.util.List;

public interface IOrdersService {

    public List<Orders> findAll(int page,int size );

    public Orders findById (String orderId);
}
