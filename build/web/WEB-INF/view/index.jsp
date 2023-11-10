<%@page import="dtos.Drink"%>
<%@page import="constant.Router"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>

<%
    ArrayList<Drink> drinks = (ArrayList<Drink>) GetParam.getClientAttribute(request, "listDrink", new ArrayList<Drink>());
    String errorMessage = (String) GetParam.getClientAttribute(request, "errorMessage", "");
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
                        <h1 class="text-2xl font-bold">Drink List</h1>

                    </div>
                    <p class="text-red-500"><%= errorMessage%></p>
                </div>
                <div>
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
                                       href="<%= Router.UpdateDrinkController%>?drinkId=<%= drink.getDrinkId()%>"
                                       >
                                        Update
                                    </a>
                                    <a class="view inline-block bg-red-800 hover:bg-red-600 duration-300 rounded-lg text-white px-4 py-1"
                                       onclick="return confirm('Are you sure you want to delete this item?');"
                                       href="<%= Router.DeleteDrinkController%>?drinkId=<%= drink.getDrinkId()%>"
                                       >
                                        Delete
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
