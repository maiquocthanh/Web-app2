<%-- 
    Document   : reader
    Created on : Dec 31, 2017, 11:09:44 AM
    Author     : hung
--%>

<%@page import="java.util.List"%>
<%@page import="com.funix.prj.asm4.dto.UserDTO"%>
<%@page import="com.funix.prj.asm4.model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>PRJ321 Assignment 5</title> 

        <!-- Bootstrap Core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Theme CSS -->
        <link href="css/freelancer.min.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    </head>
    <body id="page-top" class="index">

        <c:import url="header.jsp"></c:import>
            <section id="portfolio">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <h2><c:out value="${'FuNix - Posts list'}"/></h2>
                        <hr class="star-primary">
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-10">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Title</th>
                                        <th>Publishes</th>
                                        <th>Author</th>
                                        <th>Description</th>
                                        <th>Contents</th>
                                        <th>Created</th>
                                        <th>Action</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                                                       url="jdbc:mysql://localhost:3306/blogdb"
                                                       user="root"  password="12345"/>

                                    <sql:query dataSource="${dbsource}" var="result">
                                        SELECT * from postcontent ;
                                    </sql:query>

                                    <c:forEach var="row" items="${result.rows}">
                                        <tr>
                                            <td>${row.ID}</td>
                                            <td>${row.Title}</td>
                                            <td>${row.IsPublishes}</td>
                                            <td>${row.Username}</td>
                                            <td>${row.Description}</td>
                                            <td>${row.Contents}</td>
                                            <td><fmt:formatDate type="both" value="${row.Created}"/></td>

                                            <td>
                                                <a href="<%=request.getContextPath()%>/writing.jsp?param=${row.ID}">Edit</a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>                    
                </div>
            </div>
        </section>
    </body>
</html>

