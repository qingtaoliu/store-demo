package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    Integer addAddress(Address address);

    Integer countById(Integer uid);

    /*收货地址*/
    List<Address> findInfoByUid(Integer uid);

    Address findByAid(Integer aid);

    /*删除*/
    Integer deleteAddressById(Integer id);

    /* 设置默认收货地址 */

    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    Integer updateNonDefault(Integer uid);

    Address findLastModified(Integer uid);

    Integer updateAddressByAid(Address address);
}
