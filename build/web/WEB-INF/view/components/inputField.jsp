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
        <input type="${param.type}" value="<%= value    %>"   id="${param.field}"   class="border border-black px-2 py-1"  name="${param.field}" />
        <p class="text-red-500 text-sm">
                <%= error %>
        </p>
</div>
