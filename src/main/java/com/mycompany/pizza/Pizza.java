package com.mycompany.pizza;

import java.util.List;

public class Pizza {
    private String name;
    private String crust;
    private String sauce;
    private String cheese;
    private String toppings;
    private String orderType;
    private String paymentMethod;
    private String streetAddress;
    private String city;
    private String province;
    private OrderStatus orderStatus; // Add order status to the Pizza class

    // Private constructor to be used by the PizzaBuilder
    private Pizza(PizzaBuilder builder) {
        this.name = builder.name;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.toppings = builder.toppings;
        this.orderType = builder.orderType;
        this.paymentMethod = builder.paymentMethod;
        this.streetAddress = builder.streetAddress;
        this.city = builder.city;
        this.province = builder.province;
        this.orderStatus = new OrderStatus("Pending"); // Default status is Pending
    }

    // Method to display pizza details
    public void displayPizzaDetails() {
        System.out.println("\nPizza Details:");
        System.out.println("Name: " + name);
        System.out.println("Crust: " + crust);
        System.out.println("Sauce: " + sauce);
        System.out.println("Cheese: " + cheese);
        System.out.println("Toppings: " + toppings);
        System.out.println("Order Type: " + orderType);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Address: " + streetAddress + ", " + city + ", " + province);
        System.out.println("Order Status: " + orderStatus.getStatus()); // Display current status
    }

    public String getName() {
        return name;
    }

    // Method to get the OrderStatus object
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    // PizzaBuilder class to construct Pizza objects
    public static class PizzaBuilder {
        private String name;
        private String crust;
        private String sauce;
        private String cheese;
        private String toppings;
        private String orderType;
        private String paymentMethod;
        private String streetAddress;
        private String city;
        private String province;

        // Setter methods for each field
        public PizzaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder setToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        public PizzaBuilder setOrderType(String orderType) {
            this.orderType = orderType;
            return this;
        }

        public PizzaBuilder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PizzaBuilder setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public PizzaBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public PizzaBuilder setProvince(String province) {
            this.province = province;
            return this;
        }

        // Build method to return the final Pizza object
        public Pizza build() {
            if (name == null || crust == null || sauce == null || cheese == null || toppings == null || orderType == null || paymentMethod == null) {
                throw new IllegalStateException("All fields must be set before building the Pizza.");
            }
            return new Pizza(this);
        }
    }
}
