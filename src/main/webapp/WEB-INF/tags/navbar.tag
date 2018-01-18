<%--
    Custom tag to render navbar with search field and register/feed, login/logout links.
--%>

<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="isAnonymous" required="true" rtexprvalue="true" %>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/feed" var="feedLink"/>
<c:url value="/register" var="registerLink" />


<nav class="navbar navbar-dark bg-green">
    <div class="container">
        <div class="navbar-header">
            <h2>
                <c:choose>
                    <c:when test="${!isAnonymous}">
                        <a href="${feedLink}">Cool social network</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${registerLink}">Cool social network</a>
                    </c:otherwise>
                </c:choose>
            </h2>
        </div>
        <form class="form-inline">
            <input type="text" name="search" placeholder="search" class="form-control mr-sm-2">
            <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Search</button>
        </form>
        <c:choose>
            <c:when test="${isAnonymous}">
                <kivbook:login />
            </c:when>
            <c:otherwise>
                <kivbook:logout />
            </c:otherwise>
        </c:choose>
    </div>
</nav>