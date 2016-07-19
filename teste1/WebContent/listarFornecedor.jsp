<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Fornecedor</title>
</head>
<body>

		<c:import url="menu.jsp" />

	<table style="border: 5; border-color: blue; width: 100%;">
		<jsp:useBean id="dao" class="dao.FornecedorDao" />



		<tr>
			<td bgcolor="#00FF00">Id</td>
			<td bgcolor="#00FF00">Nome</td>
			<td bgcolor="#00FF00">Cnpj</td>
			<td bgcolor="#00FF00">Endereco</td>
			<td bgcolor="#00FF00">Data Fundacao</td>


		</tr>

		<c:forEach var="fornecedor" items="${dao.listar}">
			<tr>
				<td bgcolor='#32CD32'>${fornecedor.id}</td>
				<td bgcolor='#32CD32'>${fornecedor.nome}</td>
				<td bgcolor='#32CD32'>${fornecedor.cnpj}</td>
				<td bgcolor="#00FF00">${fornecedor.endereco}</td>
				
				</td>
				
				<td bgcolor="#32CD32">
				
				
				<fmt:formatDate value="${fornecedor.dataFundacao}"pattern="dd/MM/yyyy" /></td>
				</td>
			</tr>

		</c:forEach>
	</table>
		


</body>
</html>