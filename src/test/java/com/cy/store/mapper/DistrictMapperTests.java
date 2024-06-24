package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DistrictMapperTests {

   @Autowired
    private DistrictMapper districtMapper;

   @Test
    public void findByParent() {
       List<District> list = districtMapper.findByParent("110100");
       System.out.println(list);
   }

   @Test
    public void findNameByCode () {
       String list = districtMapper.findNameByCode("110102");
       System.out.println(list);
   }
}
