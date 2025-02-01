package com.myfoods.servlets;

import java.io.IOException;
import java.util.List;

import com.myfoods.daoimple.RestaurantDAOImplementation;
import com.myfoods.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
    private RestaurantDAOImplementation restaurantDAO = new RestaurantDAOImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
            request.setAttribute("allrestaurants", allRestaurants);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch restaurants.");
        }
    }
}

