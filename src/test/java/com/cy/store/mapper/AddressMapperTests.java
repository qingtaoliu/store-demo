package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insertAddress() {
        Address address = new Address();
        address.setUid(32);
        address.setName("test");
        addressMapper.addAddress(address);
    }

    @Test
    public void countByUid () {
        Integer count = addressMapper.countById(32);
        System.out.println(count);
    }

    @Test
    public void findAddressByUid() {
        List<Address> list = addressMapper.findInfoByUid(32);
        System.out.println(list);

    }
    @Test
    public void deleteAddressByAid() {
        addressMapper.deleteAddressById(27);
    }

    @Test
    public void updateAddressByAid() {
        Address address = new Address();
        address.setAid(31);
        address.setName("test1234");
        address.setProvinceCode("北京市");
        address.setZip("123456");
        addressMapper.updateAddressByAid(address);
    }
}
