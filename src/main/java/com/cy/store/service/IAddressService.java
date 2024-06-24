package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService  {

    void addAddress (Integer uid, String username, Address address);

    List<Address> findAddressByUid(Integer uid);

    Address findAddressByAid(Integer Aid);

    void deleteAddressByAid(Integer aid, Integer uid, String username);

    void setAddressIsDefault(Integer uid, Integer aid, String username);

    void updateAddressByAid(Integer aid, String username, Address address);
}
