package com.revature.ecommerce.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ecommerce.dao.EcommerceDao;
import com.revature.ecommerce.entity.*;
import com.revature.ecommerce.exceptions.NoCartException;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.util.EcommerceConstants;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	EcommerceDao dao;

	@Autowired
	private ProductsSearchService searchService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean addCart(int productId, String userID) {
		Product prod = dao.getProduct(productId);
		UserEntity user = dao.getUser(userID);
		Cart cart = new Cart();
		cart.setProduct(prod);
		cart.setUser(user);
		cart.setCartStatus(EcommerceConstants.ACTIVE);
		return dao.addCart(cart);

	}

	@Override
	public Cart viewCart(int cartID) throws NoCartException {
		Cart cart = dao.viewCart(cartID);
		if (cart == null)
			throw new NoCartException(EcommerceConstants.NO_ITEM_IN_CART);
		return cart;
	}

	@Override
	public boolean removeCart(long cartID) throws NoCartException {
		Cart cart = dao.viewCart(cartID);
		if (cart == null)
			throw new NoCartException(EcommerceConstants.NO_ITEM_IN_CART);
		return dao.removeCart(cart);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean addOrder(String userId, Address address) throws NoCartException, NoProductException {
		ProductOrder order = new ProductOrder();
		List<Cart> cartList = viewCart(userId);
		UserEntity user = dao.getUser(userId);
		String orderId = "" + LocalDate.now().getYear() + LocalDate.now().getMonthValue()
				+ LocalDate.now().getDayOfMonth() + userId + (dao.countRowsInOrder(userId) + 1);
		order.setOrderId(orderId);
		order.setOrderDate(LocalDate.now());
		order.setAddress(address);
		order.setStatus(EcommerceConstants.CONFIRMED);
		order.setUser(user);
		dao.addOrder(order);
		for(Cart cart: cartList){
			cart.setOrder(order);
			if(cart.getProduct().getQuantity() <= EcommerceConstants.NO_STOCK)
				throw new NoProductException(EcommerceConstants.OUT_OF_STOCK);
			cart.getProduct().setQuantity(cart.getProduct().getQuantity()-1);
		    dao.editProduct(cart.getProduct());
		    cart.setCartStatus(EcommerceConstants.IN_ACTIVE);
		    dao.editCart(cart);
		}

		 return true;
	}

	@Override
	public List<Cart> viewCart(String userID) throws NoCartException {
		List<Cart> lst = dao.viewProductsInCart(userID).stream().filter(cart -> cart.getCartStatus() == EcommerceConstants.ACTIVE)
				.collect(Collectors.toList());
		lst.forEach(c->c.getProduct().setOverAllRating(searchService.getOverAllRating(c.getProduct().getProductId())));
		if (lst.isEmpty())
			throw new NoCartException(EcommerceConstants.NO_ITEM_IN_CART);
		return lst;
	}

}
