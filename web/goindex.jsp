<%-- 
    Document   : goindex
    Created on : Jul 31, 2013, 1:26:35 PM
    Author     : Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    request.getSession(true);
    response.sendRedirect("utfpr/index.xhtml");
%>
