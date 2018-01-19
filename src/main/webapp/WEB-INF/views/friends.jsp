<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/logout" var="logoutLink"/>
<c:url value="/feed" var="feedLink"/>
<c:url value="/profile" var="profileLink"/>
<c:url value="/friends" var="friendsLink" />

<!DOCTYPE html>
<html lang="en">
    <kivbook:head title="Friends"/>
    <body class="bg-light">
        <kivbook:navbar isAnonymous="false"/>
        <div class="container">
            <div class="row">
                <div class="col-md-3 user-panel">
                    <div class="row">
                        <kivbook:image kivbookImage="${currentUser.profilePhoto}" alt="Profile ptoho"/>
                    </div>
                    <div class="row">
                        <h4><a href="${feedLink}">${currentUser.fullName}</a></h4>
                    </div>

                    <div class="row">
                        <p class="small-font">
                            Here you can see requests which were sent to you by other users.
                        </p>
                    </div>

                    <div class="row">
                        <h5>
                            <c:choose>
                                <c:when test="${newRequests.size()>0}">
                                    New friends requests
                                </c:when>
                                <c:otherwise>
                                    No friend requests
                                </c:otherwise>
                            </c:choose>
                        </h5>
                    </div>

                    <!-- new friend requests -->
                    <c:forEach items="${newRequests}" var="newRequest" >
                        <kivbook:friend-req friendRequest="${newRequest}" isReceived="true"/>
                    </c:forEach>
                </div>


                <!-- user's friends -->
                <div class="col-md-6">
                    <h4>My friends</h4>
                    <div class="card">
                        <div class="card-body">
                            <c:set var="rowItemCount" value="0" scope="page" />
                            <c:forEach items="${friendships}" var="friendship">
                                <c:set var="otherUser" value="${friendship.sender}"/>
                                <%-- start new row after 4 friendships to make table-like structure --%>
                                <c:if test="${rowItemCount % 4 ==0}">
                                    <div class="row">
                                </c:if>


                                <%-- display element --%>
                                <div class="col-md-3">
                                    <div class="card card-borderless">
                                        <div class="card-body user-panel">
                                            <div class="row justify-content-center">
                                                <a href="${profileLink}/${otherUser.username}">
                                                    <kivbook:image kivbookImage="${otherUser.profilePhoto}" alt="${otherUser.fullName}" classes="medium-thumbnail"/>
                                                </a>
                                            </div>
                                            <div class="row justify-content-center">
                                                <a href="${profileLink}/${otherUser.username}">${otherUser.fullName}</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <%--increment counter--%>
                                <c:set var="rowItemCount" value="${rowItemCount+1}" scope="page"/>

                                <%-- close the row element after the 4th friendship --%>
                                <c:if test="${rowItemCount % 4 == 0 || rowItemCount == friendships.size()}">
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <!-- friend requests sent by user -->
                <div class="col-md-3">
                    <div class="row">
                        <h5>
                            <c:choose>
                                <c:when test="${pendingRequests.size()>0}">
                                    Requests waiting for response
                                </c:when>
                                <c:otherwise>
                                    No requests are waiting for response
                                </c:otherwise>
                            </c:choose>
                        </h5>
                    </div>

                    <div class="row">
                        <p class="small-font">
                            Here you can see request sent by you. Cancel them by clicking the 'X' button.
                            To make new friends go to user's profile (you can search them by their username) where you can send friend requests to them.
                        </p>
                    </div>

                    <c:forEach items="${pendingRequests}" var="pendingRequest">
                        <kivbook:friend-req friendRequest="${pendingRequest}" isReceived="false"/>
                    </c:forEach>

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