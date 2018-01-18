<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/feed" var="feedLink"/>
<c:url value="/logout" var="logoutLink"/>
<c:url value="/messages" var="messagesLink" />
<c:url value="/friends" var="friendsLink" />
<c:url value="/profile" var="profileLink" />

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${myStyle}">
    <title>User profile</title>
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
        <kivbook:logout logoutLink="${logoutLink}"/>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-3 user-panel">
            <div class="row">
                <kivbook:image kivbookImage="${user.profilePhoto}" alt="Profile photo"/>
            </div>
            <div class="row">
                <h4><a href="${feedLink}">${user.fullName}</a></h4>
            </div>

            <kivbook:user-menu newMsgsCount="${newMsgs}" newFriendsCount="${newFriendReq}"/>

        </div>
        <div class="col-md-9">
            <h4>${user.fullName} profile</h4>
            <%-- if the edit mode is off, display normal profile, otherwise display edit form --%>
            <c:choose>
                <c:when test="${isEditMode}">
                    <form>
                        <div class="row">
                            <div class="col-md-3">Username</div>
                            <div class="col-md-9"><input type="text" path="username" value="${user.username}" /></div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Full name</div>
                            <div class="col-md-9"><input type="text" path="fullName" value="${user.fullName}" /></div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">Birth date</div>
                            <div class="col-md-9">${user.birthDate}</div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <a href="${profileLink}">Save</a>
                            </div>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <div class="col-md-3">Username</div>
                        <div class="col-md-9">${user.username}</div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">Full name</div>
                        <div class="col-md-9">${user.fullName}</div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">Birth date</div>
                        <div class="col-md-9">${user.birthDate}</div>
                    </div>
                    <%-- display edit button for current user --%>
                    <c:if test="${isCurrentUser}">
                        <div class="row">
                            <div class="col-md-12">
                                <a href="${profileLink}/${user.username}/edit">Edit</a>
                            </div>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <div class="card">


            </div>
            <%--<div class="card post-panel">--%>
                <%--<div class="card-body">--%>
                    <%--<form:form method="post" action="${feedLink}" modelAttribute="newPost">--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-3">--%>
                                <%--<kivbook:image kivbookImage="${currentUser.profilePhoto}" alt="Profile photo" classes="medium-thumbnail"/>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-9">--%>
                                <%--<form:input path="text" placeholder="Say something..." class="form-control" maxlength="1000" required="required"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-3">--%>
                                <%--<h5 class="card-title">${currentUser.fullName}</h5>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-9">--%>
                                <%--<div class="float-right">--%>
                                    <%--<form:select path="visibility" cssClass="from-control text-muted small-font">--%>
                                        <%--<form:options items="${allowedVisibilities}" />--%>
                                    <%--</form:select>--%>
                                    <%--<input type="submit" name="submit-post" class="btn btn-success" value="Post!">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<input type="hidden"--%>
                               <%--name="${_csrf.parameterName}"--%>
                               <%--value="${_csrf.token}"/>--%>
                    <%--</form:form>--%>
                <%--</div>--%>
            </div>

            <!-- pagination -->
            <%-- won't be displayed if there's only 1 page --%>
            <%--<c:if test="${!(pageControls.firstPage && pageControls.lastPge)}">--%>
                <%--<ul class="pagination">--%>
                        <%--&lt;%&ndash; 'previous' and 'next' buttons are enabled only if the current page is not first or last respectively &ndash;%&gt;--%>
                    <%--<c:choose>--%>
                        <%--<c:when test="${pageControls.firstPage}">--%>
                            <%--<li class="page-item disabled">--%>
                                    <%--<span class="page-link">--%>
                                        <%--<span aria-hidden="true">&laquo;</span>--%>
                                        <%--<span class="sr-only">Previous</span>--%>
                                    <%--</span>--%>
                            <%--</li>--%>
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>
                            <%--<li class="page-item">--%>
                                <%--<a class="page-link" href="${feedLink}/${pageControls.prevPage}">--%>
                                    <%--<span aria-hidden="true">&laquo;</span>--%>
                                    <%--<span class="sr-only">Previous</span>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>

                        <%--&lt;%&ndash; create page number list and highlight the current one &ndash;%&gt;--%>
                    <%--<c:forEach items="${pageControls.pageNumbers}" var="pageNum">--%>
                        <%--<li class="page-item <c:if test="${pageNum == pageControls.currentPage}">active</c:if>" >--%>
                            <%--<a class="page-link" href="${feedLink}/${pageNum}">${pageNum}</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>

                        <%--&lt;%&ndash; next button &ndash;%&gt;--%>
                    <%--<c:choose>--%>
                        <%--<c:when test="${pageControls.lastPge}">--%>
                            <%--<li class="page-item disabled">--%>
                                    <%--<span class="page-link">--%>
                                        <%--<span aria-hidden="true">&raquo;</span>--%>
                                        <%--<span class="sr-only">Next</span>--%>
                                    <%--</span>--%>
                            <%--</li>                                </c:when>--%>
                        <%--<c:otherwise>--%>
                            <%--<li class="page-item">--%>
                                <%--<a class="page-link" href="${feedLink}/${pageControls.nextPage}" aria-label="Next">--%>
                                    <%--<span aria-hidden="true">&raquo;</span>--%>
                                    <%--<span class="sr-only">Next</span>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                <%--</ul>--%>
            <%--</c:if>--%>


            <%--<c:forEach items="${posts}" var="post">--%>
                <%--<div class="card post-panel">--%>
                    <%--<div class="card-body">--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-3">--%>
                                <%--<a href="#"><kivbook:image kivbookImage="${post.owner.profilePhoto}" alt="${post.owner.fullName}" classes="medium-thumbnail"/></a>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-9">--%>
                                    <%--${post.text}--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-3 profile-link">--%>
                                <%--<h5 class="card-title"><a href="#">${post.owner.fullName}</a></h5>--%>
                            <%--</div>--%>
                            <%--<div class="col-md-9">--%>
                                <%--<p class="text-right"><fmt:formatDate value="${post.datePosted}" pattern="dd. MM. yyyy"/></p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</c:forEach>--%>
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