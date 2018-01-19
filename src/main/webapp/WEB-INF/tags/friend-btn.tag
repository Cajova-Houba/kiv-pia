<%--
    Custom tag to render 'Send friend request' button as a spring form.
--%>

<%@ attribute name="username" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/friends" var="friendsLink" />

<form:form action="${friendsLink}/send/${username}" method="POST">
    <button type="submit" class="btn btn-success">Send friend request</button>
</form:form>
