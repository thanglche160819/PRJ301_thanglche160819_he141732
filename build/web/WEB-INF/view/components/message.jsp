<%@page import="utils.GetParam"%>
<%
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String message =(String) GetParam.getClientParams(request,"message", "" );	
%>

<p class="text-red-500">
	<%=errorMessage%>
</p>
<p>
	<%=message%>
</p>
