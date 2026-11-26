package com.foodshare.dao;

import com.foodshare.model.FoodRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    /** Receiver requests a food post. Returns false if someone else already took it or it expired. */
    public boolean create(int foodId, int receiverId) throws SQLException {
        Connection c = DBConnection.getConnection();
        try {
            c.setAutoCommit(false);
            int claimed;
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE food_posts SET status='REQUESTED' WHERE id=? AND status='AVAILABLE' AND expiry_time > NOW()")) {
                ps.setInt(1, foodId);
                claimed = ps.executeUpdate();
            }
            if (claimed == 0) {
                c.rollback();
                return false;
            }
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO requests(food_id,receiver_id) VALUES(?,?)")) {
                ps.setInt(1, foodId);
                ps.setInt(2, receiverId);
                ps.executeUpdate();
            }
            c.commit();
            return true;
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            c.close();
        }
    }

    /** Donor accepts or rejects a pending request. Reject puts the food back to AVAILABLE. */
    public boolean respond(int requestId, int donorId, boolean accept) throws SQLException {
        String sql = "UPDATE requests r JOIN food_posts f ON f.id=r.food_id "
                + "SET r.status=?, f.status=? WHERE r.id=? AND f.donor_id=? AND r.status='PENDING'";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, accept ? "ACCEPTED" : "REJECTED");
            ps.setString(2, accept ? "REQUESTED" : "AVAILABLE");
            ps.setInt(3, requestId);
            ps.setInt(4, donorId);
            return ps.executeUpdate() > 0;
        }
    }

    /** Donor marks an accepted request as picked up. */
    public boolean markPickedUp(int requestId, int donorId) throws SQLException {
        String sql = "UPDATE requests r JOIN food_posts f ON f.id=r.food_id "
                + "SET f.status='PICKED_UP' WHERE r.id=? AND f.donor_id=? AND r.status='ACCEPTED'";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            ps.setInt(2, donorId);
            return ps.executeUpdate() > 0;
        }
    }

    public List<FoodRequest> listForDonor(int donorId) throws SQLException {
        return query("WHERE f.donor_id=?", donorId);
    }

    public List<FoodRequest> listByReceiver(int receiverId) throws SQLException {
        return query("WHERE r.receiver_id=?", receiverId);
    }

    private List<FoodRequest> query(String where, int id) throws SQLException {
        String sql = "SELECT r.*, f.food_name, f.quantity, f.location, f.expiry_time, f.status AS food_status, "
                + "d.name AS donor_name, d.phone AS donor_phone, v.name AS receiver_name, v.phone AS receiver_phone "
                + "FROM requests r JOIN food_posts f ON f.id=r.food_id "
                + "JOIN users d ON d.id=f.donor_id JOIN users v ON v.id=r.receiver_id "
                + where + " ORDER BY r.created_at DESC";
        List<FoodRequest> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FoodRequest q = new FoodRequest();
                    q.setId(rs.getInt("id"));
                    q.setFoodId(rs.getInt("food_id"));
                    q.setReceiverId(rs.getInt("receiver_id"));
                    q.setStatus(rs.getString("status"));
                    q.setCreatedAt(rs.getTimestamp("created_at"));
                    q.setFoodName(rs.getString("food_name"));
                    q.setQuantity(rs.getString("quantity"));
                    q.setLocation(rs.getString("location"));
                    q.setExpiryTime(rs.getTimestamp("expiry_time"));
                    q.setFoodStatus(rs.getString("food_status"));
                    q.setDonorName(rs.getString("donor_name"));
                    q.setDonorPhone(rs.getString("donor_phone"));
                    q.setReceiverName(rs.getString("receiver_name"));
                    q.setReceiverPhone(rs.getString("receiver_phone"));
                    list.add(q);
                }
            }
        }
        return list;
    }
}
