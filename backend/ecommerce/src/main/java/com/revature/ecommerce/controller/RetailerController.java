package com.revature.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.entity.RetailerInventory;
import com.revature.ecommerce.exception.ItemExistsException;
import com.revature.ecommerce.service.RetailerManagementService;

import java.util.List;

@RestController
@RequestMapping("retailer")
public class RetailerController {

    @Autowired
    RetailerManagementService retailerManagementService;

    @GetMapping(path = "all")
    public List<RetailerInventory> getRetailers() {
        return retailerManagementService.getRetailers();
    }

    @GetMapping(path = "getByName/{retailerId}")
    public List<RetailerInventory> getRetailerByName(@PathVariable("retailerId") String retailerName) throws ItemExistsException {
        return retailerManagementService.getRetailersByName(retailerName);
    }

    @GetMapping(path = "getById/{retailerId}")
    public RetailerInventory getRetailerById(@PathVariable("retailerId") Integer retailerId) throws ItemExistsException {
        return retailerManagementService.getRetailerById(retailerId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "add")
    public RetailerInventory addRetailer(@RequestBody RetailerInventory retailerInventory) throws ItemExistsException {
        return retailerManagementService.saveRetailer(retailerInventory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "update/{retailerId}")
    public RetailerInventory updateRetailer(@PathVariable("retailerId") Integer retailerId,
                                            @RequestBody RetailerInventory retailerInventory) throws ItemExistsException {
        return retailerManagementService.updateRetailer(retailerId, retailerInventory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "delete/{retailerId}")
    public String deleteRetailerById(@PathVariable("retailerId") Integer retailerId) throws ItemExistsException {
        retailerManagementService.deleteRetailer(retailerId);
        return "Retailer with Id: " + retailerId + " is successfully deleted";
    }

}
