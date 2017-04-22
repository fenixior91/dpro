<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Edycja Przedmiotu</title>
        <meta
            content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
            name="viewport">
        <%@include file="css-include.jsp"%>
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <%@include file="menu.jsp"%>

            <div class="content-wrapper">
                <section class="content">
                    <div class="row">
                        <div class="col-xs-3"></div>
                        <div class="col-xs-6">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Edytuj Przedmiot</h3>
                                </div>

                                <form role="form" method="post" action="<c:url value='/admin/subject/update'/>" autocomplete="off">
                                    <div class="box-body">
                                        <input id="id" name="id" type="hidden" value="${subject.id}">
                                        <div class="form-group">
                                            <label for="subjectName">Nazwa przedmiotu</label>
                                            <input id="subjectName" name="subjectName" type="text" class="form-control" value="${subject.name}">
                                        </div>

                                        <div class="form-group">
                                            <label for="ects">Punkty ECTS</label>
                                            <input id="ects" name="ects" type="number" class="form-control" min="0" value="${subject.ects}">
                                        </div>


                                        <div class="form-group">
                                            <label for="hours">Liczba godzin</label>
                                            <input id="hours" name="hours" type="number" class="form-control" min="0" value="${subject.hours}">
                                        </div>

                                        <div class="form-group">
                                            <label for="subjectType">Typ przedmiotu</label>
                                            <select id="subjectType" name="subjectTypeId" class="form-control">
                                                <c:forEach var="subjectType" items="${subjectTypes}">
                                                    <option value="${subjectType.id}">${subjectType.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Aktualizuj</button>
                                        </div>
                                    </div>
                                </form>						
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>

        <%@include file="js-include.jsp"%>
    </body>
</html>