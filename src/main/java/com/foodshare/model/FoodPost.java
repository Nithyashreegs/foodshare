package com.foodshare.model;

public class FoodPost {
    private int id;
    private int donorId;
    private String foodName;
    private String quantity;
    private String location;
    private java.sql.Timestamp expiryTime;
    private String status;
    private java.sql.Timestamp createdAt;
    private String donorName;
    private String donorPhone;
    private boolean expired;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDonorId() { return donorId; }
    public void setDonorId(int donorId) { this.donorId = donorId; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public java.sql.Timestamp getExpiryTime() { return expiryTime; }
    public void setExpiryTime(java.sql.Timestamp expiryTime) { this.expiryTime = expiryTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public java.sql.Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }
    public String getDonorPhone() { return donorPhone; }
    public void setDonorPhone(String donorPhone) { this.donorPhone = donorPhone; }
    public boolean isExpired() { return expired; }
    public void setExpired(boolean expired) { this.expired = expired; }
}
