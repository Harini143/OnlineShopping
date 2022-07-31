package com.revature.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.RetailerInventory;
import com.revature.ecommerce.exception.ItemExistsException;
import com.revature.ecommerce.repository.ProductJpaRepository;
import com.revature.ecommerce.repository.RetailerJpaRepository;
import com.revature.ecommerce.utils.AppConstants;

import java.util.List;

@Service
public class RetailerManagementServiceImpl implements RetailerManagementService {

    @Autowired
    RetailerJpaRepository retailerJpaRepository;
    
    @Override
    public RetailerInventory saveRetailer(RetailerInventory retailerInventory) throws ItemExistsException {
        if (retailerJpaRepository.findById(retailerInventory.getRetailerId()).isPresent())
            throw new ItemExistsException(AppConstants.ID_ALREADY_EXISTS);
        return retailerJpaRepository.save(retailerInventory);
    }

    @Override
    public RetailerInventory updateRetailer(Integer retailerId, RetailerInventory retailerInventory) throws ItemExistsException {
        RetailerInventory returnRetailerInventory = getRetailerById(retailerId);
        returnRetailerInventory.setRetailerName(retailerInventory.getRetailerName());
        returnRetailerInventory.setRetailerDate(retailerInventory.getRetailerDate());
        return retailerJpaRepository.save(returnRetailerInventory);
    }

    @Override
    public void deleteRetailer(Integer retailerId) throws ItemExistsException {
        if (!retailerJpaRepository.findById(retailerId).isPresent())
            throw new ItemExistsException(AppConstants.ID_NOT_EXISTS);
        retailerJpaRepository.deleteById(retailerId);
    }

    @Override
    public RetailerInventory getRetailerById(Integer retailerId) throws ItemExistsException {
        return retailerJpaRepository.findById(retailerId).orElseThrow(() -> new ItemExistsException(AppConstants.ID_ALREADY_EXISTS));
    }

    @Override
    public List<RetailerInventory> getRetailers() {
        return retailerJpaRepository.findAll();
    }

    @Override
    public List<RetailerInventory> getRetailersByName(String name) throws ItemExistsException {
       // String query = "%" + name + "%";
        List<RetailerInventory> retailerList = retailerJpaRepository.findByRetailerNameContainingIgnoreCase(name);
        System.out.println("list: ");
        System.out.println(retailerList);
        if (retailerList.isEmpty())
            throw new ItemExistsException(AppConstants.NO_RETAILER_NAME_FOUND + name);
        return  retailerList;
    }


}
