package com.foodshare.model;

public class FoodRequest {
    private int id;
    private int foodId;
    private int receiverId;
    private String status;
    private java.sql.Timestamp createdAt;
    private String foodName;
    private String quantity;
    private String location;
    private java.sql.Timestamp expiryTime;
    private String foodStatus;
    private String donorName;
    private String donorPhone;
    private String receiverName;
    private String receiverPhone;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getFoodId() { return foodId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public java.sql.Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public java.sql.Timestamp getExpiryTime() { return expiryTime; }
    public void setExpiryTime(java.sql.Timestamp expiryTime) { this.expiryTime = expiryTime; }
    public String getFoodStatus() { return foodStatus; }
    public void setFoodStatus(String foodStatus) { this.foodStatus = foodStatus; }
    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }
    public String getDonorPhone() { return donorPhone; }
    public void setDonorPhone(String donorPhone) { this.donorPhone = donorPhone; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
}
