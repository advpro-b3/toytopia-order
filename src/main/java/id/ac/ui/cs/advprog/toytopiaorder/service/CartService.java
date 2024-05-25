package id.ac.ui.cs.advprog.toytopiaorder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CartService {
    private final String CART_API_BASE_URL = "https://toytopia-cart-production.up.railway.app/api/cart/data/";

    private final RestTemplate restTemplate;

    public CartService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getCartById(String cartId) {
        String url = CART_API_BASE_URL + cartId;
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            // Handle exceptions appropriately
            return null;
        }
    }
}
