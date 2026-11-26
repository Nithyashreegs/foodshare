package com.foodshare.servlet;

import com.foodshare.dao.UserDAO;
import com.foodshare.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new User();
        u.setName(req.getParameter("name").trim());
        u.setEmail(req.getParameter("email").trim().toLowerCase());
        u.setPassword(req.getParameter("password"));
        u.setRole(req.getParameter("role"));
        u.setPhone(req.getParameter("phone").trim());
        u.setCity(req.getParameter("city").trim());

        String error = null;
        if (u.getName().isEmpty() || u.getEmail().isEmpty() || u.getCity().isEmpty()) {
            error = "Please fill in all fields.";
        } else if (u.getPassword() == null || u.getPassword().length() < 6) {
            error = "Password must be at least 6 characters.";
        } else if (!"DONOR".equals(u.getRole()) && !"RECEIVER".equals(u.getRole())) {
            error = "Choose whether you are a donor or a receiver.";
        } else if (!u.getPhone().matches("\\d{10}")) {
            error = "Phone number must be 10 digits.";
        }

        try {
            if (error == null && new UserDAO().emailExists(u.getEmail())) {
                error = "This email is already registered. Try logging in.";
            }
            if (error == null) {
                new UserDAO().register(u);
                resp.sendRedirect(req.getContextPath() + "/login?msg=Account+created.+Please+log+in.");
                return;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.setAttribute("error", error);
        req.setAttribute("form", u);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
