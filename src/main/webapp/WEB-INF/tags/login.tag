<%--
    Custom tag which will render login link.
--%>

<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/login" var="loginLink"/>
<a href="${loginLink}" class="nav-link active"><span class="glyphicon glyphicon-log-in"></span>Going in!</a>