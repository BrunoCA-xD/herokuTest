
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/Style.css">

</head>
<body>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="LoginServlet" method="post">
				Login: <input type="text" name="login" /> <br /> Senha: <input
					type="password" name="senha" /> <br />
				<button type="submit">Logar</button>
			</form>
		</div>
	</div>

</body>
</html>