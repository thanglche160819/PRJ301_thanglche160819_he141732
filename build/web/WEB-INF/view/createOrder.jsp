<%@page import="dtos.OrderItem"%>
<%@page import="dtos.Drink"%>
<%@page import="constant.Router"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Drink> drinks = (ArrayList<Drink>) GetParam.getClientAttribute(request, "listDrink", new ArrayList<Drink>());
    ArrayList<OrderItem> currentOrder = (ArrayList<OrderItem>) GetParam.getClientAttribute(request, "listOrderItem", new ArrayList<OrderItem>());
    String errorMessage = (String) GetParam.getClientAttribute(request, "errorMessage", "");
    String message = (String) GetParam.getClientAttribute(request, "message", "");
    float total = 0;
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body>

        <div class=" m-4">  
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
            
            <jsp:include page="./components/message.jsp"/>
            <div class="flex gap-10">
                <div>
                <h1 class="font-semibold text-2xl">Create Order</h1>
                    <table class="table-auto w-full">
                        <thead>
                            <tr>
                                <th class="px-4 py-2">ID</th>
                                <th class="px-4 py-2">Name</th>
                                <th class="px-4 py-2">Price</th>
                                <th class="px-4 py-2">Description</th>
                                <th class="px-4 py-2">Image</th>
                                <th class="px-4 py-2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Drink drink : drinks) {%>
                            <tr>
                                <td class="border px-4 py-2"><%= drink.getDrinkId()%></td>
                                <td class="border px-4 py-2"><%= drink.getDrinkName()%></td>
                                <td class="border px-4 py-2"><%= drink.getPrice()%></td>
                                <td class="border px-4 py-2"><%= drink.getDescription()%></td>
                                <td class="border px-4 py-2"><img src="<%= drink.getImage()%>" width="100" height="100"/></td>

                                <td class="border px-4 py-2">
                                    <a class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                                       href="<%= Router.AddToOrderController%>?drinkId=<%= drink.getDrinkId()%>"
                                       >
                                        Add to Order
                                    </a>

                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>

                <div>
                    <div class="font-semibold text-2xl">
                    Current Order
                    </div>
                    <table class="table-auto w-full">
                        <thead>
                            <tr>
                                <th class="px-4 py-2">ID</th>
                                <th class="px-4 py-2">Name</th>
                                <th class="px-4 py-2">Quantity</th>
                                <th class="px-4 py-2">Price</th>
                                <th class="px-4 py-2">Image</th>
                                <th class="px-4 py-2">Total</th>
                                <th class="px-4 py-2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (OrderItem orderItem : currentOrder) {%>
                            <%
                                total += orderItem.getDrink().getPrice() * orderItem.getQuantity();
                            %>

                            <tr>
                                <td class="border px-4 py-2"><%= orderItem.getDrink().getDrinkId()%></td>
                                <td class="border px-4 py-2"><%= orderItem.getDrink().getDrinkName()%></td>
                                <td class="border px-4 py-2"><%= orderItem.getQuantity()%></td>
                                <td class="border px-4 py-2"><%= orderItem.getDrink().getPrice()%></td>
                                <td class="border px-4 py-2"><img src="<%= orderItem.getDrink().getImage()%>" width="100" height="100"/></td>
                                <td class="border px-4 py-2"><%=  orderItem.getDrink().getPrice() * orderItem.getQuantity()%></td>

                                <td class="border px-4 py-2">
                                    <a class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                                       href="<%= Router.DeleteOrderController%>?drinkId=<%= orderItem.getDrink().getDrinkId()%>"
                                       >
                                        Remove
                                    </a>

                                </td>
                            </tr>
                            <% }%>
                            <tr>
                                <td colspan="4" class="border px-4 py-2"></td>

                                <td class="px-4 py-2 border">Total</td>
                                <td class="px-4 py-2 border"><%= total%></td>
                                <td class="px-4 py-2 border"></td>

                            </tr>
                        </tbody>
                    </table>

                    <form action="<%= Router.CreateOrderController%>" method="POST" class="flex flex-col gap-4">
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="number"/>
                            <jsp:param name="label" value="Table Number"/>
                            <jsp:param name="field" value="tableNumber"/>
                        </jsp:include>
                        <button class="view inline-block bg-blue-800 hover:bg-blue-600 duration-300 rounded-lg text-white px-4 py-1"
                                type="submit"
                                >
                            Create Order
                        </button>
                    </form>
                </div>
            </div>

        </div>
    </body>
</html>
