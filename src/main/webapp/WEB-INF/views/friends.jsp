<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/logout" var="logoutLink"/>
<c:url value="/feed" var="feedLink"/>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${myStyle}">
    <title>Friends</title>
  </head>
  <body class="bg-light">
    <nav class="navbar navbar-dark bg-green">
        <div class="container">
            <div class="navbar-header">
                <h2><a href="${feedLink}">Cool social network</a></h2>
            </div>
            <form class="form-inline">
                <input type="text" name="search" placeholder="search" class="form-control mr-sm-2">
                <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Search</button>
            </form>

            <a href="${logoutLink}" class="nav-link"><span class="glyphicon glyphicon-log-in"></span>Time to go...</a>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-4 user-panel">
                <div class="row">
                    <img src="data:image/png;base64,${currentUser.profilePhoto}" class="img-thumbnail" alt="Profile photo">
                </div>
                <div class="row">
                    <h4><a href="${feedLink}">${currentUser.fullName}</a></h4>
                </div>

                <div class="row">
                    <h5>New friend requests:</h5>
                </div>

                <!-- new friend requets -->
                <c:forEach items="${newRequests}" var="newRequest" >
                    <div class="row">
                        <div class="card conversation-card card-unread">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <img src="data:image/png;base64,${newRequest.sender.profilePhoto}" class="img-thumbnail medium-thumbnail" alt="${newRequest.sender.fullName}" width="64" height="64">
                                    </div>
                                    <div class="col-md-9">
                                        <div class="row">
                                            <b>${newRequest.sender.fullName}</b>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <button class="btn btn-success">OK</button>
                                            </div>
                                            <div class="col-md-6">
                                                <button class="btn btn-danger">X</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>


            <div class="col-md-8">
                <h4>My friends</h4>
                <div class="card">
                    <div class="card-body">
                        <c:set var="rowItemCount" value="0" scope="page" />
                        <c:forEach items="${friendships}" var="friendship">
                            <%-- start new row after 4 friendships to make table-like structure --%>
                            <c:if test="${rowItemCount % 4 ==0}">
                                <div class="row">
                            </c:if>


                            <%-- display element --%>
                            <div class="col-md-3">
                                <div class="card card-borderless">
                                    <div class="card-body user-panel">
                                        <div class="row justify-content-center">
                                            <img src="data:image/png;base64,${friendship.sender.profilePhoto}" class="img-thumbnail medium-thumbnail" alt="${friendship.sender.fullName}" width="64" height="64">
                                        </div>
                                        <div class="row justify-content-center">
                                            <a href="${feedLink}">${friendship.sender.fullName}</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%--increment counter--%>
                            <c:set var="rowItemCount" value="${rowItemCount+1}" scope="page"/>

                            <%-- close the row element after the 4th friendship --%>
                            <c:if test="${rowItemCount % 4 == 0 || rowItemCount == friendships.size()}">
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer">
        <div class="container text-center">
            Cool Social Network - Zdeněk Valeš 2017
        </div>
    </footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  </body>
</html>