<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/login" var="loginLink" />
<c:url value="/register" var="registerLink" />

<!DOCTYPE html>
<html lang="en">
  <head>
    <kivbook:head title="I'm going in!" />
  </head>
  <body class="bg-light">
    <kivbook:navbar isAnonymous="true"/>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
            </div>
            <div class="col-md-6">
                <form method="post" action="login">
                    <fieldset>
                        <div class="form-group">
                            <label for="username">username</label>
                            <input type="text" name="username" id="username" class="form-control" placeholder="username">
                        </div>
                        <div class="form-group">
                            <label for="password">password</label>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                    </fieldset>

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="row">
                        <div class="col-md-3">
                            <input type="submit" value="Login" class="btn btn-success">
                        </div>
                        <div class="col-md-6"></div>
                        <div class="col-md-3">Or join <a href="${registerLink}">here...</a> </div>
                    </div>

                </form>
                
                <c:if test="${error != null}">
                    <span class="text-danger">Bad username and/or password.</span>
                </c:if>
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