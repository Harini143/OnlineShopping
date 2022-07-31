package com.revature.ecommerce.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.revature.ecommerce.entity.Address;
import com.revature.ecommerce.entity.UserEntity;
import com.revature.ecommerce.exception.UserServiceException;
import com.revature.ecommerce.model.AddressDto;


public interface UserService extends UserDetailsService {
	UserEntity createUser(UserEntity user) throws UserServiceException;
	UserEntity getUser(String email);
	UserEntity getUserByEmail(String email);
	UserEntity updateUser(String userId, UserEntity user);
	void deleteUser(String userId);
	List<UserEntity> getUsers();
	AddressDto saveAddress(Address address);
	List<AddressDto> getUserAllAddress(String email);
	AddressDto updateAddress(Address address);
	void  deleteAddress(Long addressId);
}
