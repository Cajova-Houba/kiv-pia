<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/register"  var="registerLink"/>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${myStyle}">
    <title>My cool social network</title>
  </head>
  <body class="bg-light">
    <nav class="navbar navbar-dark bg-green">
        <div class="container">
            <div class="navbar-header">
                <h2><a href="${registerLink}">Cool social network</a></h2>
            </div>

            <a href="login" class="nav-link"><span class="glyphicon glyphicon-log-in"></span>Going in!</a>
        </div>
    </nav>
  	<div class="container">
  		<div class="row">
  			<div class="col-md-4">
  				<form method="post" action="${registerLink}">
                    <fieldset>
                        <legend>Register now!</legend>
                        <div class="form-group">
                            <label for="username">username</label>
                            <c:if test="${errUsernameWrong || errUsernameExists}">
                                <label for="username" class="text-danger">Wrong username!</label>
                            </c:if>
                            <input type="text" name="username" id="username" class="form-control" placeholder="username" maxlength="255">
                        </div>

                        <div class="form-group">
                            <label for="email">email</label>
                            <c:if test="${errWrongEmail}">
                                <label for="email" class="text-danger">Wrong email!</label>
                            </c:if>
                            <input type="text" name="email" id="email" class="form-control" placeholder="email@email.com" maxlength="255">
                        </div>

                        <div class="form-group">
                            <label for="password">password</label>
                            <c:if test="${errWrongPass}">
                                <label for="password" class="text-danger">Wrong password!</label>
                            </c:if>
                            <input type="password" name="password" id="password" class="form-control" placeholder="" maxlength="255">
                        </div>

                        <div class="form-group">
                            <label for="password-conf">confirm password</label>
                            <c:if test="${errPassDontMatch}">
                                <label for="password-conf" class="text-danger">Passwords don't match!</label>
                            </c:if>
                            <input type="password" name="password-conf" id="password-conf" class="form-control" placeholder="" maxlength="255">
                        </div>

                        <div class="form-group">
                            <label for="full-name">full name</label>
                            <c:if test="${errWrongFullName}">
                                <label for="full-name" class="text-danger">Wrong full name!</label>
                            </c:if>
                            <input type="text" id="full-name" name="full-name" class="form-control" placeholder="Bobby McJohnson" maxlength="500">
                        </div>

                        <div class="form-group">
                            <label for="birth-date">birth date</label>
                            <c:if test="${errTooYoungMan}">
                                <label for="birth-date" class="text-danger">Sorry man, you're too young...</label>
                            </c:if>
                            <input type="date" id="birth-date" class="form-control">
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <label>Gender</label>
                                <c:if test="${errNotAGender}">
                                    <label class="text-danger">That's not a gender. Not at all.</label>
                                </c:if>
                            </div>
                            <div class="col-md-6">
                                <div class="radio">
                                    <label><input type="radio" name="gender" id="male" checked>Male</label>
                                </div>
                                <div class="radio">
                                    <label><input type="radio" name="gender" id="female">Female</label>
                                </div>
                                <div class="radio">
                                    <label><input type="radio" name="gender" id="mayo">Mayonnaise</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="accept-terms">Accept terms of use </label>
                            <input type="checkbox" id="accept-terms" name="accept-terms">
                            <c:if test="${errShutUpAndAccept}">
                                <label for="accept-terms" class="text-danger">How can you have any fun if you don't accept our terms?</label>
                            </c:if>
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
  				</form>
  			</div>
  			<div class="col-md-8">
                <div class="jumbotron">
                    <h1>What are you waiting for?</h1>
                    <p>
                        Join the best social network today! Just fill in your personal info and join our awesome community!
                        You will be able to share your thoughts, opinios, photos, videos, ... with your friends, friends of their frends and complete strangers also! Join us today!
                    </p>
                    <p class="text-right">${currDate}</p>
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

    <footer class="footer">
        <div class="container text-center">
            Cool Social Network - Zdeněk Valeš 2017
        </div>
    </footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
  </body>
</html>