package com.foodshare.servlet;

import com.foodshare.dao.FoodDAO;
import com.foodshare.model.FoodPost;
import com.foodshare.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/add-food")
public class AddFoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Auth.require(req, resp, "DONOR")) return;
        req.getRequestDispatcher("/add-food.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Auth.require(req, resp, "DONOR")) return;
        User u = Auth.user(req);

        FoodPost p = new FoodPost();
        p.setDonorId(u.getId());
        p.setFoodName(req.getParameter("foodName").trim());
        p.setQuantity(req.getParameter("quantity").trim());
        p.setLocation(req.getParameter("location").trim());

        String error = null;
        try {
            // datetime-local sends "2026-11-21T20:30"
            String raw = req.getParameter("expiry").replace('T', ' ') + ":00";
            p.setExpiryTime(Timestamp.valueOf(raw));
            if (p.getExpiryTime().before(new Timestamp(System.currentTimeMillis()))) {
                error = "Expiry time must be in the future.";
            }
        } catch (IllegalArgumentException e) {
            error = "Please enter a valid expiry date and time.";
        }
        if (error == null && (p.getFoodName().isEmpty() || p.getQuantity().isEmpty() || p.getLocation().isEmpty())) {
            error = "Please fill in all fields.";
        }

        if (error != null) {
            req.setAttribute("error", error);
            req.setAttribute("form", p);
            req.getRequestDispatcher("/add-food.jsp").forward(req, resp);
            return;
        }
        try {
            new FoodDAO().add(p);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard?msg=Food+posted.+Receivers+can+now+see+it.");
    }
}
