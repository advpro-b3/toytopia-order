package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {
    private final String CART_API_BASE_URL = "https://toytopia-cart-production.up.railway.app/api/cart/data/";

    private final RestTemplate restTemplate;

    public CartService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Cart getCartById(String cartId) {
        String url = CART_API_BASE_URL + cartId;
        try {
            return restTemplate.getForObject(url, Cart.class);
        } catch (Exception e) {
            // Handle exceptions appropriately
            return null;
        }
    }
}
