package com.revature.ecommerce.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.ecommerce.entity.Address;
import com.revature.ecommerce.entity.UserEntity;
import com.revature.ecommerce.exception.UserServiceException;
import com.revature.ecommerce.model.AddressDto;
import com.revature.ecommerce.repository.AddressJpaRepository;
import com.revature.ecommerce.repository.UserRepository;
import com.revature.ecommerce.utils.AppConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressJpaRepository addressJpaRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserEntity createUser(UserEntity user) throws UserServiceException {
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new UserServiceException(AppConstants.USER_ALREADY_EXISTS);
		return userRepository.save(user);
	}

	@Override
	public UserEntity getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		return userRepository.findById(email).orElseThrow(()-> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND));
	}

	@Override
	public UserEntity updateUser(String userId, UserEntity user) {
		return null;
	}

	@Override
	public void deleteUser(String userId) {

	}

	@Override
	public List<UserEntity> getUsers() {
		List<UserEntity> userEntities = new ArrayList<>();
		userRepository.findAll().forEach(userEntities::add);
		return userEntities;
	}

	@Override
	public AddressDto saveAddress(Address address) {
		AddressDto returnValue = new AddressDto();
		BeanUtils.copyProperties(addressJpaRepository.save(address), returnValue);
		return returnValue;
	}

	@Override
	public List<AddressDto> getUserAllAddress(String email) {
		List<AddressDto> returnList = new ArrayList<>();
		List<Address> addresses = addressJpaRepository.getUserAddressByEmail(email);
		addresses.forEach(address -> {
			AddressDto addressDto = new AddressDto();
			BeanUtils.copyProperties(address, addressDto);
			returnList.add(addressDto);
		});
		return returnList;
	}

	@Override
	public AddressDto updateAddress(Address address) {
		AddressDto returnValue = new AddressDto();
		BeanUtils.copyProperties(addressJpaRepository.save(address), returnValue);
		return returnValue;
	}

	@Override
	public void deleteAddress(Long addressId) {
		addressJpaRepository.deleteById(addressId);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		List<GrantedAuthority> authorities = Arrays.stream(userEntity.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), authorities);
	}
}
