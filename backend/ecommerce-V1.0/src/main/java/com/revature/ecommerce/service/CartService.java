package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.Address;
import com.revature.ecommerce.entity.Cart;
import com.revature.ecommerce.entity.ProductOrder;
import com.revature.ecommerce.exceptions.NoCartException;
import com.revature.ecommerce.exceptions.NoProductException;

public interface CartService {

	public boolean addCart(int productId, String userId);
	public Cart viewCart(int cartID)throws NoCartException;
	public List<Cart> viewCart(String userId)throws NoCartException;
	public boolean removeCart(long cartId)throws NoCartException;
	
	public boolean addOrder(String userID, Address address)throws NoCartException, NoProductException;
	
	
}
