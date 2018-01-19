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
    <div class="col-md-12">
        <ul>
            <li><a href="${profileLink}"><b>P</b>rofile</a> </li>
            <li><a href="${messagesLink}"><b>M</b>essages
                <c:if test="${newMsgsCount > 0}">
                    <span class="text-highlight">${newMsgsCount}</span>
                </c:if>
            </a></li>
            <li><a href="${friendsLink}"><b>F</b>riends
                <c:if test="${newFriendsCount > 0}">
                    <span class="text-highlight">${newFriendsCount}</span>
                </c:if>
            </a></li>
        </ul>
    </div>
</div>