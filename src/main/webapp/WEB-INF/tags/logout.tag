<%@ attribute name="logoutLink" required="true" rtexprvalue="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="POST" action="${logoutLink}"><button type="submit" class="nav-link btn btn-success">Time to go...</button> </form:form>
