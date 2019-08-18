package com.jack.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jack.domain.Orders;
import com.jack.domain.Product;
import com.jack.service.IOrdersService;
import com.jack.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

   /* *//**
     * 查询所有，未分页
     * @return
     *//*
    @RequestMapping("/findAll")
    public ModelAndView findAll(){

        ModelAndView mv=new ModelAndView (  );
        List<Orders> list = iOrdersService.findAll ();
        mv.addObject ( "ordersList",list );
        mv.setViewName ( "orders-list" );

        return mv;
    }*/

    /**
     * 查询所有，进行分页
     * @return
     */
    @RequestMapping("/findAll")                              //获取请求URL的参数并必须有，并赋值给指定的变量
    public ModelAndView findAll(@RequestParam(name = "page",required = true)Integer page,@RequestParam(name = "size",required = true)Integer size){

        ModelAndView mv=new ModelAndView (  );
        List<Orders> list = iOrdersService.findAll (page,size);
        //把查询到的结果集封装到PageInfo bean中
        PageInfo pageInfo=new PageInfo ( list );
       mv.addObject ( "pageInfo" ,pageInfo);
        mv.setViewName ( "orders-list" );

        return mv;
    }

    /**
     * order的详情查询--根据id查询--需要多表联查
     * @param orderId
     * @return
     */
    @RequestMapping("/findById")                              //获取请求URL的参数并必须有，并赋值给指定的变量
    public ModelAndView  findById(@RequestParam(name = "id",required = true)String orderId){

        ModelAndView mv=new ModelAndView (  );
        Orders orders = iOrdersService.findById (orderId);
        mv.addObject ( "orders" ,orders);
        mv.setViewName ( "orders-show" );

        return mv;
    }

}
