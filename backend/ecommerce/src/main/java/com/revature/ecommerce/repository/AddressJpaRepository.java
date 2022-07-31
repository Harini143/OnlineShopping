package com.revature.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.ecommerce.entity.Address;

import java.util.List;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, Long> {

   // @Query("from Product p inner join fetch p.inventory r where r.retailerId = :id")

    @Query("from Address a inner join fetch a.user u where u.email = :email")
    List<Address> getUserAddressByEmail(String email);
}
