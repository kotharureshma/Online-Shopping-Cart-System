package com.shopping;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class OnlineShoppingSystem {
	  
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined Products
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product(1, "Laptop", 55000));
        catalog.add(new Product(2, "Smartphone", 22000));
        catalog.add(new Product(3, "Headphones", 2000));
        catalog.add(new Product(4, "Smart Watch", 4000));
        catalog.add(new Product(5, "Keyboard", 1000));

        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n==================== ONLINE SHOPPING CART=================");
            System.out.println("1. View Product Catalog");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Remove Item from Cart");
            System.out.println("4. View Cart Summary");
            System.out.println("5. Checkout & Generate Bill");
            System.out.println("6. Search Product by Name");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nProduct Catalog:");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID to add: ");
                    int pid = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    Product selected = null;
                    for (Product p : catalog) {
                        if (p.getId() == pid) {
                            selected = p;
                            break;
                        }
                    }
                    if (selected != null) {
                        cart.addToCart(selected, qty);
                        System.out.println(" Added to cart.");
                    } else {
                        System.out.println(" Invalid Product ID.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID to remove: ");
                    int rid = sc.nextInt();
                    System.out.print("Enter quantity to remove: ");
                    int removeQty = sc.nextInt();
                    cart.removeFromCart(rid, removeQty);
                    break;

                case 4:
                    System.out.println("\n Cart Summary:");
                    cart.viewCart();
                    break;

                case 5:
                    cart.checkout();
                    break;

                case 6:
                    System.out.print("Enter product name to search: ");
                    String name = sc.nextLine().toLowerCase();
                    boolean found = false;
                    for (Product p : catalog) {
                        if (p.getName().toLowerCase().contains(name)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println(" No product found with that name.");
                    }
                    break;

                case 7:
                    System.out.println(" Exiting Shopping Cart. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
