package com.mycompany.pizza;

/**
 * This class handles the order status.
 */
public class OrderStatus {
    private String status;

    public OrderStatus(String initialStatus) {
        this.status = initialStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void printStatus() {
        System.out.println("Order Status: " + status);
    }
}
