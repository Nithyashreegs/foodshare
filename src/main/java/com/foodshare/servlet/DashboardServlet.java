package com.foodshare.servlet;

import com.foodshare.dao.FoodDAO;
import com.foodshare.dao.RequestDAO;
import com.foodshare.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Auth.require(req, resp, null)) return;
        User u = Auth.user(req);
        try {
            FoodDAO foodDAO = new FoodDAO();
            RequestDAO requestDAO = new RequestDAO();
            if ("DONOR".equals(u.getRole())) {
                req.setAttribute("myPosts", foodDAO.listByDonor(u.getId()));
                req.setAttribute("incoming", requestDAO.listForDonor(u.getId()));
            } else {
                req.setAttribute("available", foodDAO.listAvailable());
                req.setAttribute("myRequests", requestDAO.listByReceiver(u.getId()));
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
