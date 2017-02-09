<%@page import="entidade.Imagens"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/css.css" rel="stylesheet" />
</head>
<body>
	<form action="controllerImagem" id="formImagem"
		enctype="application/x-www-form-urlencoded" method="post">

		<input type="hidden" name="urlimagemBase64" id="urlimagemBase64"
			value="${imagem.urlimagem}" /> 
			
			<input type="hidden"
			name="urlminiimgBase64" id="urlminiimgBase64"
			value="${imagem.urlminiimg}" />

		<div style="width: 100%">

			<div style="float: left; width: 45%">
				<table>
					<tr>
						<td>Código</td>
					</tr>
					<tr>
						<td>
						<input readonly="readonly" type="text" style="width: 80px;" id="id" name="id"
							value="<c:out value="${imagem.id}" />">
						</td>
					</tr>

					<tr>
						<td>Produto</td>
					</tr>
					<tr>
						<td>
						<input type="text" style="width: 200px;" id="produto"
							name="produto" value="<c:out value="${imagem.produto}" />">
						</td>
					</tr>

					<tr>
						<td>Fornecedor</td>
					</tr>
					<tr>
						<td>
						<select style="width: 200px;" id="fornecedor" name="fornecedor">
								<option value="nao_informado">Defina o fornecedor</option>
								<option value="Fonecedor A"
								
								<% 
									Imagens imagem = (Imagens) request.getAttribute("imagem");
									if (imagem != null && imagem.getFornecedor().equalsIgnoreCase("Fonecedor A")) {
										 out.print("selected=\"selected\"");
									} 
								%>>Fonecedor A</option>
								
								<option value="Fonecedor B"
								<% 
									if (imagem != null && imagem.getFornecedor().equalsIgnoreCase("Fonecedor B")) {
										 out.print("selected=\"selected\"");
									} 
								%>>Fonecedor B</option>
						</select></td>
					</tr>
					<tr>
						<td>Miniatura</td>
					</tr>
					<tr>
						<td><select style="width: 200px;" id="tamanhomini" name="tamanhomini">
								<option value="50x50">Defina o tamanho da miniatura</option> 
								
								<option value="200x300"
								<% 
									if (imagem != null && imagem.getMiniatura().equalsIgnoreCase("200x300")) {
										 out.print("selected=\"selected\"");
									} 
								%> >200 x 300</option>
								
								<option value="300x420"
								<% 
									if (imagem != null && imagem.getMiniatura().equalsIgnoreCase("300x420")) {
										 out.print("selected=\"selected\"");
									} 
								%>>300 x 420</option>
						</select></td>
					</tr>
				</table>
				<br /> <br /> <br /> <br />
				    <input type="submit" onclick="document.getElementById('formImagem').action = 'controllerImagem?acao=salvar'"
					value="Salvar"> 
					<input type="submit"  onclick="document.getElementById('formImagem').action = 'controllerImagem?acao=deletar&id=${imagem.id}'"
					value="Excluir">
					<input type="submit"  onclick="document.getElementById('formImagem').action = 'controllerImagem?acao=todos'"
					value="Todos">
			</div>

			<div style="float: right; width: 45%;">
				<img src="<c:out value="${imagem.urlimagem}"/>" width="50%"
					style="min-height: 250px;" border="0" id="target" name="target"> 
					<br />
					<input type="button" value="Excluir imagem" onclick="deletaFoto();">
					 <input 
					onchange="visualizarImg();" type="file" value="Selecionar a imagem">
			</div>
		</div>

	</form>
	
	<br/>
	<br/>

<form method="get" action="controllerImagem">	
<div align="left" style="width: 100%;">
	<table align="left" style="widows: 100%">
		<c:forEach items='${listadeimagens}' var='imagem'>
			<tr>
				<td style="width: 70px;" align="left"><c:out value="${imagem.id}" /></td>
				<td align="left"> <img border="0" width="100" height="100" alt="Imagem" src="<c:out value="${imagem.urlminiimg}" />"> </td>
				<td style="width: 200px" align="left"><c:out value="${imagem.produto}" /></td>
				<td style="width: 200px" align="left"><c:out value="${imagem.fornecedor}" /></td>
				<td align="right" style="width: 150px"><a
					href="controllerImagem?acao=editar&codigoImg=<c:out value="${imagem.id}"/>">Editar</a>
				</td>
				<td align="right" style="width: 150px"><a
					href="controllerImagem?acao=deletar&codigoImg=<c:out value="${imagem.id}"/>">Excluir</a>
				</td>
				
				<td align="right" style="width:150px"><a
					href="imagemDownload?imagem=mini&codigoImg=<c:out value="${imagem.id}"/>">Miniatura</a>
				</td>
				
				<td align="right" style="width: 150px"><a
					href="imagemDownload?imagem=orig&codigoImg=<c:out value="${imagem.id}"/>">Original</a>
				</td>
			</tr>
			<br />
		</c:forEach>

	</table>
	
</div>	
</form>
</body>

<script type="text/javascript">
	// transforma a imagem em base64 e mostra no navegador
	function visualizarImg() {
		var preview = document.querySelector('img'); /// pega o campo de imagem
		var file = document.querySelector('input[type=file]').files[0]; // pega o primeiro input que armazena a imagem em base 64
		var reader = new FileReader();

		reader.onloadend = function() {
			preview.src = reader.result;// carrega em base64 a img
			document.getElementById("urlimagemBase64").value = reader.result; // seta o valor da imagem ao intputtext urlimagemBase64
		};

		if (file) {
			reader.readAsDataURL(file); // faz o prewiew da imagem na tela	    
		} else {
			preview.src = "";
		}

	}

	// deleta a imagem do navegador
	function deletaFoto() {
		var preview = document.querySelector('img');
		preview.src = '';
		document.getElementById("urlimagemBase64").value = '';
		document.getElementById("target").src = '';

	}
</script>
</html>