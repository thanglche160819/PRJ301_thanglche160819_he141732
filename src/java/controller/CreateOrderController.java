
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import constant.Router;
import daos.DrinkDAO;
import daos.OrderDao;
import dtos.Drink;
import dtos.Order;
import dtos.OrderItem;
import utils.GetParam;

@WebServlet(name = "CreateOrderController", urlPatterns = { "/CreateOrderController" })
public class CreateOrderController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateOrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            DrinkDAO drinkDao = new DrinkDAO();
            ArrayList<Drink> listDrink = drinkDao.getAll();
            request.setAttribute("listDrink", listDrink);
            ArrayList<OrderItem> listOrderItem = new ArrayList<>();


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
                Integer drinkId = Integer.parseInt(arrOrderItemDetail[0]);
                Integer quantity = Integer.parseInt(arrOrderItemDetail[1]);
                Drink drink = drinkDao.getOneById(drinkId);
                OrderItem orderItem = new OrderItem();
                orderItem.setDrink(drink);
                orderItem.setQuantity(quantity);
                listOrderItem.add(orderItem);
            }

            request.setAttribute("listOrderItem", listOrderItem);

            request.getRequestDispatcher(Router.CREATE_ORDER_PAGE).forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
        }
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
        try {
            DrinkDAO drinkDao = new DrinkDAO();
            OrderDao orderDao = new OrderDao();
            ArrayList<OrderItem> listOrderItem = new ArrayList<>();
            ArrayList<Drink> listDrink = drinkDao.getAll();
            request.setAttribute("listDrink", listDrink);
            // // get current order
            String currentOrder = (String) request.getSession().getAttribute("currentOrder");
            if (currentOrder == null) {
                currentOrder = "";
            }

            // // split current order to array
            String[] arrOrderItem = currentOrder.split(";");
            for (String string : arrOrderItem) {
                System.out.println(string);
                if (string.equals("")) {
                    continue;
                }
                String[] arrOrderItemDetail = string.split(",");
                Integer drinkId = Integer.parseInt(arrOrderItemDetail[0]);
                Integer quantity = Integer.parseInt(arrOrderItemDetail[1]);
                Drink drink = drinkDao.getOneById(drinkId);
                OrderItem orderItem = new OrderItem();
                orderItem.setDrink(drink);
                orderItem.setQuantity(quantity);
                listOrderItem.add(orderItem);
            }
            request.setAttribute("listOrderItem", listOrderItem);

            Integer tableNumber = GetParam.getIntParams(request, "tableNumber", "Table Number", 0, Integer.MAX_VALUE,
                    null);
            Date orderDate = new Date();
            if (tableNumber == null) {
                request.setAttribute("errorMessage", "Table number is invalid");
                request.getRequestDispatcher(Router.CREATE_ORDER_PAGE).forward(request, response);
                return;
            }

            Order order = new Order();
            order.setTableNumber(tableNumber);
            order.setOrderDate(orderDate);
            order.setItems(listOrderItem);

            orderDao.createNewOrder(order);

            request.getSession().setAttribute("currentOrder", "");
            request.setAttribute("listOrderItem", new ArrayList<>());

            request.getRequestDispatcher(Router.CREATE_ORDER_PAGE).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
        }
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
