package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.impl.AddressService;
import com.cy.store.units.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("add")
    public JsonResult add(Address address, HttpSession httpSession){
        Integer uid = getUidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        addressService.addAddress(uid,username,address);
        return new JsonResult(OK);
    }

    @GetMapping("/all")
    public JsonResult getAddressByUid(HttpSession httpSession){
        Integer uid = getUidFromSession(httpSession);
        List<Address> list = addressService.findAddressByUid(uid);
        return new JsonResult(OK,list);
    }
    @RequestMapping("setDefault")
    public JsonResult setDefaultAddress(Integer aid, HttpSession httpSession){
        Integer uid = getUidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        addressService.setAddressIsDefault(uid, aid, username);
        return new JsonResult(OK);
    }
    @DeleteMapping("delete")
    public JsonResult deleteAddressByAid(Integer aid, HttpSession httpSession){
        Integer uid = getUidFromSession(httpSession);
        String username = getUsernameFromSession(httpSession);
        addressService.deleteAddressByAid(uid, aid, username);
        return new JsonResult(OK);
    }

    @GetMapping("{aid}")
    public JsonResult getAddressByAid(@PathVariable Integer aid){
        Address result = addressService.findAddressByAid(aid);
        return new JsonResult(OK,result);
    }

    @RequestMapping("update")
    public JsonResult updateAddressByAid(Address address, HttpSession httpSession){
        String username = getUsernameFromSession(httpSession);
        addressService.updateAddressByAid(address.getAid(), username, address);
        return new JsonResult(OK);
    }
}
