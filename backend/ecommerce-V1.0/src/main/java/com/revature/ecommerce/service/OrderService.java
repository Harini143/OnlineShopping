package com.revature.ecommerce.service;

import java.util.List;

import com.revature.ecommerce.entity.Cart;
import com.revature.ecommerce.entity.ProductOrder;
import com.revature.ecommerce.exceptions.NoCartException;
import com.revature.ecommerce.exceptions.NoOrderException;

public interface OrderService {
	public List<ProductOrder> viewOrder(String Contact)throws NoOrderException;
	public List<Cart> viewCart(String userId)throws NoCartException;
	public boolean cancelOrder(String orderId)throws NoOrderException;
    public List<Cart> viewProductsInOrder(String orderID);


}
