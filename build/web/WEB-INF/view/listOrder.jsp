<%@page import="dtos.OrderItem"%>
<%@page import="dtos.Order"%>
<%@page import="dtos.Drink"%>
<%@page import="constant.Router"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>

<%
    ArrayList<Order> orders = (ArrayList<Order>) GetParam.getClientAttribute(request, "listOrder", new ArrayList<Order>());
%>


<!DOCTYPE
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <script src="https://cdn.tailwindcss.com"></script>
        </head>
        <body>
            <div class="max-w-4xl mx-auto space-y-4 m-4">
                <div class="flex space-x-4">
                    <a class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                       href="<%= Router.IndexController%>"
                       >
                        List Drink
                    </a>

                    <a class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                       href="<%= Router.CreateDrinkController%>"
                       >
                        Add Drink
                    </a>

                    <a class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                       href="<%= Router.ListOrderController%>"
                       >
                        List Order
                    </a>


                    <a class="view inline-block bg-green-800 hover:bg-green-600 duration-300 rounded-lg text-white px-4 py-1"
                       href="<%= Router.CreateOrderController%>"
                       >
                        Create Order
                    </a>

                    <a class="view inline-block bg-red-800 hover:bg-red-600 duration-300 rounded-lg text-white px-4 py-1"
                       href="<%= Router.LogoutController%>"
                       >
                        Logout
                    </a>
                </div>
                <div>
                    <div>
                        <h1 class="text-2xl font-bold">List Order</h1>

                    </div>

                </div>

                <div>
                    <table class="table-auto w-full">
                        <thead>
                            <tr>
                                <th class="px-4 py-2">ID</th>
                                <th class="px-4 py-2">Order Date</th>
                                <th class="px-4 py-2">Table Number</th>
                                <th class="px-4 py-2">Total Item</th>
                                <th class="px-4 py-2">Total Price</th>
                                <th class="px-4 py-2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Order order : orders) {%>
                            <%
                                float totalPrice = 0;
                                for (OrderItem orderItem : order.getItems()) {
                                    totalPrice += orderItem.getPrice() * orderItem.getQuantity();
                                }
                            %>


                            <tr>
                                <td class="border px-4 py-2"><%= order.getOrderId()%></td>
                                <td class="border px-4 py-2"><%= order.getOrderDate()%></td>
                                <td class="border px-4 py-2"><%= order.getTableNumber()%></td>
                                <td class="border px-4 py-2"><%= order.getItems().size()%></td>
                                <td class="border px-4 py-2"><%=  totalPrice%></td>
                                <td class="border px-4 py-2">
                                    <a class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                                       href="<%= Router.OrderDetailController%>?orderId=<%= order.getOrderId()%>"
                                       >
                                        View Detail
                                    </a>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </body>
    </html>
