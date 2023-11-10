/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

public class Router {
    // jsp mapper

    public final static String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    public final static String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    public final static String INDEX_PAGE = "/WEB-INF/view/index.jsp";
    public final static String ERROR_PAGE = "/WEB-INF/view/error.jsp";
    public final static String CREATE_DRINK_PAGE = "/WEB-INF/view/createDrink.jsp";
    public final static String UPDATE_DRINK_PAGE = "/WEB-INF/view/updateDrink.jsp";
    public final static String CREATE_ORDER_PAGE = "/WEB-INF/view/createOrder.jsp";
    public final static String LIST_ORDER_PAGE = "/WEB-INF/view/listOrder.jsp";
    public final static String ORDER_DETAIL_PAGE = "/WEB-INF/view/orderDetail.jsp";

    // servlet router
    public final static String LoginController = "/ProjectPRJ/LoginController";
    public final static String IndexController = "/ProjectPRJ/IndexController";
    public final static String RegisterController = "/ProjectPRJ/RegisterController";
    public final static String LogoutController = "/ProjectPRJ/LogoutController";
    public final static String CreateDrinkController = "/ProjectPRJ/CreateDrinkController";
    public final static String UpdateDrinkController = "/ProjectPRJ/UpdateDrinkController";
    public final static String DeleteDrinkController = "/ProjectPRJ/DeleteDrinkController";
    public final static String CreateOrderController = "/ProjectPRJ/CreateOrderController";
    public final static String DeleteOrderController = "/ProjectPRJ/DeleteOrderController";
    public final static String AddToOrderController = "/ProjectPRJ/AddToOrderController";
    public final static String ListOrderController = "/ProjectPRJ/ListOrderController";
    public final static String OrderDetailController = "/ProjectPRJ/OrderDetailController";
}
