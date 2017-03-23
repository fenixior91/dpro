<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Log in</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="<c:url value='/static/css/bootstrap/bootstrap.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/font-awesome/font-awesome.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/custom/AdminLTE.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/icheck/blue.css'/>">
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="/"><b>Dziekanat</b> PRO</a>
            </div>

            <div class="login-box-body">
                <p class="login-box-msg">Panel Logowania</p>

                <form action="<c:url value='/login'/>" method="post">
                    <div class="form-group has-feedback">
                        <input name="username" type="text" class="form-control" placeholder="Login">
                        <span class="fa fa-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input name="password" type="password" class="form-control" placeholder="Hasło">
                        <span class="fa fa-lock form-control-feedback"></span>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="row">
                        <div class="col-xs-12">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Zaloguj</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <script src="<c:url value='/static/js/jquery/jquery-2.2.3.min.js'/>"></script>
        <script src="<c:url value='/static/js/bootstrap/bootstrap.min.js'/>"></script>
        <script src="<c:url value='/static/js/icheck/icheck.min.js'/>"></script>
        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' // optional
                });
            });
        </script>
    </body>
</html>
