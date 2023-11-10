
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

@WebServlet(name = "UpdateDrinkController", urlPatterns = { "/UpdateDrinkController" })
public class UpdateDrinkController extends HttpServlet {

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
            out.println("<title>Servlet UpdateDrinkController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateDrinkController at " + request.getContextPath() + "</h1>");
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
            int drinkId = Integer.parseInt(request.getParameter("drinkId"));
            request.setAttribute("drink", drinkDao.getOneById(drinkId));
            request.getRequestDispatcher(Router.UPDATE_DRINK_PAGE).forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            DrinkDAO drinkDao = new DrinkDAO();

            int drinkId = GetParam.getIntParams(request, "drinkId", "Drink Id", 0, Integer.MAX_VALUE, null);
            String drinkName = GetParam.getStringParam(request, "drinkName", "Drink Name", 0, 50, null);
            String description = GetParam.getStringParam(request, "description", "Description", 0, 500, null);
            Float price = GetParam.getFloatParams(request, "price", "Price", 0, Float.MAX_VALUE, null);
            String image = GetParam.getStringParam(request, "image", "Image", 0, 500, null);

            if (drinkId == 0 || drinkName == null || description == null || price == null || image == null) {
                request.setAttribute("errorMessage", "Invalid params");
                request.getRequestDispatcher(Router.ERROR_PAGE).forward(request, response);
                return;
            }

            Drink drink = drinkDao.getOneById(drinkId);
            drink.setDrinkName(drinkName);
            drink.setDescription(description);
            drink.setPrice(price);
            drink.setImage(image);
            drinkDao.updateDrink(drink);
            response.sendRedirect(Router.IndexController);
        } catch (Exception e) {

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
