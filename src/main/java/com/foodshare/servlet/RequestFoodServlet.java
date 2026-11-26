package com.foodshare.servlet;

import com.foodshare.dao.RequestDAO;
import com.foodshare.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/request-food")
public class RequestFoodServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Auth.require(req, resp, "RECEIVER")) return;
        User u = Auth.user(req);
        String msg;
        try {
            int foodId = Integer.parseInt(req.getParameter("foodId"));
            boolean ok = new RequestDAO().create(foodId, u.getId());
            msg = ok ? "Request sent. The donor will confirm shortly." : "Sorry, this food is no longer available.";
        } catch (NumberFormatException e) {
            msg = "Invalid food item.";
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard?msg=" + msg.replace(" ", "+"));
    }
}
