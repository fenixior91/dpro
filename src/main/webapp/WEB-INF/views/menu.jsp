<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    	<img src="<c:url value='/static/images/admin.png'/>" class="user-image" alt="Admin Image">
                        <span class="hidden-xs">Admin</span>
                     </a>
          			 <ul class="dropdown-menu">
                     	<li class="user-header">
                        	<img src="<c:url value='/static/images/admin.png'/>" class="img-circle" alt="Admin Image">
                            <p>Admin</p>
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
            	<img src="<c:url value='/static/images/admin.png'/>" class="img-circle" alt="Admin Image">
            </div>
        	<div class="pull-left info">
        		<p>Administrator</p>
            	<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        	</div>
     	</div>
        <ul class="sidebar-menu">
        	<li>
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
                	<i class="fa fa-circle-o"></i> <span>WykÅadowcy</span>
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