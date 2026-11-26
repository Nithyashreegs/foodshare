package com.foodshare.servlet;

import com.foodshare.model.User;
import java.io.IOException;
import javax.servlet.http.*;

/** Small helper used by controllers to check the session. */
public class Auth {

    public static User user(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return s == null ? null : (User) s.getAttribute("user");
    }

    /** Returns true if logged in (and has the given role, when role != null). Otherwise redirects. */
    public static boolean require(HttpServletRequest req, HttpServletResponse resp, String role) throws IOException {
        User u = user(req);
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return false;
        }
        if (role != null && !role.equals(u.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/dashboard?msg=That+page+is+for+" + role.toLowerCase() + "s+only");
            return false;
        }
        return true;
    }
}
