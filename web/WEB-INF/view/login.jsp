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

        <div class="max-w-lg mx-auto">  <h1 class="font-semibold text-4xl">Login Form</h1>
            <form action="<%= Router.LoginController%>" class="space-y-4" method="POST">
                <jsp:include page="./components/message.jsp"/>
                <jsp:include page="./components/inputField.jsp">
                    <jsp:param name="type" value="text"/>
                    <jsp:param name="label" value="Username"/>
                    <jsp:param name="field" value="username"/>
                </jsp:include>
                <jsp:include page="./components/inputField.jsp">
                    <jsp:param name="type" value="password"/>
                    <jsp:param name="label" value="Password"/>
                    <jsp:param name="field" value="password"/>
                </jsp:include>
                <button class="px-16 py-2 bg-gray-800 hover:bg-gray-600 duration-300 text-white block rounded-lg">
                    Login
                </button>

                <div>
                    Go to <a href="<%= Router.RegisterController%>" class="text-blue-500"> Register</a>
                </div>
            </form>
        </div>
    </body>
</html>
