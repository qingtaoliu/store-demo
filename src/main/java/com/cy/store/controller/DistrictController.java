package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictService;
import com.cy.store.units.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictController extends BaseController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("parent")
    public JsonResult findByParent(String parent) {
        List<District> list = districtService.findByParent(parent);
        return new JsonResult(OK, list);
    }

}
