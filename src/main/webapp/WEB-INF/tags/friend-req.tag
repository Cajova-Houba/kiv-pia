<%--
    Custom tag to render pending frined reqeust card. Works for both type of pending requests - received and sent.
--%>

<%@attribute name="friendRequest"  required="true" rtexprvalue="true" type="cz.zcu.pia.valesz.core.domain.FriendRequest" %>
<%@attribute name="isReceived" required="true" rtexprvalue="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<c:url value="/profile" var="profileLink" />
<c:url value="/friends" var="friendsLink" />

<c:choose>
    <c:when test="${isReceived}">
        <c:set var="otherUser" value="${friendRequest.sender}"/>
    </c:when>
    <c:otherwise>
        <c:set var="otherUser" value="${friendRequest.receiver}"/>
    </c:otherwise>
</c:choose>
<div class="card conversation-card card-unread">
    <div class="card-body">
        <div class="row">
            <div class="col-md-4">
                <a href="${profileLink}/${otherUser.username}">
                    <kivbook:image kivbookImage="${otherUser.profilePhoto}" alt="${otherUser.fullName}" classes="medium-thumbnail"/>
                </a>
            </div>
            <div class="col-md-8">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${profileLink}/${otherUser.username}"><b>${otherUser.fullName}</b></a>
                    </div>
                </div>

                <%--
                    For received requests display OK and X buttons.
                    For sent requests display only X button.
                --%>
                <div class="row">
                    <c:choose>
                        <c:when test="${isReceived}">
                            <div class="col-md-6">
                                <form:form method="post" action="${friendsLink}/accept">
                                    <input type="hidden" name="requestId" value="${friendRequest.id}">
                                    <button type="submit" class="btn btn-success">OK</button>
                                </form:form>
                            </div>
                            <div class="col-md-6">
                                <form:form method="post" action="${friendsLink}/reject">
                                    <input type="hidden" name="requestId" value="${friendRequest.id}">
                                    <button class="btn btn-danger">X</button>
                                </form:form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-12">
                                <form:form method="post" action="${friendsLink}/cancel">
                                    <input type="hidden" name="requestId" value="${friendRequest.id}">
                                    <button class="btn btn-danger">X</button>
                                </form:form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>