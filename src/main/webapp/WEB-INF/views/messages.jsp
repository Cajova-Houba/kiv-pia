<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/feed" var="feedLink"/>
<c:url value="/logout" var="logoutLink"/>
<c:url value="/messages" var="messageLink"/>

<!DOCTYPE html>
<html lang="en">
  <kivbook:head title="Messages"/>
  <body class="bg-light">
    <kivbook:navbar isAnonymous="false"/>
    <div class="container">
        <div class="row">
            <div class="col-md-4 user-panel">
                <div class="row">
                    <kivbook:image kivbookImage="${currentUser.profilePhoto}" alt="Profile photo"/>
                </div>
                <div class="row">
                    <h4><a href="${feedLink}">${currentUser.fullName}</a></h4>
                </div>

                <div class="row">
                    <h5>Conversations</h5>
                </div>

                <div class="row">
                    <div class="card conversation-card">
                        <div class="card-body">
                            <a href="${messageLink}/new"><b>+</b> New message</a>
                        </div>
                    </div>
                </div>

                <!-- conversations -->
                <%-- it may happen tha ${conversation} is null in that case ${conversations} should also be empty --%>
                <c:forEach items="${conversations}" var="conv">
                    <div class="row">
                        <div class="card conversation-card <c:if test="${conv.newFlag}">card-unread</c:if> <c:if test="${conv == conversation}">conversation-card-selected</c:if> ">
                            <a href="${messageLink}/${conv.getOtherUser(currentUser).username}">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <kivbook:image kivbookImage="${conv.secondUser.profilePhoto}" alt="${conv.secondUser.fullName}" classes="medium-thumbnail"/>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="row">
                                                <b>${conv.secondUser.fullName}</b>
                                            </div>
                                            <div class="row">
                                                ${conv.newestMessage.text}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <c:set var="counter" value="${counter+1}" scope="page" />
                </c:forEach>
            </div>


            <!-- middle panel with conversations or form for new conversation -->
            <div class="col-md-8">
                <c:choose>
                    <c:when test="${isNewConv}">
                        <h4>New conversation</h4>
                        <div class="card">
                            <div class="card-body">
                                <form:form action="${messageLink}/new" method="post" modelAttribute="newConversationModel">
                                    <fieldset class="bottom-margin">
                                        <div class="row">
                                            <div class="col-md-4"><form:label path="receiverUsername">Send to:</form:label></div>
                                            <div class="col-md-4">
                                                <form:select path="receiverUsername" cssClass="form-control" tabindex="1" autofocus="autofocus">
                                                    <form:options items="${possibleReceivers}" cssClass="form-control"/>
                                                </form:select>
                                            </div>
                                            <div class="col-md-4"></div>
                                        </div>
                                    </fieldset>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <form:input path="messageText" maxlength="500" cssClass="form-control" required="required" tabindex="2"/>
                                        </div>
                                        <div class="col-md-4">
                                            <button type="submit" class="btn btn-success" tabindex="3">Send</button>
                                        </div>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${conversation == null}">
                        <h4>No messages</h4>
                        <div class="card">

                        </div>
                    </c:when>
                    <c:otherwise>
                        <h4>${conversation.secondUser.fullName}</h4>
                        <div class="card">
                            <div class="card-body">
                                <fmt:formatDate value="${conversation.oldestMessage.timestamp}" pattern="dd" var="curDay"/>
                                <c:set var="counter" value="1" />
                                <c:forEach items="${conversation.messages}" var="msg">

                                    <%-- display date for the first message --%>
                                    <c:if test="${counter == 1}" >
                                        <div class="row">
                                            <p class="text-muted text-center full-width">
                                                <fmt:formatDate value="${msg.timestamp}" pattern="dd. MM. yyyy"/>
                                                <c:set var="counter" value="0"/>
                                            </p>
                                        </div>
                                    </c:if>

                                    <%-- add separator for messages from other dates --%>
                                    <fmt:formatDate value="${msg.timestamp}" pattern="dd" var="tmpDay" />
                                    <c:if test="${curDay != tmpDay}">
                                        <hr>
                                        <div class="row">
                                            <p class="text-muted text-center full-width"> <fmt:formatDate value="${msg.timestamp}" pattern="dd. MM. yyyy"/></p>
                                        </div>
                                        <c:set var="curDay" value="${tmpDay}"/>
                                    </c:if>

                                    <div class="row">
                                        <c:choose>
                                            <c:when test="${msg.sender == currentUser}">
                                                <p class="my-message">
                                                        ${msg.text}
                                                </p>
                                                <span class="text-muted"><fmt:formatDate value="${msg.timestamp}" pattern="HH:mm"/></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="text-muted"><fmt:formatDate value="${msg.timestamp}" pattern="HH:mm"/></span>
                                                <p class="other-message">
                                                        ${msg.text}
                                                </p>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </c:forEach>

                                <%-- form for response to conversation --%>
                                <div class="row">
                                    <div class="col-md-6"></div>
                                    <div class="col-md-6">
                                        <form method="POST" action="${messageLink}/${conversation.secondUser.username}" class="form-inline">
                                            <label class="sr-only" for="msgText">Name</label>
                                            <input type="text" maxlength="500" placeholder="Respond..." id="msgText" name="msgText" class="form-control full-width" tabindex="1" autofocus required>
                                            <input type="hidden"
                                                   name="${_csrf.parameterName}"
                                                   value="${_csrf.token}"/>
                                            <input type="submit" value="Send" class="btn btn-success" tabindex="2">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- end of message card -->
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <kivbook:footer />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  </body>
</html>