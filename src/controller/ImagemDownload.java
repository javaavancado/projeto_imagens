package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoImagens;
import entidade.Imagens;

/**
 * Responsável por fazer o download das imagens originais e miniaturas
 * 
 * @author alex
 * 
 */
@WebServlet("/imagemDownload")
public class ImagemDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Dao que realizar as operações com o banco de dados
	private DaoImagens daoImagens = new DaoImagens();

	public ImagemDownload() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tipo = request.getParameter("imagem");
		String codigo = request.getParameter("codigoImg");

		Imagens imagens = null;
		String imagemBase64 = null;
		if (tipo.equalsIgnoreCase("mini")) {// consulta imagem miniatura
			imagens = daoImagens.consultaMiniatura(Integer.parseInt(codigo));
			imagemBase64 = imagens.getUrlminiimg();
		} else {// consulta imagem original
			imagens = daoImagens.consultaOriginal(Integer.parseInt(codigo));
			imagemBase64 = imagens.getUrlimagem();
		}

		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=imagem.png");
		
		//Pega só a parte da imagem original em base64
		String base64Image = imagemBase64.split(",")[1];
	    
		//Convertendo para  byte[] usando lib apache
		byte[] imageBytes = new Base64().decodeBase64(base64Image);

		InputStream is = new ByteArrayInputStream(imageBytes);

		int read = 0;
		byte[] bytes = new byte[1024];
		OutputStream os = response.getOutputStream();

		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
