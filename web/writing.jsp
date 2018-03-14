<%-- 
    Document   : writing
    Created on : Dec 31, 2017, 11:09:29 AM
    Author     : hung
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="jdk.nashorn.internal.ir.TryNode"%>
<%@page import="com.funix.prj.asm4.dto.*" %>
<%@page import="com.funix.prj.asm4.model.*" %>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
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
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
        <script>tinymce.init({selector: 'textarea'});</script>

    </head>
    <body id="page-top" class="index">

        <c:import url="header.jsp"/>
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2>Writing page</h2>
                        <hr class="star-primary">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <form action="AddContentServlet" name="sentMessage" method="post">
                                                       
                            <%
                                String idContent = request.getParameter("param");
                                if (idContent != null) {
                                    int id = Integer.parseInt(idContent);
                                    ContentDTO dto1 = new ContentDTO();
                                    ContentModel contents = dto1.getContentsByID(id);

                            %>
                            <div class="row control-group">
                                <div class="form-group">
                                    <c:choose>
                                        <c:when test="${contents != null && contents.getIsPublish() == 0}">
                                            <input type="radio" name="status" value="1" >Publish
                                            <input type="radio" name="status" value="0" >Draft
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="status" value="1" >Publish
                                            <input type="radio" name="status" value="0" >Draft
                                        </c:otherwise>
                                    </c:choose>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>

                            <div class="row control-group">
                                <div class="form-group">
                                    <label for="name">Author</label>
                                    <select name="author" class="form-control">  
                                        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                                                           url="jdbc:mysql://localhost:3306/blogdb"
                                                           user="root"  password="12345"/>

                                        <sql:query dataSource="${dbsource}" var="user">
                                            SELECT * from users ;
                                        </sql:query>
                                        <c:forEach var="row" items="${user.rows}">
                                            <c:choose>
                                                <c:when test="${row.Username.equals(contents.getUserID())}">
                                                   <option value="${row.Username}" selected="true">${row.Username} </option>
                                                </c:when>
                                                <c:otherwise>
                                                   <option value="${row.Username}" selected="true">${row.Username} </option>
                                                </c:otherwise>
                                            </c:choose> 
                                        </c:forEach>

                                    </select>

                                </div>
                            </div>

                            <div class="row control-group">
                                <div class="form-group">
                                    <textarea name="content" rows="30"><c:if test="${contents != null}">${contents.getContents()}</c:if></textarea>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-xs-12">
                                        <button type="submit" class="btn btn-success btn-lg">Save</button>
                                        <input type="hidden" name="edit_content" value="<%= contents.getId()%>"/>
                                </div>
                            </div>
                            <% } else {
                            %>
                            <div class="row control-group">
                                <div class="form-group">
                                    <label for="name">Title</label>
                                    <input name="title" type="text" class="form-control" placeholder="Title"
                                           id="name" required data-validation-required-message="Please enter your title.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <%--
                            <div class="row control-group">
                                <div class="form-group">
                                    <label for="name">Description</label>
                                    <input name="description" type="text"  class="form-control" placeholder="Description"
                                           id="name" required data-validation-required-message="Please enter your username.">
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            --%>

                            <div class="row control-group">
                                <div class="form-group">
                                    <input type="radio" name="status" value="1" checked>Publish
                                    <input type="radio" name="status" value="0">Draft
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>

                            <div class="row control-group">
                                <div class="form-group">
                                    <a href="postDetail.jsp"></a>
                                    <label>Author</label>
                                    <select name="author" class="form-control">  
                                        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                                                           url="jdbc:mysql://localhost:3306/blogdb"
                                                           user="root"  password="12345"/>

                                        <sql:query dataSource="${dbsource}" var="user">
                                            SELECT * from users ;
                                        </sql:query>
                                        <c:forEach var="row" items="${user.rows}">
                                            <option value="${row.Username}" selected="true">${row.Username} </option>
                                        </c:forEach>

                                       
                                    </select>
                                </div>
                            </div>

                            <div class="row control-group">
                                <div class="form-group">
                                    <textarea name="content" rows="30"></textarea>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <button type="submit" class="btn btn-success btn-lg">Create</button>                   
                                </div>
                            </div>
                            <%}%>

                        </form>

                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
