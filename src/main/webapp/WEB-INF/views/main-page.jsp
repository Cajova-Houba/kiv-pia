<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/feed" var="feedLink"/>
<c:url value="/logout" var="logoutLink"/>
<c:url value="/messages" var="messagesLink" />
<c:url value="/friends" var="friendsLink" />

<!DOCTYPE html>
<html lang="en">
  <kivbook:head title="Kivbook"/>
  <body class="bg-light">
    <kivbook:navbar isAnonymous="false"/>

    <div class="container">
        <div class="row">
            <div class="col-md-2 user-panel">
                <div class="row">
                    <kivbook:image kivbookImage="${currentUser.profilePhoto}" alt="Profile photo"/>
                </div>
                <div class="row">
                    <h4><a href="${feedLink}">${currentUser.fullName}</a></h4>
                </div>

                <kivbook:user-menu newMsgsCount="${newMsgs}" newFriendsCount="${newFriendReq}"/>

            </div>
            <div class="col-md-10">
                <div class="card post-panel">
                    <div class="card-body">
                        <form:form method="post" action="${feedLink}" modelAttribute="newPost">
                            <div class="row">
                                <div class="col-md-3">
                                    <kivbook:image kivbookImage="${currentUser.profilePhoto}" alt="Profile photo" classes="medium-thumbnail"/>
                                </div>
                                <div class="col-md-9">
                                    <form:input path="text" placeholder="Say something..." class="form-control" maxlength="1000" required="required"/>
                                </div>
                            </div>
                            <div class="row">  
                                <div class="col-md-3">
                                    <h5 class="card-title">${currentUser.fullName}</h5>
                                </div>
                                <div class="col-md-9">
                                    <div class="float-right">
                                        <form:label path="visibility" cssClass="text-muted small-font">Visible to</form:label>
                                        <form:select path="visibility" cssClass="from-control text-muted small-font">
                                            <form:options items="${allowedVisibilities}" id="visibilities" />
                                        </form:select>
                                        <input type="submit" name="submit-post" class="btn btn-success" value="Post!">
                                    </div>
                                </div>
                            </div>

                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </form:form>
                    </div>
                </div>

                <!-- pagination -->
                <%-- won't be displayed if there's only 1 page --%>
                <c:if test="${!(pageControls.firstPage && pageControls.lastPge)}">
                    <ul class="pagination">
                        <%-- 'previous' and 'next' buttons are enabled only if the current page is not first or last respectively --%>
                        <c:choose>
                            <c:when test="${pageControls.firstPage}">
                                <li class="page-item disabled">
                                    <span class="page-link">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="${feedLink}/${pageControls.prevPage}">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <%-- create page number list and highlight the current one --%>
                        <c:forEach items="${pageControls.pageNumbers}" var="pageNum">
                            <li class="page-item <c:if test="${pageNum == pageControls.currentPage}">active</c:if>" >
                                <a class="page-link" href="${feedLink}/${pageNum}">${pageNum}</a>
                            </li>
                        </c:forEach>

                        <%-- next button --%>
                        <c:choose>
                            <c:when test="${pageControls.lastPge}">
                                <li class="page-item disabled">
                                    <span class="page-link">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </span>
                                </li>                                </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="${feedLink}/${pageControls.nextPage}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </c:if>


                <c:forEach items="${posts}" var="post">
                    <div class="card post-panel">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <a href="#"><kivbook:image kivbookImage="${post.owner.profilePhoto}" alt="${post.owner.fullName}" classes="medium-thumbnail"/></a>
                                </div>
                                <div class="col-md-9">
                                    ${post.text}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3 profile-link">
                                    <h5 class="card-title"><a href="#">${post.owner.fullName}</a></h5>
                                </div>
                                <div class="col-md-9">
                                    <p class="text-right"><fmt:formatDate value="${post.datePosted}" pattern="dd. MM. yyyy"/></p>
                                </div>
                            </div>
                        </div>
                    </div>
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