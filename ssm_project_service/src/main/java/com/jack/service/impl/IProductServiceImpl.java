package com.jack.service.impl;

import com.jack.dao.IProductDao;
import com.jack.domain.Product;
import com.jack.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.List;
//配置注解与事务注解
@Service
@Transactional
@RolesAllowed ( "ADMIN" )
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll ( ) {


        return iProductDao.findAll ();
    }

    @Override
    public void saveProduct (Product product ) {
        iProductDao.saveProduct(product);
    }
}
