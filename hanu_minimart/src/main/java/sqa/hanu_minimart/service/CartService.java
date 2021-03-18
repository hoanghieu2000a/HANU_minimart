package sqa.hanu_minimart.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	public CartService() {
		
	}
	
	public List<Cart> getAllCart(){
		return cartRepository.findAll();
	} 
	public Cart addNewCart(Cart cart) {
		return cartRepository.save(cart);
	}
	public void deleteCart(Long id) {
		boolean exists = cartRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Cart does not exist!");
		}
		cartRepository.deleteById(id);
	}
	public Cart getCartById(Long id){
		boolean exists = cartRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Cart does not exist!");
		}
		return  cartRepository.findById(id).get();
	}
	@Transactional
	public void update(Long id,Set<CartItem> cartItem) {
		Cart cart = cartRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Cart does not exist!"));
		cart.setCartItem(cartItem);
		cartRepository.save(cart);
	}	
}
