<%-- 
    Document   : index
    Created on : Dec 31, 2017, 11:08:05 AM
    Author     : hung
--%>
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

        <title>PRJ321 Assignment 4</title> 

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
        <!%@ include file = "header.jsp" %-->
        <c:import url="header.jsp"/>
        <section id="portfolio">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2><c:out value="${'Funix'}"/></h2>
                        <hr class="star-primary">
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                                           url="jdbc:mysql://localhost:3306/blogdb"
                                           user="root"  password="12345"/>

                        <sql:query dataSource="${dbsource}" var="result">
                            SELECT * FROM PostContent WHERE IsPublishes = 1 order  by Created desc ;
                        </sql:query>
                        <c:forEach var="row" items="${result.rows}">
                            <div class="col-lg-8">
                                <!-- Title -->
                                <h1> Title: ${row.Title}</h1>
                                <!-- Author -->
                                <p class="lead">
                                 By <a href="#">${row.Username}</a>
                                </p>
                                <hr>
                                <!-- Date/Time -->
                                <p><span class="glyphicon glyphicon-time"></span> Posted on <fmt:formatDate type="both" value="${row.Created}"/></p>
                                 <hr>   
                                <!-- Description -->
                                Description: ${row.Description}
                                <hr>
                            </div>
                        </c:forEach>

                    </div>

                </div>

            </div>
        </section>
    </body>
</html>

