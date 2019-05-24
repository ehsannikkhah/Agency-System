package com.ehsan.controller;

import model.da.DaoImpl;
import model.entity.Customer;
import model.util.Ciphering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ehsan on 8/13/2018.
 */

@Controller

public class CustomerController {

    @Autowired
    DaoImpl dao ;

    @RequestMapping(value="/secured/customer profile",method = RequestMethod.GET)
    public String view_customer_profile() {

        return "customer profile";
    }

    @RequestMapping(value = "/secured/editcustomerprofileflag" ,method = RequestMethod.GET)
    public String editcustomerprofileflag(HttpServletRequest request)
    {
        request.getSession().setAttribute("editcustomerprofile",true);
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("admin_deletedrivers");
        request.getSession().removeAttribute("deletedrivers");
        request.getSession().removeAttribute("editdriverprofile");

        return "customer profile";
    }

    @RequestMapping(value = "/secured/editcustomerprofile" ,method = RequestMethod.POST)
    public String editcustomerprofile(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                                    @RequestParam String address,@RequestParam long id,HttpServletRequest request ) throws NoSuchAlgorithmException {
        Customer customer = new Customer(username,email,new Ciphering().mD5(password));
        customer.setAddress(address);
        dao.updateCustomerRecord(id,customer);
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");

        return "customer profile";
    }
}

