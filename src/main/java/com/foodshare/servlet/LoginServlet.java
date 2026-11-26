package com.foodshare.servlet;

import com.foodshare.dao.UserDAO;
import com.foodshare.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Auth.user(req) != null) {
            resp.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim().toLowerCase();
        String password = req.getParameter("password");
        try {
            User u = new UserDAO().login(email, password);
            if (u == null) {
                req.setAttribute("error", "Email or password is incorrect.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
                return;
            }
            HttpSession session = req.getSession(true);
            session.setAttribute("user", u);
            session.setMaxInactiveInterval(30 * 60);

            // Cookie: remember the email for the next visit
            Cookie cookie = new Cookie("lastEmail", email);
            cookie.setMaxAge(7 * 24 * 60 * 60);
            resp.addCookie(cookie);

            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
