package com.foodshare.servlet;

import com.foodshare.dao.RequestDAO;
import com.foodshare.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/** Donor actions on a request: accept, reject, pickup. */
@WebServlet("/manage-request")
public class ManageRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Auth.require(req, resp, "DONOR")) return;
        User u = Auth.user(req);
        String action = req.getParameter("action");
        String msg;
        try {
            int id = Integer.parseInt(req.getParameter("requestId"));
            RequestDAO dao = new RequestDAO();
            boolean ok;
            if ("accept".equals(action)) {
                ok = dao.respond(id, u.getId(), true);
                msg = ok ? "Request accepted. Share the pickup details with the receiver." : "Could not update request.";
            } else if ("reject".equals(action)) {
                ok = dao.respond(id, u.getId(), false);
                msg = ok ? "Request rejected. The food is available again." : "Could not update request.";
            } else if ("pickup".equals(action)) {
                ok = dao.markPickedUp(id, u.getId());
                msg = ok ? "Marked as picked up. Thank you for sharing!" : "Could not update request.";
            } else {
                msg = "Unknown action.";
            }
        } catch (NumberFormatException e) {
            msg = "Invalid request.";
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/dashboard?msg=" + msg.replace(" ", "+"));
    }
}
