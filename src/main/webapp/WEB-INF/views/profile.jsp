<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/feed" var="feedLink"/>
<c:url value="/logout" var="logoutLink"/>
<c:url value="/login" var="loginLink"/>
<c:url value="/messages" var="messagesLink" />
<c:url value="/friends" var="friendsLink" />
<c:url value="/profile" var="profileLink" />
<c:url value="/register" var="registerLink" />

<!DOCTYPE html>
<html lang="en">
    <kivbook:head title="User profile"/>
    <body class="bg-light">
        <kivbook:navbar isAnonymous="${isAnonymous}"/>

        <div class="container">
            <div class="row">
                <div class="col-md-3 user-panel">
                    <div class="row">
                        <kivbook:image kivbookImage="${user.profilePhoto}" alt="Profile photo"/>
                    </div>
                    <div class="row">
                        <h4>
                            <c:choose>
                                <c:when test="${isAnonymous || !isCurrentUser}">
                                    ${user.fullName}
                                </c:when>
                                <c:otherwise>
                                    <a href="${feedLink}">${user.fullName}</a>
                                </c:otherwise>
                            </c:choose>
                        </h4>
                    </div>

                    <c:if test="${isCurrentUser}">
                        <kivbook:user-menu newMsgsCount="${newMsgs}" newFriendsCount="${newFriendReq}"/>
                    </c:if>

                </div>
                <div class="col-md-9">
                    <h3 class="bottom-margin">${user.fullName} profile</h3>
                    <%--
                        if the edit mode is off, display normal profile, otherwise display edit form
                        all info which is being displayed / edited in profile card is passed via ProfileUpdateForm object
                    --%>
                    <c:choose>
                        <c:when test="${isEditMode}">
                            <h4>Upload new profile photo</h4>
                            <p>Here you can upload new photo. The old one will be deleted (if it's not one of the default ones). Maximum size of your new photo is 100 kB.</p>
                            <span class="text-danger"><b>Warning:</b> you can upload any file you want (non-image) but you will have to face the consequences.</span>
                            <form method="POST" action="${profileLink}/${username}/edit/profile-photo?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" class="bottom-margin">
                                <div class="row bottom-margin">
                                    <div class="col-md-6">
                                        <label class="custom-file">
                                            <input type="file" id="file" name="file" class="custom-file-input" required>
                                            <span class="custom-file-control"></span>
                                        </label>
                                    </div>
                                    <c:if test="${isImageSizeError}">
                                        <div class="col-md-6">
                                            <span class="text-danger">Your photo is too big! Choose another one.</span>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="submit" value="Upload" class="btn btn-success"/>
                                    </div>

                                </div>
                            </form>
                            <h4>Profile edit</h4>
                            <form:form modelAttribute="userForm">
                                <div class="row">
                                    <%-- username is not editable --%>
                                    <div class="col-md-3">Username</div>
                                    <div class="col-md-9">${userForm.username}</div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">Email</div>
                                    <div class="col-md-6"><form:input path="email" maxlength="255"/></div>
                                    <div class="col-md-3"><form:errors path="email" cssClass="text-danger"/> </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">Full name</div>
                                    <div class="col-md-6"><form:input path="fullName" maxlength="255"/></div>
                                    <div class="col-md-3"><form:errors path="fullName" cssClass="text-danger"/> </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">Birth date</div>
                                    <div class="col-md-6"><form:input path="birthDate"/> </div>
                                    <div class="col-md-3"><form:errors path="birthDate" cssClass="text-danger"/> </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">Profile visibility</div>
                                    <div class="col-md-6"><form:select path="profileVisibility" items="${userForm.possibleVisibilities}"/> </div>
                                    <div class="col-md-3"><form:errors path="profileVisibility" cssClass="text-danger"/> </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <button type="submit" class="btn btn-success">Save</button>
                                    </div>
                                </div>
                            </form:form>
                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="col-md-3">Username</div>
                                <div class="col-md-9">${userForm.username}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">Email</div>
                                <div class="col-md-9">${userForm.email}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">Full name</div>
                                <div class="col-md-9">${userForm.fullName}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">Age</div>
                                <div class="col-md-9">${userForm.age}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">Gender</div>
                                <div class="col-md-9">${userForm.displayGender}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">Profile visibility</div>
                                <div class="col-md-9">${userForm.displayVisibility}</div>
                            </div>
                            <%-- display edit button for current user --%>
                            <c:if test="${isCurrentUser}">
                                <div class="row">
                                    <div class="col-md-12">
                                        <a href="${profileLink}/${user.username}/edit" class="btn btn-success">Edit</a>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${!isAnonymous && !isConnection && !isCurrentUser}">
                                <div class=row">
                                    <form:form action="${friendsLink}/send/${user.username}" method="POST">
                                        <button type="submit" class="btn btn-success">Send friend request</button>
                                    </form:form>
                                </div>
                            </c:if>
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