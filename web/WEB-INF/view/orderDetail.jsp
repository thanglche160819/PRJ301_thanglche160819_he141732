<%@page import="dtos.OrderItem"%>
<%@page import="dtos.Order"%>
<%@page import="dtos.Drink"%>
<%@page import="constant.Router"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>

<%
    Order order = (Order) GetParam.getClientAttribute(request, "orderDetail", new Order());
    float total = 0;
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
            <div class="max-w-2xl mx-auto space-y-4 m-4">
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
                            <h1 class="text-2xl font-bold">Order Detail</h1>

                        </div>

                    </div>


                    <div>
                        <table class="table-auto w-full">
                            <thead>
                                <tr>
                                    <th class="px-4 py-2">ID</th>
                                    <th class="px-4 py-2">Name</th>
                                    <th class="px-4 py-2">Quantity</th>
                                    <th class="px-4 py-2">Price</th>
                                    <th class="px-4 py-2">Image</th>
                                    <th class="px-4 py-2">Total</th>

                                </tr>
                            </thead>
                            <tbody>
                                <% for (OrderItem orderItem : order.getItems()) {%>
                                <%
                                    total += orderItem.getPrice() * orderItem.getQuantity();


                                %>

                                <tr>
                                    <td class="border px-4 py-2"><%= orderItem.getDrink().getDrinkId()%></td>
                                    <td class="border px-4 py-2"><%= orderItem.getDrink().getDrinkName()%></td>
                                    <td class="border px-4 py-2"><%= orderItem.getQuantity()%></td>
                                    <td class="border px-4 py-2"><%= orderItem.getDrink().getPrice()%></td>
                                    <td class="border px-4 py-2"><img src="<%= orderItem.getDrink().getImage()%>" width="100" height="100"/></td>
                                    <td class="border px-4 py-2"><%=  orderItem.getDrink().getPrice() * orderItem.getQuantity()%></td>


                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="4" class="border px-4 py-2"></td>

                                    <td class="px-4 py-2 border">Total</td>
                                    <td class="px-4 py-2 border"><%= total%></td>


                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>
        </body>
    </html>
