<%--
    Custom tag which will render logout button as spring form.
--%>

<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/logout" var="logoutLink"/>


<form:form method="POST" action="${logoutLink}"><button type="submit" class="nav-link btn btn-success">Time to go...</button> </form:form>
