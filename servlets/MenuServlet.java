package com.myfoods.servlets;

import java.io.IOException;
import java.util.List;

import com.myfoods.daoimple.MenuDAOImplementation;
import com.myfoods.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Menu working...");

        HttpSession session = req.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurant_id");
        System.out.println("Session restaurant_id: " + restaurantId); // Debugging

        if (restaurantId != null) {
            List<Menu> menuList = getMenuListByRestaurant(restaurantId);
            req.setAttribute("menus", menuList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("Menu.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing restaurant ID in session");
        }
    }

    private List<Menu> getMenuListByRestaurant(Integer restaurantId) {
        MenuDAOImplementation menuDAO = new MenuDAOImplementation();
        return menuDAO.getAllMenusByRestaurant(restaurantId);
    }
}
