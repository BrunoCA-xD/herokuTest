<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Produto</title>
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
			<form action="saveProduct" id="frmProduct" method="post"
				class="contact100-form validate-form">
				<span class="contact100-form-title">Produto </span> <input
					type="hidden" name="id" value="${product.id}" readonly="readonly" />
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter the product's name">
					<label for="nome" class="label-input100">Nome*: </label> <input
						class="input100" type="text" id="nome" name="nome"
						value="${product.nome}"
						placeholder="Please Type the product's Name" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter the product's amount">
					<label for="quantidade" class="label-input100">Quantidade*:
					</label> <input class="input100" type="number" step="0.01" min="0"
						id="quantidade" name="quantidade" value="${product.quantidade}"
						placeholder="Please Type product amount" />
				</div>
				<div class="wrap-input100 validate-input bg1"
					data-validate="Enter the product's price">
					<label class="label-input100" for="valor">Valor* R$:</label> <input
						class="input100" type="number" step="0.01" min="0" id="valor"
						name="valor" value="${product.valor}"
						placeholder="Please Type product price" />
				</div>

				<div class="container-contact100-form-btn">
					<div
						<c:out value='${product.id !=null  ? "class=col-sm-6" :"class=col-sm-12" }'/>>
						<button class="contact100-form-btn" style="display: inline;">
							<span> Salvar &nbsp;&nbsp;<i
								class="fas fa-long-arrow-alt-right"></i>
							</span>
						</button>
					</div>
					<c:if test="${product.id !=null }">
						<div class="col-sm-6">
							<button class="contact100-form-btn" style="display: inline;"
								onclick="document.getElementById('frmProduct').action = 'saveProduct?acao=reset';">
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
						<th>Quantidade</th>
						<th>Valor</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${produtos}" var="product">

						<tr>
							<td width="2"><a
								href="saveProduct?acao=delete&produto=${product.id}"><i
									class="far fa-trash-alt"></i></a></td>
							<td width="2"><a
								href="saveProduct?acao=edit&produto=${product.id}"><i
									class="far fa-edit"></i></a></td>
							<td><c:out value="${product.nome}"></c:out></td>
							<td><c:out value="${product.quantidade}"></c:out></td>
							<td><c:out value="${product.valor}"></c:out></td>
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
		<script type="text/javascript" src="resources/js/main.js"></script>
	<c:if test="${errorMsg !=null || successMsg !=null}">
		<script type="text/javascript">
			$('.toast').toast('show');
		</script>
	</c:if>
</body>
</html>