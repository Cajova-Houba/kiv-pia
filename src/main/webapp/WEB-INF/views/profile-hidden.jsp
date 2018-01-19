<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>


<!DOCTYPE html>
<html lang="en">
    <kivbook:head title="User profile"/>
    <body class="bg-light">
        <kivbook:navbar isAnonymous="${isAnonymous}"/>
        <div class="container">
            <div class="row">
                <div class="col-md-3">

                </div>
                <div class="col-md-6">
                    <h3 class="text-danger text-center">Requested profile cannot be displayed.</h3>
                    <c:choose>
                        <c:when test="${!isAnonymous && !isConnection}">
                            <%-- user is logged in and is not in any connection with the hidden user => display friendship button --%>
                            <div class="row text-xs-center">
                                Sorry, you can't view this profile, but you can send a friend request to the user!
                            </div>
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <kivbook:friend-btn username="${username}"/>
                                </div>
                                <div class="col-md-4"></div>
                            </div>
                        </c:when>
                        <c:when test="${!isAnonymous && isConnection}">
                            <%-- user is logged in but connection already exists --%>
                            <div class="row text-center">
                                Sorry, but the user hid his profile or haven't responded to your friend request yet.
                            </div>
                        </c:when>
                    </c:choose>
                </div>

                <div class="col-md-3">

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