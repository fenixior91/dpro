<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Przedmioty</title>
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
        <%@include file="css-include.jsp"%>
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <%@include file="menu.jsp"%>

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Przedmioty</h1>
                </section>

                <section class="content">
                    <div class="callout callout-info">
                        <a href="<c:url value='/admin/subject/create'/>" class="btn btn-success">Dodaj Przedmiot</a>
                        <a href="<c:url value='/admin/subject_type/create'/>" class="btn btn-success">Dodaj Typ Przedmiotu</a>
                    </div>

                    <div class="row">
                        <c:forEach items="${subjects}" var="subject">
                            <div class="col-md-3">
                                <div class="box box-primary">
                                    <div class="box-body box-profile">
                                        <h3 class="profile-username text-center">${subject.name}</h3>

                                        <p class="text-muted text-center">${subject.subjectType.name}</p>
                                        <a href="<c:url value='/admin/subject/show/${subject.id}'/>" class="btn btn-primary btn-block"><b>Pokaż</b></a>
                                        <a href="<c:url value='/admin/subject/edit/${instructor.id}'/>" class="btn btn-success btn-block"><b>Edytuj</b></a>
                                        <a href="<c:url value='/admin/subject/delete/${instructor.id}'/>" class="btn btn-danger btn-block"><b>Usuń</b></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </div>

        <%@include file="js-include.jsp"%>
    </body>
</html>