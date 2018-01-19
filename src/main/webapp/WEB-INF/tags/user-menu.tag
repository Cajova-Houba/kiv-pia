<%--
    Custom tag to render user's menu.
--%>

<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="newMsgsCount" required="true" rtexprvalue="true" %>
<%@ attribute name="newFriendsCount" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/messages" var="messagesLink" />
<c:url value="/friends" var="friendsLink" />
<c:url value="/profile" var="profileLink" />


<div class="row user-menu">
    <ul>
        <li><a href="${profileLink}">Profile</a> </li>
        <li><a href="${messagesLink}">Messages
            <c:if test="${newMsgsCount > 0}">
                <span class="text-highlight">${newMsgsCount}</span>
            </c:if>
        </a></li>
        <li><a href="${friendsLink}">Friends
            <c:if test="${newFriendsCount > 0}">
                <span class="text-highlight">${newFriendsCount}</span>
            </c:if>
        </a></li>
        <li>Settings</li>
    </ul>
</div>