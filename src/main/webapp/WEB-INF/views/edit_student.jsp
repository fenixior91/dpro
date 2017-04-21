<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Edycja Studenta</title>
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
                        <div class="col-xs-6">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Edytuj Studenta</h3>
                                </div>

                                <form role="form" method="post" action="<c:url value='/admin/student/update'/>" autocomplete="off">
                                    <div class="box-body">
                                        <input id="id" name="id" type="hidden" value="${student.id}">
                                        <div class="form-group">
                                            <label for="username">Login</label>
                                            <input id="username" name="username" type="text" class="form-control" value="${student.username}"> 
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Hasło</label>
                                            <input id="password" name="password" type="password" class="form-control" value="${student.password}">
                                        </div>
                                        <div class="form-group">
                                            <label for="firstName">Imię</label>
                                            <input id="first_name" name="firstName" type="text" class="form-control" value="${student.firstName}">
                                        </div>
                                        <div class="form-group">
                                            <label for="lastName">Nazwisko</label>
                                            <input id="last_name" name="lastName" type="text" class="form-control" value="${student.lastName}">
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input id="email" name="email" type="email" class="form-control" value="${student.email}">
                                        </div>
                                        <div class="form-group">
                                            <label for="dateOfBirth">Data Urodzenia</label>
                                            <input id="date_of_birth" name="dateOfBirth" type="date" class="form-control" value="${student.dateOfBirth}">
                                        </div>

                                        <div class="form-group">
                                            <label for="pesel">PESEL</label>
                                            <input id="pesel" name="pesel" type="text" class="form-control" value="${student.pesel}">
                                        </div>

                                        <div class="form-group">
                                            <label for="album">Numer Albumu</label>
                                            <input id="album" name="album" type="album" class="form-control" value="${student.album}">
                                        </div>

                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Aktualizuj</button>
                                        </div>
                                    </div>
                                </form>						
                            </div>
                        </div>

                        <div class="col-xs-6">
                            <div class="box box-primary">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Przypisz Przedmioty</h3>
                                </div>

                                <form role="form" method="post" action="<c:url value='/admin/instructor/attach_subjects'/>" autocomplete="off">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label for="username">Login</label>
                                            <input id="username" name="username" type="text" class="form-control" value=""> 
                                        </div>
                                        <div class="form-group">
                                            <label for="password">Hasło</label>
                                            <input id="password" name="password" type="password" class="form-control" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="first_name">Imię</label>
                                            <input id="first_name" name="first_name" type="text" class="form-control" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="last_name">Nazwisko</label>
                                            <input id="last_name" name="last_name" type="text" class="form-control" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input id="email" name="email" type="email" class="form-control" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="date_of_birth">Data Urodzenia</label>
                                            <input id="date_of_birth" type="date" class="form-control" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="science_degree">Stopień naukowy</label>
                                            <input id="science_degree" name="science_degree" type="text" class="form-control" value="">
                                        </div>

                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Dodaj</button>
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