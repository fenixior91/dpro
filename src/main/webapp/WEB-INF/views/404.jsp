<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>404</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="<c:url value='/static/css/bootstrap/bootstrap.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/font-awesome/font-awesome.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/custom/AdminLTE.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/icheck/blue.css'/>">
    </head>
    <body class="hold-transition lockscreen">
        <div class="lockscreen-wrapper">
            <div class="lockscreen-logo">
                <h3><i class="fa fa-warning text-red"></i> <b>404</b> Nie znaleziono strony!</h3>
            </div>
            <div class="lockscreen-name">Niestety strona którą szukasz nie isnieje!</div>

            <div class="text-center">
                <a href="<c:url value='/'/>">Wróć na stronę główną</a>
            </div>
        </div>
    </body>
</html>