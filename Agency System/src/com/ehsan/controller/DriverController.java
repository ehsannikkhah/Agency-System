package com.ehsan.controller;

import model.da.DaoImpl;
import model.entity.Driver;
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
public class DriverController {

    @Autowired
    DaoImpl dao ;

    @RequestMapping(value = "/secured/deletedriversflag" ,method = RequestMethod.GET)
    public String deletedriversflag(HttpServletRequest request)
    {
        request.getSession().setAttribute("deletedrivers",true);
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");
        return "customer profile";
    }

    @RequestMapping(value = "/secured/deletedrivers" ,method = RequestMethod.POST)
    public String deletedrivers(HttpServletRequest request, @RequestParam long id)
    {
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("deletedrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");


        dao.deleteDriverRecord(id);
        return "customer profile";
    }

    @RequestMapping(value = "/secured/showdriversflag" ,method = RequestMethod.GET)
    public String showdrivers(HttpServletRequest request)
    {
        request.getSession().setAttribute("showdrivers",true);
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("deletedrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");

        return "customer profile";
    }

    @RequestMapping(value = "/secured/editdriverprofileflag" ,method = RequestMethod.GET)
    public String editdriverprofileflag(HttpServletRequest request)
    {
        request.getSession().setAttribute("editdriverprofile",true);
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("admin_deletedrivers");
        request.getSession().removeAttribute("deletedrivers");
        request.getSession().removeAttribute("editcustomerprofile");

        return "customer profile";
    }

    @RequestMapping(value = "/secured/editdriverprofile" ,method = RequestMethod.POST)
    public String editdriverprofile(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                           @RequestParam String address,@RequestParam long id,HttpServletRequest request ) throws NoSuchAlgorithmException {
        Driver driver = new Driver(username,email,new Ciphering().mD5(password));
        driver.setAddress(address);
        dao.updateDriverRecord(id,driver);
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

