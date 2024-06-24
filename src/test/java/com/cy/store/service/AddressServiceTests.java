package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.impl.AddressService;
import com.cy.store.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressServiceTests {

    @Autowired
    private AddressService addressService;

    @Test
    public void addAddress() {
        Address address = new Address();
        address.setUid(32);
        address.setName("test12");
        addressService.addAddress(32, "luke liu", address);
    }
    @Test
    void setAddressIsDefault () {
        addressService.setAddressIsDefault(32, 31, "luke liu");
    }

    @Test
    void deleteAddress() {
        addressService.deleteAddressByAid(28, 32, "luke liu");
    }

}
