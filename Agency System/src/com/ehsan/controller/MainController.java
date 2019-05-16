package com.ehsan.controller;

import model.da.DaoImpl;
import model.entity.Administrator;
import model.entity.Customer;
import model.entity.Driver;
import model.entity.Trip;
import model.service.Validation;
import model.util.Ciphering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ehsan on 8/13/2018.
 */

@Controller
public class MainController {

    @Autowired
    DaoImpl dao ;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String test1(@RequestParam String usernamesignup, @RequestParam String emailsignup,
                       @RequestParam String passwordsignup, @RequestParam String entity_type, HttpServletRequest request)
    {
        if (entity_type.equals("customer"))
        {
            Customer customer1 = null;
            try {
                customer1 = new Customer(usernamesignup,emailsignup, new Ciphering().mD5(passwordsignup));
            } catch (NoSuchAlgorithmException e) {
                e.getMessage();
            }
            dao.save(customer1);
            request.getSession().setAttribute("ticket",true);
            return "redirect:/spring/secured/continue";
        }

        else if (entity_type.equals("driver"))
        {
            Driver driver1 = null;
            try {
                driver1 = new Driver(usernamesignup,emailsignup, new Ciphering().mD5(passwordsignup));
            } catch (NoSuchAlgorithmException e) {
                e.getMessage();
            }
            dao.save(driver1);
            request.getSession().setAttribute("ticket",true);
        }
        return "redirect:/spring/secured/continue";
    }

    @RequestMapping(value = "/secured/registertrip", method = RequestMethod.POST)
    public String register_Trip(@RequestParam String username, @RequestParam String password, @RequestParam String begin,
                                @RequestParam String destination, @RequestParam String customer_name,
                                @RequestParam String driver_name ,HttpServletRequest request)
    {
            Trip trip = null;
            try {
                trip = new Trip(username,new Ciphering().mD5(password),begin,destination,new Date(),customer_name,driver_name);
            } catch (NoSuchAlgorithmException e) {
                e.getMessage();
            }
            dao.save(trip);
            Boolean successnewtrip = true;
            request.getSession().setAttribute("admin_successnewtrip",successnewtrip);
            return "redirect:/spring/secured/continue";
    }

    @RequestMapping(value = "/secured/continue")
    public String continue_page(HttpServletRequest request)
    {
        request.getSession().removeAttribute("ticket");
        return "continue";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String signIn(@RequestParam String username ,@RequestParam String password
            ,HttpServletRequest request,@RequestParam String entity_type){

        Administrator administrator = dao.getRecordOfAdministrator(1l);

        if (administrator.getPassword().equals(password))
        {
            request.getSession().setAttribute("admin","ADMINISTRATOR");
            request.getSession().setAttribute("ticket",true);
            return "redirect:/spring/";
        }

        Boolean condition = false;

        if (entity_type.equals("customer"))
        {
            // if exist an entity with this password

            if ((condition  = Validation.isValidForCustomer(password,username))) {
                Customer customer = Validation.getCustomerByPassword(password,username);
                request.getSession().setAttribute("user", customer);
                request.getSession().setAttribute("status",condition);
                request.getSession().setAttribute("ticket",true);
                return "redirect:/spring/";
            }
            // condition used to display message to user that entered username or password is Wrong
            else{
                request.getSession().setAttribute("status",condition);
                return "/Registration_Form" ;
            }
        }
        else if (entity_type.equals("driver"))
        {
            if ((condition  = Validation.isValidForDriver(password,username))) {
                Driver driver = Validation.getDriverByPassword(password,username);
                request.getSession().setAttribute("driver", driver);
                request.getSession().setAttribute("status",condition);
                request.getSession().setAttribute("ticket",true);
                return "redirect:/spring/";
            }
            else{
                request.getSession().setAttribute("status",condition);
                return "/Registration_Form" ;
            }
        }
        return "";
    }


    @RequestMapping(value = "/secured/getAll",method = RequestMethod.POST)
    public String getAllWithoutAuthentication(@RequestParam HttpServletRequest request){

         ArrayList arrayList = dao.getAllWithoutAuthentication();
         request.getSession().setAttribute("data",arrayList);
         return "redirect:/spring/";
    }

   /* @ResponseBody
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public  Father test2(String id){

        Father father = dao.getRecord(Long.parseLong(id));
        return father;}*/

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String viewPersons2( HttpServletRequest request) {

        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");
        return "index";
    }

    @RequestMapping(value="/Registration_Form",method = RequestMethod.GET)
    public String view_Registration_page() {

        return "Registration_Form";
    }

    @RequestMapping(value = "/secured/sessiontimeout" ,method = RequestMethod.GET)
    public String session(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/spring/";
    }

    @RequestMapping(value = "/secured/showtripsflag" ,method = RequestMethod.GET)
    public String showTrips(HttpServletRequest request)
    {
        request.getSession().setAttribute("admin_show",true);
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("deletedrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");
        return "customer profile";
    }

    @RequestMapping(value = "/secured/newtrip" ,method = RequestMethod.GET)
    public String newTrip(HttpServletRequest request)
    {
        request.getSession().setAttribute("admin_newtrip",true);
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");
        return "customer profile";
    }
    @RequestMapping(value = "/secured/edittripflag" ,method = RequestMethod.GET)
    public String editTripflag(HttpServletRequest request)
    {
        request.getSession().setAttribute("admin_edittrip",true);
        request.getSession().removeAttribute("admin_show");
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("deletedrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");
        return "customer profile";
    }

    @RequestMapping(value = "/secured/edittrip" ,method = RequestMethod.POST)
    public String editTrip(@RequestParam String username, @RequestParam String password, @RequestParam String begin,
                           @RequestParam String destination, @RequestParam String customer_name,
                           @RequestParam String driver_name ,@RequestParam long id,HttpServletRequest request ) throws NoSuchAlgorithmException {
        if ( dao.findTripEntity(id) == false )
        {
            request.getSession().setAttribute("entitynotexist",true);
            request.getSession().removeAttribute("admin_show");
            request.getSession().removeAttribute("admin_newtrip");
            request.getSession().removeAttribute("admin_edittrip");
            request.getSession().removeAttribute("admin_deletetrip");
            request.getSession().removeAttribute("showdrivers");
            request.getSession().removeAttribute("editdriverprofile");
            request.getSession().removeAttribute("editcustomerprofile");
            return "customer profile";
        }
        Trip trip = new Trip(username,new Ciphering().mD5(password),begin,destination,new Date(),customer_name,driver_name);
        dao.updateTripRecord(id,trip);
        request.getSession().setAttribute("admin_show",true);
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");

        return "customer profile";
    }

    @RequestMapping(value = "/secured/deletetripflag" ,method = RequestMethod.GET)
    public String deleteTrip(HttpServletRequest request)
    {
            request.getSession().setAttribute("admin_deletetrip",true);
            request.getSession().removeAttribute("admin_newtrip");
            request.getSession().removeAttribute("admin_edittrip");
            request.getSession().removeAttribute("admin_show");
            request.getSession().removeAttribute("entitynotexist");
            request.getSession().removeAttribute("deletedrivers");
            request.getSession().removeAttribute("showdrivers");
            request.getSession().removeAttribute("editdriverprofile");
            request.getSession().removeAttribute("editcustomerprofile");
            return "customer profile";
    }

    @RequestMapping(value = "/secured/deletetriprecord" ,method = RequestMethod.POST)
    public String deleteTripRecord(HttpServletRequest request , @RequestParam long id)
    {
        request.getSession().setAttribute("admin_show",true);
        request.getSession().removeAttribute("admin_newtrip");
        request.getSession().removeAttribute("admin_edittrip");
        request.getSession().removeAttribute("admin_deletetrip");
        request.getSession().removeAttribute("entitynotexist");
        request.getSession().removeAttribute("showdrivers");
        request.getSession().removeAttribute("editdriverprofile");
        request.getSession().removeAttribute("editcustomerprofile");
        dao.deleteTripRecord(id);
        return "customer profile";
    }
}

