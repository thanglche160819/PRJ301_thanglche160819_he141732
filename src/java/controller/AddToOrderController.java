
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import constant.Router;
import daos.DrinkDAO;
import dtos.Drink;
import dtos.OrderItem;
import utils.GetParam;

@WebServlet(name = "AddToOrderController", urlPatterns = { "/AddToOrderController" })
public class AddToOrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Integer drinkId = GetParam.getIntParams(request, "drinkId", "Drink Id", 0, Integer.MAX_VALUE, null);
            DrinkDAO drinkDao = new DrinkDAO();
            ArrayList<OrderItem> listOrderItem = new ArrayList<>();
            if (drinkId == null) {
                request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
                return;
            }
            // get current order
            String currentOrder = (String) request.getSession().getAttribute("currentOrder");
            if (currentOrder == null) {
                currentOrder = "";
            }

            String[] arrOrderItem = currentOrder.split(";");
            for (String string : arrOrderItem) {
                System.out.println(string);
                if (string.equals("")) {
                    continue;
                }
                String[] arrOrderItemDetail = string.split(",");
                Integer orderDrinkId = Integer.parseInt(arrOrderItemDetail[0]);
                Integer quantity = Integer.parseInt(arrOrderItemDetail[1]);
                Drink drink = drinkDao.getOneById(orderDrinkId);
                System.out.println(drink);
                OrderItem orderItem = new OrderItem();
                orderItem.setDrink(drink);
                orderItem.setQuantity(quantity);
                listOrderItem.add(orderItem);
            }
            // check if drink is exist in order
            boolean isExist = false;
            for (OrderItem orderItem : listOrderItem) {
                if (orderItem.getDrink().getDrinkId() == drinkId) {
                    orderItem.setQuantity(orderItem.getQuantity() + 1);
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                Drink drink = drinkDao.getOneById(drinkId);
                OrderItem orderItem = new OrderItem();
                orderItem.setDrink(drink);
                orderItem.setQuantity(1);
                listOrderItem.add(orderItem);
            }

            // update current order
            currentOrder = "";
            for (OrderItem orderItem : listOrderItem) {
                currentOrder += orderItem.getDrink().getDrinkId() + "," + orderItem.getQuantity() + ";";
            }

            request.setAttribute("message", "Add to order successfully");

            request.getSession().setAttribute("currentOrder", currentOrder);
            response.sendRedirect(Router.CreateOrderController);
        } catch (Exception e) {
            request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
