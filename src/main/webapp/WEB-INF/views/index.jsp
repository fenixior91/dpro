<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Dziekanat PRO</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="<c:url value='/static/css/bootstrap/bootstrap.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/font-awesome/font-awesome.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/custom/AdminLTE.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/css/custom/_all-skins.min.css'/>">
    </head>

    <body class="hold-transition lockscreen">
        <div class="lockscreen-wrapper">
            <div class="lockscreen-logo">
                <h3><b>Dziekanat</b> PRO</h3>
            </div>

            <a href="<c:url value='/login'/>" class="btn btn-block btn-lg btn-success">Zaloguj się</a>
        </div>
    </body>
</html>