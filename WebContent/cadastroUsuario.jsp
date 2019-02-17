<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/util.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<!-- Position it -->
	<div
		style="position: fixed; bottom: 5px; right: 0; z-index: 1000000000">

		<!-- Then put toasts within -->

		<div class="toast" role="alert" aria-live="assertive"
			aria-atomic="true" data-autohide="false">
			<div class="toast-header">
				${errorMsg !=null ? '<i class="fas fa-exclamation-circle rounded mr-1" style="color:red;"></i>' 
				: successMsg !=null ? '<i class="fas fa-check-circle rounded mr-1" style="color:green;"></i>' : ''}
				<strong class="mr-auto">Meu sistema</strong>
				<button type="button" class="ml-1 mb-1 close" data-dismiss="toast"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="toast-body">${errorMsg !=null ? errorMsg : "" }
				${successMsg !=null ? successMsg : "" }</div>
		</div>
	</div>


	<div class="container-contact100">
		<div class="wrap-contact100">
			<form action="saveUser" id="frmUser" method="post"
				class="contact100-form validate-form">
				<span class="contact100-form-title">Usuário </span> <input
					type="hidden" name="id" value="${user.id}" readonly="readonly" />
				<div class="wrap-input100 bg1">
					<label for="nome" class="label-input100">Nome: </label> <input
						class="input100" type="text" id="nome" name="nome"
						value="${user.nome}" placeholder="Please Type Your Name" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter Your Login">
					<label for="login" class="label-input100">Login*: </label> <input
						class="input100 errorField" type="text" id="login" name="login"
						value="${user.login}" placeholder="Please Type Your Login" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter Your password">
					<label class="label-input100" for="senha">Senha*:</label> <input
						class="input100" type="password" id="senha" name="senha"
						value="${user.senha}" placeholder="Please Type Your password" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter Your phone number">
					<label for="fone" class="label-input100">Fone*: </label> <input
						class="input100" type="text" id="fone" name="fone"  value="${user.fone}"
						placeholder="Please Type Your phone" />
				</div>

				<div class="container-contact100-form-btn">
					<div
						<c:out value='${user.id !=null ? "class=col-sm-6" :"class=col-sm-12" }'/>>
						<button class="contact100-form-btn" style="display: inline;">
							<span> Salvar &nbsp;&nbsp;<i
								class="fas fa-long-arrow-alt-right"></i>
							</span>
						</button>
					</div>
					<c:if test="${user.id !=null }">
						<div class="col-sm-6">
							<button class="contact100-form-btn" style="display: inline;"
								onclick="document.getElementById('frmUser').action = 'saveUser?acao=reset';">
								<span> Cancelar </span>
							</button>
						</div>
					</c:if>
				</div>
			</form>
			<br /> <br /> <span class="contact100-form-title"> Listagem
			</span>
			<table class="table " style="text-align: center;">
				<thead>
					<tr>
						<th>Excluir</th>
						<th>Editar</th>
						<th>Nome</th>
						<th>Usuário</th>
						<th>Fone</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usuarios}" var="user">

						<tr>
							<td width="2"><a
								href="saveUser?acao=delete&usuario=${user.id}"><i
									class="far fa-trash-alt"></i></a></td>
							<td width="2"><a
								href="saveUser?acao=edit&usuario=${user.id}"><i
									class="far fa-edit"></i></a></td>
							<td><c:out value="${user.nome}"></c:out></td>
							<td><c:out value="${user.login}"></c:out></td>
							<td><c:out value="${user.fone}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>

	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="resources/js/main.js"> </script>
	<c:if test="${errorMsg !=null || successMsg !=null}">
		<script type="text/javascript">
			$('.toast').toast('show');
						
		</script>
	</c:if>
</body>
</html>