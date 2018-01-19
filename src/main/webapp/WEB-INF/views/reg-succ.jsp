<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/login" var="loginLink" />
<c:url value="/register" var="registerLink"/>

<!DOCTYPE html>
<html lang="en">
  <kivbook:head title="I'm going in!"/>
  <body class="bg-light">
    <kivbook:navbar isAnonymous="true"/>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
            </div>
            <div class="col-md-6 text-center">
                <h3>Congratulations! You are now a member of our family!</h3>
                <p>
                    You can now <a href="${loginLink}">login</a>.
                </p>
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