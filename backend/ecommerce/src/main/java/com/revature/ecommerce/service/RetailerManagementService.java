package com.revature.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.RetailerInventory;
import com.revature.ecommerce.exception.ItemExistsException;
import com.revature.ecommerce.repository.RetailerJpaRepository;

import java.util.List;


public interface RetailerManagementService {
    RetailerInventory saveRetailer(RetailerInventory retailerInventory) throws ItemExistsException;
    RetailerInventory updateRetailer(Integer retailerId, RetailerInventory retailerInventory) throws ItemExistsException;
    void deleteRetailer(Integer retailerId) throws ItemExistsException;
    RetailerInventory getRetailerById(Integer retailerId) throws ItemExistsException;
    List<RetailerInventory> getRetailers();
    List<RetailerInventory> getRetailersByName(String name) throws ItemExistsException;


}
