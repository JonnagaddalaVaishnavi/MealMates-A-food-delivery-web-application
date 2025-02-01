package com.myfoods.utility;

import java.util.HashMap;
import java.util.Map;

import com.myfoods.model.CartItem;

public class Cart {
    private Map<Integer, CartItem> items;

    // Constructor
    public Cart() {
        this.items = new HashMap<>();
    }

    // Add item to cart
    public void addItem(CartItem item) {
        int itemId = item.getId();
        if(items.containsKey(itemId)) {
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.put(itemId, item);
        }
    }

    // Update item quantity in cart
    public void updateItem(int itemId, int qty) {
        if (items.containsKey(itemId)) {
            if (qty <= 0) {
                items.remove(itemId);
            } else {
                CartItem id = items.get(itemId);
                items.get(itemId).setQuantity(qty);
            }
        }
    }

    // Remove item from cart
    public void removeItem(int itemId) {
        items.remove(itemId);
    }
    
    // Clear cart
    public void clear() {
        items.clear();
    }

    // Get items in cart
    public Map<Integer, CartItem> getItems() {
        return items;
    }
}
