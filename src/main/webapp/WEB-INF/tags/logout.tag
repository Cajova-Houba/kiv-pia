<%--
    Custom tag which will render logout button as spring form.
--%>

<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/logout" var="logoutLink"/>

<c:url value="/logout" var="logoutLink"/>

<form:form method="POST" action="${logoutLink}"><button type="submit" class="nav-link btn btn-success">Time to go...</button> </form:form>
