package com.myfoods.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/selectRestaurant")
public class SelectRestaurantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String restaurantIdParam = req.getParameter("restaurant_id");

        if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
            Integer restaurantIdInt = Integer.parseInt(restaurantIdParam);
            session.setAttribute("restaurant_id", restaurantIdInt);
            System.out.println("Set session restaurant_id: " + restaurantIdInt); // Debugging

            // Redirect to menu page with the restaurant_id
            resp.sendRedirect("menu?restaurant_id=" + restaurantIdInt); 
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing restaurant ID");
        }
    }
}
