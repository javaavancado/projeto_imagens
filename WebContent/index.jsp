<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form action="">
	<div style="width: 100%">

		<div style="float: left; width: 45%">
			<table>
				<tr>
					<td>Código</td>
				</tr>
				<tr >
					<td><input type="text" style="width: 80px;"></td>
				</tr>
				
				<tr>
					<td>Produto</td>
				</tr>
				<tr>
					<td><input type="text" style="width: 200px;"></td>
				</tr>
				
				<tr>
					<td>Fornecedor</td>
				</tr>
				<tr>
					<td>
						<select style="width: 200px;">
						  <option value="">Defina o fornecedor</option>
						  <option value="forn1">Fonecedor A</option>
						  <option value="fora2">Fornecedor B</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Miniatura</td>
				</tr>
				<tr>
					<td>
						<select style="width: 200px;">
						  <option value="">Defina o tamanho da miniatura</option>
						  <option value="200x300">200 x 300</option>
						  <option value="300x420">300 x 420</option>
						</select>
					</td>
				</tr>
			</table>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			 <input type="submit" value="Salvar">
		</div>

		<div style="float: right; width: 45%;">
		
		 <img src="" width="50%" style="min-height: 250px;" border="0">
		 <br/>
		 <input type="button" value="Selecionar a imagem">
		 <input type="button" value="Excluir imagem">
		</div>
	</div>
	
	</form>
</body>
</html>