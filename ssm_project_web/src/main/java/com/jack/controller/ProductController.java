package com.jack.controller;

import com.jack.domain.Product;
import com.jack.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    /**
     * 查询所有
     * @return
     */

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv=new ModelAndView (  );
        List<Product> list = iProductService.findAll ();
        mv.addObject ( "productList",list );
        mv.setViewName ( "product-list" );

        return mv;
    }

    /**
     * 保存一个产品
     */
    @RequestMapping("/save.do")
    public String saveProduct(Product product) throws Exception {

    iProductService.saveProduct(product);

    return "redirect:findAll";

    }


}
