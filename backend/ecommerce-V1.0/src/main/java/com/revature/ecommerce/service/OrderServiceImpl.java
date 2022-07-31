package com.revature.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ecommerce.dao.EcommerceDao;
import com.revature.ecommerce.entity.Cart;
import com.revature.ecommerce.entity.ProductOrder;
import com.revature.ecommerce.exceptions.NoCartException;
import com.revature.ecommerce.exceptions.NoOrderException;
import com.revature.ecommerce.util.EcommerceConstants;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private EcommerceDao dao;
	

	@Override
	public List<ProductOrder> viewOrder(String userId) throws NoOrderException {
		List<ProductOrder> orderList =  dao.viewOrderByUser(userId);
		if(orderList.isEmpty())
			throw new NoOrderException(EcommerceConstants.NO_ORDER);
		orderList.sort((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()));
		return orderList;
	}

	@Override
	public List<Cart> viewCart(String userId) throws NoCartException {
		List<Cart> lst = dao.viewProductsInCart(userId).stream().filter(cart -> cart.getCartStatus() == EcommerceConstants.IN_ACTIVE)
				.collect(Collectors.toList());
		if (lst.isEmpty())
			throw new NoCartException(EcommerceConstants.NO_ITEM_IN_CART);
		return lst;
	}

	@Override
	public boolean cancelOrder(String orderId) {
		ProductOrder order = dao.viewOrder(orderId);
		order.setStatus(EcommerceConstants.CANCELLED);
		List<Cart> plist = dao.viewProductsInOrder(orderId);
		for(Cart cart: plist) {
			cart.getProduct().setQuantity(cart.getProduct().getQuantity()+1);
			dao.editCart(cart);
		}
		dao.editOrder(order);
		return true;
	}

	@Override
	public List<Cart> viewProductsInOrder(String orderID) {
		return dao.viewProductsInOrder(orderID);
	}

}
