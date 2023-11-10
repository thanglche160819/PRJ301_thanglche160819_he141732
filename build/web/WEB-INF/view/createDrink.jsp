<%@page import="constant.Router"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.User"%>
<%@page import="java.util.ArrayList"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body>

        <div class="max-w-4xl mt-4 mx-auto">  
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
                   <h1 class="font-semibold text-4xl">Create Drink</h1>
            <form action="<%= Router.CreateDrinkController%>" class="space-y-4" method="POST">
                <jsp:include page="./components/message.jsp"/>
                <jsp:include page="./components/inputField.jsp">
                    <jsp:param name="type" value="text"/>
                    <jsp:param name="label" value="Drink Name"/>
                    <jsp:param name="field" value="drinkName"/>

                </jsp:include>
                <jsp:include page="./components/inputField.jsp">
                    <jsp:param name="type" value="number"/>
                    <jsp:param name="label" value="Price"/>
                    <jsp:param name="field" value="price"/>
                    <jsp:param name="defaultValue" value="0"/>
                </jsp:include>
                <jsp:include page="./components/inputField.jsp">
                    <jsp:param name="type" value="text"/>
                    <jsp:param name="label" value="Image"/>
                    <jsp:param name="field" value="image"/>
                </jsp:include>
                <jsp:include page="./components/textareaField.jsp">
                    <jsp:param name="type" value="text"/>
                    <jsp:param name="label" value="Description"/>
                    <jsp:param name="field" value="description"/>
                </jsp:include>
                <button class="px-16 py-2 bg-gray-800 hover:bg-gray-600 duration-300 text-white block rounded-lg">
                    Create
                </button>
            </form>
        </div>
    </body>
</html>
