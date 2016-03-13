<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
<title>.: Sistema Ventas</title>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Inicia sesión para acceder
					Sistema de Ventas</h1>
				<div class="account-wall">
					<img class="profile-img" src="image/loginUser.png" alt="">
					<form class="form-signin" accept-charset="UTF-8" role="form"
						method="POST" action="LoginControllers?metodo=logueo">
						<input type="text" class="form-control" name="username"
							placeholder="usuario" required autofocus> <input
							type="password" class="form-control" placeholder="Password"
							name="password" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Ingresar</button>

						<br> <br> <br>
						<core:if test="${requestScope.validaLogin==1}">

							<div class="alert alert-info">Usuario y/o password
								incorrectos.</div>


						</core:if>
						<core:if test="${requestScope.validaLogin==2}">

							<div class="alert alert-info">El usuario se encuentra
								bloqueado, por favor comuniquese con el Administrador del
								Sistema.</div>


						</core:if>
						<core:if test="${requestScope.validaLogin==3}">
							<div class="alert alert-info">Ocurrio un error, por favor
								comuniquese con el Administrador del Sistema.</div>


						</core:if>

						<!-- <label class="checkbox pull-left"> <input type="checkbox"
							value="remember-me"> Remember me
						</label> <a href="#" class="pull-right need-help">Need help? </a><span
							class="clearfix"></span> -->
					</form>
				</div>
				<!-- <a href="#" class="text-center new-account">Create an account </a> -->
			</div>
		</div>
	</div>
	<!-- 
	<form action="LoginControllers" method="post">
		Enter username : <input type="text" name="username"> <BR>
		Enter password : <input type="password" name="password"> <BR>
		<input type="submit" />
	</form>
	 -->
</body>
</html>