package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictService;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Autowired(required = false)
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer MAX_COUNT;

    public void addAddress(Integer uid, String username, Address address) {
       Integer count = addressMapper.countById(uid);
       if (count >= MAX_COUNT) {
           throw new AddressCountLimitException("数量超过"+MAX_COUNT);
       }
        String provinceName = districtService.findNameByCode(address.getProvinceCode());
        String cityName = districtService.findNameByCode(address.getCityCode());
        String areaName = districtService.findNameByCode(address.getAreaCode());

        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
       address.setUid(uid);
       address.setIsDefault(count > 0 ? 1 : 0);
       address.setModifiedTime(new Date());
       address.setCreatedTime(new Date());
       address.setCreatedUser(username);
       address.setModifiedUser(username);

       Integer row = addressMapper.addAddress(address);
       if (row != 1) {
           throw new InsertException("插入异常");
       }
    }

    @Override
    public List<Address> findAddressByUid(Integer uid) {
        List<Address> list = addressMapper.findInfoByUid(uid);
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }



    @Override
    public void deleteAddressByAid(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("地址不存在");
        }
        Integer result = addressMapper.deleteAddressById(aid);
        if (result != 1) {
            throw new DeleteException("删除失败");
        }
        Integer count = addressMapper.countById(uid);
        if (count == 0) {
            //如果查出数据为0，说明刚刚删除的就是最后一条数据，此时也不需要重新找一个地址设置为默认，直接退出就行
            return;
        }

        //如果删除的地址不是默认值，删除之后就不需要进行其他操作
        if (address.getIsDefault() == 0) {
            return;
        }

        //走到这里说明删除的是默认地址并且删除后数据库中还有地址，此时就需要重新设置一个默认的收货地址，规则是最后修改的地址设置为默认地址
        //先找到最后修改的那条地址信息
        Address lastModifiedAddress = addressMapper.findLastModified(uid);

        result = addressMapper.updateDefaultByAid(lastModifiedAddress.getAid(), username, new Date());
        if (result != 1) {
            throw new UpdateException("更新时发生未知异常");
        }

    }

    @Override
    public void setAddressIsDefault(Integer uid, Integer aid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        Integer integer = addressMapper.updateNonDefault(uid);
        if (integer < 1) {
            throw new UpdateException("更新时产生异常");
        }
        Integer rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新时产生异常");
        }
    }
    @Override
    public Address findAddressByAid(Integer aid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        return address;
    }

    @Override
    public void updateAddressByAid(Integer aid, String username, Address address) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        address.setAid(aid);
        address.setModifiedTime(new Date());
        address.setModifiedUser(username);
        Integer row = addressMapper.updateAddressByAid(address);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }
    }
}
