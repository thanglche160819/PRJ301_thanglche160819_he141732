<%@page import="utils.GetParam"%>
<%
        String value =(String) GetParam.getClientParams(request,request.getParameter("field"),"");

        if (request.getParameter("field").equals("password")){
                value = "";
        }
        if (request.getParameter("defaultValue") != null && value.equals("")){
                value =request.getParameter("defaultValue");
        }

        String error =(String) GetParam.getClientAttribute(request,request.getParameter("field")+"Error", "" );


%>

<div class="flex flex-col">
        <label for="username" class="font-semibold">${param.label}</label>
        <textarea name="${param.field}" id="${param.field}" class="border border-black px-2 py-1"  cols="30" rows="10"><%= value    %></textarea>
        <p class="text-red-500 text-sm">
                <%= error %>
        </p>
</div>
