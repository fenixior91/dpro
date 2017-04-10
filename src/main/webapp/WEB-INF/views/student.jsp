<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Student | Dashboard</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="<c:url value='/static/css/bootstrap/bootstrap.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/font-awesome/font-awesome.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/custom/AdminLTE.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/custom/_all-skins.min.css'/>">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <header class="main-header">
                <a class="logo">
                    <span class="logo-mini"><b>D</b>PRO</span>
                    <span class="logo-lg"><b>Dziekanat</b> PRO</span>
                </a>

                <nav class="navbar navbar-static-top">
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="<c:url value='/static/images/student.png'/>" class="user-image" alt="Student Image">
                                    <span class="hidden-xs">USER</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header">
                                        <img src="<c:url value='/static/images/student.png'/>" class="img-circle" alt="Student Image">
                                        <p>USER</p>
                                    </li>

                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="" class="btn btn-default btn-flat">Profil</a>
                                        </div>
                                        <div class="pull-right">
                                            <form action="<c:url value='/logout'/>" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <button type="submit" class="btn btn-default btn-flat">Wyloguj</button>
                                            </form>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>

            <aside class="main-sidebar">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<c:url value='/static/images/student.png'/>" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Student</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href="<c:url value='/admin'/>">
                                <i class="fa fa-circle-o"></i> <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value='/students'/>">
                                <i class="fa fa-circle-o"></i> <span>Studenci</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fa fa-circle-o"></i> <span>Wykładowcy</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fa fa-circle-o"></i> <span>Kierunki</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fa fa-circle-o"></i> <span>Przedmioty</span>
                            </a>
                        </li>
                    </ul>
                </section>
            </aside>

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Profil</h1>
                </section>

                <section class="content">

                </section>
            </div>
        </div>

        <script src="<c:url value='/static/js/jquery/jquery-2.2.3.min.js'/>"></script>
        <script src="<c:url value='/static/js/bootstrap/bootstrap.min.js'/>"></script>
        <script src="<c:url value='/static/js/icheck/icheck.min.js'/>"></script>
        <script src="<c:url value='/static/js/vue/vue.min.js'/>"></script>
        <script src="<c:url value='/static/js/app/app.js'/>"></script>
    </body>
</html>