package com.shopping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
	private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void removeFromCart(int productId, int quantity) {
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                if (quantity > item.getQuantity()) {
                    System.out.println(" Cannot remove " + quantity + 
                                       ". You only have " + item.getQuantity() + " in cart.");
                } else if (quantity == item.getQuantity()) {
                    iterator.remove(); // remove entire product
                    System.out.println("Removed " + item.getProduct().getName() + 
                                       " completely from cart.");
                } else {
                    // reduce quantity
                    item.setQuantity(item.getQuantity() - quantity);
                    System.out.println(" Removed " + quantity + " of " + 
                                       item.getProduct().getName());
                }
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }


    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println(" Cart is empty.");
            return;
        }
        double total = 0;
        for (CartItem item : cartItems) {
            System.out.println(item);
            total += item.getTotalPrice();
        }
        System.out.println("Total Amount = ₹" + total);
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout!");
            return;
        }
        double total = 0;
        System.out.println("\n===== BILL RECEIPT =====");
        for (CartItem item : cartItems) {
            System.out.println(item);
            total += item.getTotalPrice();
        }

        System.out.println("-------------------------");
        System.out.println("SUBTOTAL: ₹" + total);

        // Step 1: Check if discount is available
        double discount = 0;
        String discountMsg = "No discount available.";

        if (total >= 50000) {
            discount = total * 0.10; // 10% off
            discountMsg = "Congratulations! You got a 10% discount.";
        } else if (total >= 10000) {
            discount = total * 0.05; // 5% off
            discountMsg = "Great! You got a 5% discount.";
        }

        // Step 2: Show discount information
        System.out.println(discountMsg);

        // Step 3: Apply discount only if > 0
        if (discount > 0) {
            System.out.println("DISCOUNT: -₹" + discount);
        }

        double finalAmount = total - discount;
        System.out.println("TOTAL BILL: ₹" + finalAmount);
        System.out.println("Thank you for shopping! ");

        cartItems.clear(); // empty cart after checkout
    }

}


