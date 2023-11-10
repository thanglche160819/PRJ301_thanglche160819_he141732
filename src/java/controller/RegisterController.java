
package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import constant.Router;
import daos.UserDAO;
import dtos.User;
import utils.GetParam;

public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        // initialize resource
        UserDAO userDAO = new UserDAO();

        // validate params
        String username = GetParam.getStringParam(request, "username", "Username", 1, 50, null);
        String password = GetParam.getStringParam(request, "password", "Password", 1, 50, null);
        String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 1, 50, null);
        String fullName = GetParam.getStringParam(request, "fullName", "Full Name", 1, 50, null);

        if (username == null || password == null || confirmPassword == null || fullName == null) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("confirmPasswordError", "Confirm password is not match");
            return false;
        }

        User existedUser = userDAO.getOneByUsername(username);
        if (existedUser != null) {
            request.setAttribute("usernameError", "Username is existed");
            return false;
        }

        // create user
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);

        // insert user
        userDAO.createNewUser(user);

        // handle on login

        return true;
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
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher(Router.REGISTER_PAGE).forward(request, response);
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
            if (processRequest(request, response)) {
                response.sendRedirect(Router.LoginController);

                // forward on 200
                return;
            }
            // forward on 400
            this.doGet(request, response);
        } catch (Exception e) {
            System.out.println(e);

            // redirect on 500
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
