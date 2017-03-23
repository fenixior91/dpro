<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>403</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/static/css/font-awesome/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/static/css/custom/AdminLTE.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/static/css/icheck/blue.css'/>">
</head>
<body class="hold-transition lockscreen">
<div class="lockscreen-wrapper">
    <div class="lockscreen-logo">
        <h3><i class="fa fa-warning text-red"></i> <b>403</b> Odmowa dostępu!</h3>
    </div>
    <div class="lockscreen-name">Nie jesteś upoważniony, aby wejść na tę stronę!</div>

    <div class="text-center">
        Wróć na stronę główną, aby się <a href="<c:url value='/'/>">zalogować</a>
    </div>
</div>
</body>
</html>