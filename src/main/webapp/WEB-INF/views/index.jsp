<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="kivbook" tagdir="/WEB-INF/tags" %>


<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/register"  var="registerLink"/>

<!DOCTYPE html>
<html lang="en">
  <kivbook:head title="My cool social network"/>
  <body class="bg-light">
    <kivbook:navbar isAnonymous="true"/>
  	<div class="container">
  		<div class="row">
  			<div class="col-md-4">
  				<form:form method="post" action="${registerLink}" modelAttribute="userForm" >
                    <fieldset>
                        <legend>Register now!</legend>
                        <span class="small-font">* field is mandatory</span>
                        <div class="form-group">
                            <form:label path="username">username *</form:label>
                            <form:errors path="username" cssClass="text-danger" />
                            <form:input path="username" cssClass="form-control" maxlength="20" required="required"/>
                        </div>

                        <div class="form-group">
                            <form:label path="email">email *</form:label>
                            <form:errors path="email" cssClass="text-danger" />
                            <form:input path="email" id="email" cssClass="form-control" maxlength="100" required="required"/>
                        </div>

                        <div class="form-group">
                            <form:label path="password">password *</form:label>
                            <form:errors path="password" cssClass="text-danger" />
                            <form:password path="password" id="password" cssClass="form-control" maxlength="100" required="required"/>
                        </div>

                        <div class="form-group">
                            <form:label path="passwordConf">confirm password *</form:label>
                            <form:errors path="passwordConf" cssClass="text-danger" />
                            <form:password path="passwordConf" id="passwordConf" cssClass="form-control"  maxlength="100" required="required"/>
                        </div>

                        <div class="form-group">
                            <form:label path="fullName">full name *</form:label>
                            <form:errors path="fullName" cssClass="text-danger" />
                            <form:input path="fullName" id="fullName" cssClass="form-control"  maxlength="20" required="required"/>
                        </div>

                        <div class="form-group">
                            <form:label path="birthDate">birth date</form:label>
                            <form:errors path="birthDate" cssClass="text-danger" />
                            <form:input type="date" path="birthDate" id="birthDate" cssClass="form-control" />
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <form:label path="gender">Gender *</form:label>
                                <form:errors path="gender" cssClass="text-danger" />
                            </div>
                            <div class="col-md-6">
                                <div class="radio">
                                    <label><form:radiobutton path="gender" value="male" id="male" />Male</label>
                                </div>
                                <div class="radio">
                                    <label><form:radiobutton path="gender" value="female" id="female" />Female</label>
                                </div>
                                <div class="radio">
                                    <label><form:radiobutton path="gender" value="mayo" id="mayo" />Mayonnaise</label>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <form:label path="acceptTerms">Accept terms of use *</form:label>
                            <form:checkbox path="acceptTerms" />
                            <form:errors path="acceptTerms" cssClass="text-danger" />
                        </div>

                        <div class="form-group">
                            <c:if test="${isTuringFailed}">
                                <span class="text-danger">You are a robot!1!</span>
                            </c:if>
                            <label for="turingTest">Are you a robot? What day is today? *</label>
                            <p class="small-font">English months, lowercacse only.</p>
                            <input type="text" id="turingTest" name="turingTest" class="form-control" required>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                            </div>
                            <div class="col-md-6">
                                <input type="submit" value="Sell my soul" class="btn btn-success">
                            </div>
                        </div>

                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                    </fieldset>
  				</form:form>
  			</div>
  			<div class="col-md-8">
                <div class="jumbotron">
                    <h1>What are you waiting for?</h1>
                    <p>
                        Join the best social network today! Just fill in your personal info and join our awesome community!
                        You will be able to share your thoughts, opinios, photos, videos, ... with your friends, friends of their frends and complete strangers also! Join us today!
                    </p>
                    <p class="text-right"><fmt:formatDate value="${currDate}" pattern="dd.MM.YYYY"/></p>
                </div>

                <div class="page-header">
                    <h3>So, what is this all about?</h3>
                </div>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
                <p class="text-right"><i>2017, valesz</i></p>
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