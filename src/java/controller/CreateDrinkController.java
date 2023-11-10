
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import constant.Router;
import daos.DrinkDAO;
import dtos.Drink;
import utils.GetParam;

@WebServlet(name = "CreateDrinkController", urlPatterns = { "/CreateDrinkController" })
public class CreateDrinkController extends HttpServlet {

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
            DrinkDAO drinkDao = new DrinkDAO();

            String drinkName = GetParam.getStringParam(request, "drinkName", "Drink Name", 0, 50, null);
            String description = GetParam.getStringParam(request, "description", "Description", 0, 500, null);
            Float price = GetParam.getFloatParams(request, "price", "Price", 0, Float.MAX_VALUE, null);
            String image = GetParam.getStringParam(request, "image", "Image", 0, 500, null);

            if (drinkName == null || description == null || price == null || image == null) {
                request.setAttribute("errorMessage", "Invalid params");
                request.getRequestDispatcher(Router.CREATE_DRINK_PAGE).forward(request, response);
                return;
            }

            Drink drink = new Drink();
            drink.setDrinkName(drinkName);
            drink.setDescription(description);
            drink.setPrice(price);
            drink.setImage(image);

            drinkDao.createNewDrink(drink);
            response.sendRedirect(Router.IndexController);
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
        try {
            request.getRequestDispatcher(Router.CREATE_DRINK_PAGE).forward(request, response);
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
