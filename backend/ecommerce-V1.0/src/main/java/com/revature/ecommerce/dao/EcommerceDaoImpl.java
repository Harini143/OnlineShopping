package com.revature.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.revature.ecommerce.entity.Cart;
import com.revature.ecommerce.entity.Category;
import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ProductOrder;
import com.revature.ecommerce.entity.ProductSpecification;
import com.revature.ecommerce.entity.ReviewRating;
import com.revature.ecommerce.entity.UserEntity;

@Repository
public class EcommerceDaoImpl implements EcommerceDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Category> getCategories() {
		String jpql="from Category c";
		TypedQuery<Category> query = em.createQuery(jpql, Category.class);
		return query.getResultList();
	}

	@Override
	public List<Product> getProducts(int catId) {
		String jpql="from Product p inner join fetch p.category  c where c.categoryId=:catid";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		query.setParameter("catid", catId);
		return query.getResultList();
	}

	@Override
	public List<ProductSpecification> getProductSpecifications(int productId) {
		String jpql="from ProductSpecification ps inner join fetch ps.product p where p.productId=:prodid";
		TypedQuery<ProductSpecification> query = em.createQuery(jpql, ProductSpecification.class);
		query.setParameter("prodid", productId);
		return query.getResultList();
	}

	@Override
	public List<Product> getProducts(String searchStr) {
		String jpql="from Product p where lower(p.productName) like lower(:str) or lower(p.productInfo) like lower(:str)";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		query.setParameter("str", "%" +searchStr+ "%");
		return query.getResultList();
	}

	@Override
	public List<ReviewRating> getProductReviews(int prodId) {
		String jpql="from ReviewRating r inner join fetch r.product p where p.productId=:prodid";
		TypedQuery<ReviewRating> query = em.createQuery(jpql, ReviewRating.class);
		query.setParameter("prodid", prodId);
		return query.getResultList();
	}

	@Override
	public boolean addReviewRating(ReviewRating reviewRating) {
		em.persist(reviewRating);
		return true;
	}

	@Override
	public boolean addCart(Cart cart) {
		em.persist(cart);
		return true;
	}
	@Override
	public Cart viewCart(long cartId) {
		
		return em.find(Cart.class, cartId);
	}
	@Override
	public boolean removeCart(Cart cart) {
		em.remove(cart);
		return true;
	}
	
	@Override
	public boolean addOrder(ProductOrder order) {
		em.persist(order);
		return true;
	}

	@Override
	public List<ProductOrder> viewOrderByUser(String userId) {
		String jpql="from ProductOrder po inner join fetch po.user u where u.email=:userid";
		TypedQuery<ProductOrder> query = em.createQuery(jpql, ProductOrder.class);
		query.setParameter("userid", userId);
		return query.getResultList();
	}

	@Override
	public List<Cart> viewProductsInCart(String userId) {
		String jpql="from Cart c inner join fetch c.product p inner join fetch c.user u where u.email=:userId";
		TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
		query.setParameter("userId",userId);
		return query.getResultList();
	}

	@Override
	public List<Cart> viewProductsInOrder(String orderId) {
		String jpql="from Cart c inner join fetch c.order o inner join fetch c.product p inner join fetch c.user u  where o.orderId=:oid";
		TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
		query.setParameter("oid", orderId);
		return query.getResultList();
	}
	@Override
	public boolean cancelOrder(ProductOrder order) {
		em.remove(order);
		return true;
	}

	@Override
	public ProductOrder viewOrder(String orderId) {
		
		return em.find(ProductOrder.class, orderId);
	}

	@Override
	public boolean removeCart(int orderId) {
		String jpql ="delete from Cart cp where c.order.orderId=:orderid";
		
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		query.setParameter("orderid", orderId);
		int res =query.executeUpdate();
		if(res > 0)
		return true;
		else
			return false;
	}

	@Override
	public UserEntity getUser(String userID) {
		
		return em.find(UserEntity.class, userID);
	}

	@Override
	public Product getProduct(int productId) {
		return em.find(Product.class,productId);
	}

	@Override
	public Long countRowsInOrder(String userId) {
		String jpql="select count(po.orderId) from ProductOrder po join po.user u where u.email=:userid";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("userid", userId);
		return query.getSingleResult();
	}

	@Override
	public boolean editProduct(Product prod) {
		em.merge(prod);
		return true;
	}

	@Override
	public boolean editCart(Cart cart) {
		em.merge(cart);
		return true;
	}

	@Override
	public ProductOrder viewProductOrder(String orderID) {
		
		return em.find(ProductOrder.class, orderID);
	}

	@Override
	public boolean editOrder(ProductOrder order) {
		em.merge(order);
		return true;
	}

}
