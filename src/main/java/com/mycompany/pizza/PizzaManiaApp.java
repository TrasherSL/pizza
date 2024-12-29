package com.mycompany.pizza;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application class for Pizza Mania.
 * Handles user interactions for pizza creation, ordering, and management.
 */
public class PizzaManiaApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepOrdering = true;

        List<Pizza> favoritePizzas = new ArrayList<>();
        List<Pizza> orderHistory = new ArrayList<>();

        while (keepOrdering) {
            System.out.println("\n======================================");
            System.out.println("        WELCOME TO PIZZA MANIA ");
            System.out.println("======================================");
            System.out.println("1. Create User");
            System.out.println("2. Create Custom Pizza");
            System.out.println("3. Save Pizza as Favorite");
            System.out.println("4. Place Order");
            System.out.println("5. Update Order Status");
            System.out.println("6. View Orders");
            System.out.println("7. View Favorite Pizzas");
            System.out.println("8. View Loyalty Points");
            System.out.println("9. View Seasonal Offers");
            System.out.println("10. Rate Pizza");
            System.out.println("11. Exit");

            System.out.print("Enter your choice (1-11): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    Pizza pizza = createCustomPizza(scanner);
                    orderHistory.add(pizza);
                    break;
                case 3:
                    savePizzaAsFavorite(scanner, favoritePizzas, orderHistory);
                    break;
                case 4:
                    placeOrder(scanner, orderHistory);
                    break;
                case 5:
                    updateOrderStatus(scanner, orderHistory); // Updated call
                    break;
                case 6:
                    viewOrders(orderHistory);
                    break;
                case 7:
                    viewFavoritePizzas(favoritePizzas);
                    break;
                case 8:
                    viewLoyaltyPoints();
                    break;
                case 9:
                    viewSeasonalOffers();
                    break;
                case 10:
                    ratePizza(scanner);
                    break;
                case 11:
                    System.out.println("Thank you for visiting Pizza Mania! Goodbye!");
                    keepOrdering = false;
                    break;
                default:
                    System.out.println(" Invalid choice! Please select between 1 and 11.");
            }
        }

        scanner.close();
    }

    // Create a User
    private static void createUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        System.out.println("\nUser created successfully! Welcome, " + name + ".");
    }

    // Create a Custom Pizza with Toppings Selection
    private static Pizza createCustomPizza(Scanner scanner) {
        System.out.print("Enter Pizza Name: ");
        String name = scanner.nextLine();

        System.out.println("Choose a Crust: 1. Thin Crust 2. Thick Crust");
        String crust = scanner.nextLine();

        System.out.println("Choose a Sauce: 1. Tomato Sauce 2. BBQ Sauce");
        String sauce = scanner.nextLine();

        System.out.println("Choose a Cheese: 1. Mozzarella 2. Cheddar");
        String cheese = scanner.nextLine();

        // Toppings Selection
        System.out.println("Choose toppings (you can select multiple by entering their numbers, separated by commas):");
        System.out.println("1. Pepperoni");
        System.out.println("2. Mushrooms");
        System.out.println("3. Olives");
        System.out.println("4. Green Peppers");
        System.out.print("Enter your topping choices: ");
        String toppingsInput = scanner.nextLine();

        List<String> toppingsList = new ArrayList<>();
        if (!toppingsInput.isEmpty()) {
            String[] selectedToppings = toppingsInput.split(",");
            for (String topping : selectedToppings) {
                switch (topping.trim()) {
                    case "1":
                        toppingsList.add("Pepperoni");
                        break;
                    case "2":
                        toppingsList.add("Mushrooms");
                        break;
                    case "3":
                        toppingsList.add("Olives");
                        break;
                    case "4":
                        toppingsList.add("Green Peppers");
                        break;
                    default:
                        System.out.println("⚠️ Invalid topping choice: " + topping.trim());
                }
            }
        }

        // Order Type Validation (Delivery/Pickup)
        String orderType = "";
        while (true) {
            System.out.println("Choose Order Type: 1. Delivery 2. Pickup");
            String orderChoice = scanner.nextLine();
            if ("1".equals(orderChoice)) {
                orderType = "Delivery";
                break;
            } else if ("2".equals(orderChoice)) {
                orderType = "Pickup";
                break;
            } else {
                System.out.println("⚠️ Invalid order type, please try again.");
            }
        }

        // Payment Method Validation
        String paymentMethod = "";
        while (true) {
            System.out.println("Choose Payment Method: 1. Credit Card 2. Cash on Delivery");
            String paymentChoice = scanner.nextLine();
            if ("1".equals(paymentChoice)) {
                paymentMethod = "Credit Card";
                break;
            } else if ("2".equals(paymentChoice)) {
                paymentMethod = "Cash on Delivery";
                break;
            } else {
                System.out.println("⚠️ Invalid payment method, please try again.");
            }
        }

        // Address Information
        System.out.print("Enter your street address: ");
        String streetAddress = scanner.nextLine();

        System.out.print("Enter your city: ");
        String city = scanner.nextLine();

        System.out.print("Enter your province: ");
        String province = scanner.nextLine();

        // Create Pizza
        Pizza pizza = new Pizza.PizzaBuilder()
                .setName(name)
                .setCrust(crust)
                .setSauce(sauce)
                .setCheese(cheese)
                .setToppings(String.join(", ", toppingsList))
                .setOrderType(orderType)
                .setPaymentMethod(paymentMethod)
                .setStreetAddress(streetAddress)
                .setCity(city)
                .setProvince(province)
                .build();

        pizza.displayPizzaDetails();
        return pizza;
    }

    // Save Pizza as Favorite
    private static void savePizzaAsFavorite(Scanner scanner, List<Pizza> favoritePizzas, List<Pizza> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("⚠️ No pizzas in the order history.");
            return;
        }

        System.out.println("Choose a pizza from the order history to save as a favorite: ");
        for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println(i + 1 + ". " + orderHistory.get(i).getName());
        }

        int favoriteIndex = scanner.nextInt() - 1;
        if (favoriteIndex >= 0 && favoriteIndex < orderHistory.size()) {
            favoritePizzas.add(orderHistory.get(favoriteIndex));
            System.out.println("Pizza added to your favorites.");
        } else {
            System.out.println("⚠️ Invalid choice.");
        }
    }

    // Place Order (Store to history)
    private static void placeOrder(Scanner scanner, List<Pizza> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("⚠️ You have no pizzas to order.");
            return;
        }

        System.out.println("Choose a pizza to place an order: ");
        for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println(i + 1 + ". " + orderHistory.get(i).getName());
        }

        int orderIndex = scanner.nextInt() - 1;
        if (orderIndex >= 0 && orderIndex < orderHistory.size()) {
            Pizza pizza = orderHistory.get(orderIndex);
            pizza.displayPizzaDetails();
            System.out.println("Your order is placed successfully!");
        } else {
            System.out.println("⚠️ Invalid pizza choice.");
        }
    }

    // Update Order Status
    private static void updateOrderStatus(Scanner scanner, List<Pizza> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("⚠️ No orders to update.");
            return;
        }

        System.out.println("Choose a pizza to update its status: ");
        for (int i = 0; i < orderHistory.size(); i++) {
            System.out.println(i + 1 + ". " + orderHistory.get(i).getName());
        }

        int orderIndex = scanner.nextInt() - 1;
        if (orderIndex >= 0 && orderIndex < orderHistory.size()) {
            Pizza pizza = orderHistory.get(orderIndex);
            System.out.println("Current status: " + pizza.getOrderStatus().getStatus());

            System.out.println("Enter new status: ");
            scanner.nextLine(); // consume the newline
            String newStatus = scanner.nextLine();
            pizza.getOrderStatus().setStatus(newStatus);
            System.out.println("Order status updated successfully.");
        } else {
            System.out.println(" Invalid pizza choice.");
        }
    }

    // View Orders
    private static void viewOrders(List<Pizza> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println(" No orders to display.");
            return;
        }
        System.out.println("\nOrder History:");
        for (Pizza pizza : orderHistory) {
            pizza.displayPizzaDetails();
        }
    }

    // View Favorite Pizzas
    private static void viewFavoritePizzas(List<Pizza> favoritePizzas) {
        if (favoritePizzas.isEmpty()) {
            System.out.println("⚠️ No favorite pizzas.");
            return;
        }
        System.out.println("\nFavorite Pizzas:");
        for (Pizza pizza : favoritePizzas) {
            pizza.displayPizzaDetails();
        }
    }

    // View Loyalty Points (Simulated as an example)
    private static void viewLoyaltyPoints() {
        System.out.println("You have 100 loyalty points!");
    }

    // View Seasonal Offers (Simulated as an example)
    private static void viewSeasonalOffers() {
        System.out.println("Seasonal Offer: 20% off on all pizzas!");
    }

    // Rate Pizza (Simulated as an example)
    private static void ratePizza(Scanner scanner) {
        System.out.println("Rate your pizza from 1 to 5: ");
        int rating = scanner.nextInt();
        if (rating >= 1 && rating <= 5) {
            System.out.println("Thank you for your rating of " + rating + " stars.");
        } else {
            System.out.println("⚠️ Invalid rating.");
        }
    }
}
