package com.foodshare.dao;

import com.foodshare.model.FoodPost;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    public void add(FoodPost p) throws SQLException {
        String sql = "INSERT INTO food_posts(donor_id,food_name,quantity,location,expiry_time) VALUES(?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, p.getDonorId());
            ps.setString(2, p.getFoodName());
            ps.setString(3, p.getQuantity());
            ps.setString(4, p.getLocation());
            ps.setTimestamp(5, p.getExpiryTime());
            ps.executeUpdate();
        }
    }

    /** Food that receivers can still request (not expired). */
    public List<FoodPost> listAvailable() throws SQLException {
        return query("SELECT f.*, u.name AS donor_name, u.phone AS donor_phone FROM food_posts f "
                + "JOIN users u ON u.id=f.donor_id WHERE f.status='AVAILABLE' AND f.expiry_time > NOW() "
                + "ORDER BY f.expiry_time", 0);
    }

    public List<FoodPost> listByDonor(int donorId) throws SQLException {
        return query("SELECT f.*, u.name AS donor_name, u.phone AS donor_phone FROM food_posts f "
                + "JOIN users u ON u.id=f.donor_id WHERE f.donor_id=? ORDER BY f.created_at DESC", donorId);
    }

    private List<FoodPost> query(String sql, int param) throws SQLException {
        List<FoodPost> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            if (param > 0) ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FoodPost p = new FoodPost();
                    p.setId(rs.getInt("id"));
                    p.setDonorId(rs.getInt("donor_id"));
                    p.setFoodName(rs.getString("food_name"));
                    p.setQuantity(rs.getString("quantity"));
                    p.setLocation(rs.getString("location"));
                    p.setExpiryTime(rs.getTimestamp("expiry_time"));
                    p.setStatus(rs.getString("status"));
                    p.setCreatedAt(rs.getTimestamp("created_at"));
                    p.setDonorName(rs.getString("donor_name"));
                    p.setDonorPhone(rs.getString("donor_phone"));
                    p.setExpired("AVAILABLE".equals(p.getStatus())
                            && p.getExpiryTime().before(new Timestamp(System.currentTimeMillis())));
                    list.add(p);
                }
            }
        }
        return list;
    }
}
