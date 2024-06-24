package com.cy.store.mapper;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService implements IDistrictServer {

    @Autowired
    DistrictMapper districtMapper;

    @Override
    public List<District> findByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        return list;
    }

    @Override
    public String findNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
