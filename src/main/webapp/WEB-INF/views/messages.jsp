<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/css/style.css" var="myStyle" />
<c:url value="/feed" var="feedLink"/>
<c:url value="/logout" var="logoutLink"/>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${myStyle}">
    <title>Messages</title>
  </head>
  <body class="bg-light">
    <nav class="navbar navbar-dark bg-green">
        <div class="container">
            <div class="navbar-header">
                <h2><a href="${feedLink}">Cool social network</a></h2>
            </div>
            <form class="form-inline">
                <input type="text" name="search" placeholder="search" class="form-control mr-sm-2">
                <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Search</button>
            </form>

            <a href="${logoutLink}" class="nav-link"><span class="glyphicon glyphicon-log-in"></span>Time to go...</a>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-4 user-panel">
                <div class="row">
                    <img src="img/profile_photo.png" class="img-thumbnail" alt="Profile photo">
                </div>
                <div class="row">
                    <h4><a href="${feedLink}">Pepa Uživatel</a></h4>
                </div>

                <div class="row">
                    <h5>Conversations</h5>
                </div>

                <div class="row">
                    <div class="card conversation-card">
                        <div class="card-body">
                            <a href="#"><b>+</b> New message</a>
                        </div>
                    </div>
                </div>

                <!--panel s náhledem konverzace -->
                <div class="row">
                    <div class="card conversation-card conversation-card-selected">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="img/poster_photo.png" class="img-thumbnail" alt="Poster photo" height="16">
                                </div>
                                <div class="col-md-9">
                                    <div class="row">
                                        <b>Hustej uživatel</b>  
                                    </div>
                                    <div class="row">
                                        Tak co, jak se máš?
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--panel s náhledem konverzace -->
                <div class="row">
                    <div class="card conversation-card card-unread">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="img/mommy_photo.png" class="img-thumbnail" alt="Poster photo" height="16">
                                </div>
                                <div class="col-md-9">
                                    <div class="row">
                                        <b>Maminečka</b>  
                                    </div>
                                    <div class="row">
                                        Už jsi dneska jedl?
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--panel s náhledem konverzace -->
                <div class="row">
                    <div class="card conversation-card card-unread">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="img/profile_photo.png" class="img-thumbnail" alt="Poster photo" height="16">
                                </div>
                                <div class="col-md-9">
                                    <div class="row">
                                        <b>Někdo úplně jinej</b>  
                                    </div>
                                    <div class="row">
                                        Hi, you just won 1 MILLION DOLLARS!!!
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--panel s náhledem konverzace -->
                <div class="row">
                    <div class="card conversation-card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="img/profile_photo.png" class="img-thumbnail" alt="Poster photo" height="16">
                                </div>
                                <div class="col-md-9">
                                    <div class="row">
                                        <b>Kámoš</b>  
                                    </div>
                                    <div class="row">
                                        Tahle zpráva je už dávno přečtená.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


            <div class="col-md-8">
                <h4>Hustej uživatel</h4>
                <div class="card">
                    <div class="card-body">
                        <!-- <div class="row">
                            <h4>Hustej uživatel</h4>
                        </div> -->
                        <div class="row">
                            <span class="text-muted">12:47</span>
                            <p class="other-message">
                                Čau, já sem hustej uživatel.
                            </p>
                        </div>

                        <div class="row">
                            <p class="my-message">
                                Čau, já jsem Pepa. Jak se máš?
                            </p>
                            <span class="text-muted">13:21</span>
                        </div>

                        <div class="row">
                            <span class="text-muted">13:25</span>
                            <p class="other-message">
                                Ale jo, dá se. Co ty?
                            </p>
                        </div>

                        <div class="row">
                            <p class="my-message">
                                No nic moc. Už musim jít, tak čau.
                            </p>
                            <span class="text-muted">13:30</span>
                        </div>

                        <div class="row">
                            <span class="text-muted">13:32</span>
                            <p class="other-message">
                                Čau.   
                            </p>
                        </div>

                        <!-- different date -->
                        <hr>
                        <div class="row">
                            <p class="text-muted text-center full-width">8.1. 2017</p>
                        </div>

                        <div class="row">
                            <span class="text-muted">7:30</span>
                            <p class="other-message">
                                Čau.   
                            </p>
                        </div>

                        <div class="row">
                            <span class="text-muted">7:31</span>
                            <p class="other-message">
                                Tak co, jak se máš?
                            </p>
                        </div>

                        <div class="row">
                            <span class="text-muted">7:35</span>
                            <p class="other-message">
                                Tady ti posílám vážně dlouhou zprávu:
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                            </p>
                        </div>

                        <div class="row">
                            <p class="my-message">
                                Dík no.
                            </p>
                            <span class="text-muted">8:00</span>
                        </div>
                    </div>
                </div> <!-- end of message card -->

                

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