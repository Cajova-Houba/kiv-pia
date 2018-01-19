<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/logout" var="logoutLink"/>
<c:url value="/feed" var="feedLink"/>
<c:url value="/profile" var="profileLink"/>

<!DOCTYPE html>
<html lang="en">
    <kivbook:head title="Friends"/>
    <body class="bg-light">
        <kivbook:navbar isAnonymous="false"/>
        <div class="container">
            <div class="row">
                <div class="col-md-4 user-panel">
                    <div class="row">
                        <kivbook:image kivbookImage="${currentUser.profilePhoto}" alt="Profile ptoho"/>
                    </div>
                    <div class="row">
                        <h4><a href="${feedLink}">${currentUser.fullName}</a></h4>
                    </div>

                    <div class="row">
                        <p>
                            To make new friends go to user's profile (you can search them by their username) where you can send friend requests to them.
                        </p>
                    </div>

                    <div class="row">
                        <h5>New friend requests:</h5>
                    </div>

                    <!-- new friend requets -->
                    <c:forEach items="${newRequests}" var="newRequest" >
                        <c:set var="otherUser" value="${newRequest.sender}"/>
                        <div class="row">
                            <div class="card conversation-card card-unread">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <a href="${profileLink}/${otherUser.username}">
                                                <kivbook:image kivbookImage="${otherUser.profilePhoto}" alt="${otherUser.fullName}" classes="medium-thumbnail"/>
                                            </a>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="row">
                                                <a href="${profileLink}/${otherUser.username}"><b>${otherUser.fullName}</b></a>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <button class="btn btn-success">OK</button>
                                                </div>
                                                <div class="col-md-6">
                                                    <button class="btn btn-danger">X</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>


                <div class="col-md-8">
                    <h4>My friends</h4>
                    <div class="card">
                        <div class="card-body">
                            <c:set var="rowItemCount" value="0" scope="page" />
                            <c:forEach items="${friendships}" var="friendship">
                                <c:set var="otherUser" value="${friendship.getOtherUser(currentUser)}"/>
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