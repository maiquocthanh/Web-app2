<%-- 
    Document   : header
    Created on : Dec 31, 2017, 11:08:33 AM
    Author     : hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Navigation -->
        <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                    </button>
                    <%
                        Object object = request.getSession(false).getAttribute("isFirst");

                        if (object != null) {
                    %>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/postDetail.jsp">Welcome to the board!</a>
                    <%
                    } else {
                    %>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/postDetail.jsp">Welcome back!</a>
                    <% }%>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="hidden">
                            <a href="#page-top"></a>
                        </li>
                        <li class="page-scroll">
                            <a href="<%=request.getContextPath()%>/postDetail.jsp">Detail Posts</a>
                        </li>
                        <li class="page-scroll">
                            <a href="<%=request.getContextPath()%>/posts.jsp">All Posts</a>
                        </li>
                        <li class="page-scroll">
                            <a href="<%=request.getContextPath()%>/writing.jsp">Writing Post</a>
                        </li>
                        <li class="page-scroll">
                            <a href="<%=request.getContextPath()%>/signup.jsp">Sign-Up</a>
                        </li>
                        <li class="page-scroll">
                            <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
                        </li>
                        <li class="page-scroll">
                            <a href="<%=request.getContextPath()%>/LogoutProcessServlet">Logout</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
    </body>
</html>

