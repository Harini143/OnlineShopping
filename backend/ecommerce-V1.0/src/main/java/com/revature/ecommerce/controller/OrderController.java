package com.revature.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.dto.EcomerceMessage;
import com.revature.ecommerce.entity.Cart;
import com.revature.ecommerce.entity.ProductOrder;
import com.revature.ecommerce.exceptions.NoOrderException;
import com.revature.ecommerce.service.OrderService;
import com.revature.ecommerce.util.EcommerceConstants;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {

	@Autowired
	 private OrderService orderService;
	
	@GetMapping("/viewOrder/{userId}")
	public List<ProductOrder> viewOrder(@PathVariable("userId") String userId) throws NoOrderException {
		return orderService.viewOrder(userId);
	}
	
	@GetMapping("/viewProductsInOrder/{orderId}")
	public List<Cart> viewProductsInOrder(@PathVariable("orderId") String orderId) {
		return orderService.viewProductsInOrder(orderId);
	}

	@PutMapping("/cancelOrder/{orderid}")
	public EcomerceMessage cancelOrder(@PathVariable("orderid") String orderId) throws NoOrderException {
		orderService.cancelOrder(orderId);
		return new EcomerceMessage(EcommerceConstants.ORDER_CANCELLED);
	}

}
