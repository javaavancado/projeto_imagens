package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoImagens;
import entidade.Imagens;

/**
 * Controller Servlet que ir� controlar o cadastro das imagens
 * 
 * @author alex
 * 
 */
@WebServlet("/controllerImagem")
public class ControllerImagem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoImagens daoImagens = new DaoImagens();

	public ControllerImagem() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String id = request.getParameter("codigoImg");
		if (acao.equalsIgnoreCase("editar")) {// se estiver editando
			Imagens imagensEditar = daoImagens.consulta(Integer.parseInt(id));
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("imagem", imagensEditar);
			view.forward(request, response);
		}else if (acao.equalsIgnoreCase("deletar")) {// se for deletar
			try {
				daoImagens.deleta(id);
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("listadeimagens", daoImagens.consultaTodos());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			String id = request.getParameter("id");

			if (acao.equalsIgnoreCase("todos")) {// mostrar todos
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("listadeimagens", daoImagens.consultaTodos());
				view.forward(request, response);
			} 
			else if (acao.equalsIgnoreCase("deletar")) {// se for deletar
				daoImagens.deleta(id);
			} else {

				String produto = request.getParameter("produto");
				String fornecedor = request.getParameter("fornecedor");
				String urlimagemBase64 = request
						.getParameter("urlimagemBase64");
				String tamanhomini = request.getParameter("tamanhomini");
				// -------------------------------------------------------

				// --------------Seta os parametros para o objeto
				Imagens imagens = new Imagens();
				imagens.setId(id != null && !id.isEmpty() && id.trim() != null ? Integer.parseInt(id) : null);
				imagens.setProduto(produto);
				imagens.setFornecedor(fornecedor);
				imagens.setMiniatura(tamanhomini);
				imagens.setUrlimagem(urlimagemBase64);// seta a imagem original 
				
				//----------------------Criando miniatura------------------------------
				String miniImgBase64 = null;
				 if(urlimagemBase64 != null && !urlimagemBase64.isEmpty()){
					// pega s� a parte da imagem original em base64
					String base64Image = urlimagemBase64.split(",")[1];
				    
					//Convertendo para  byte[] usando lib apache
					byte[] imageBytes = new Base64().decodeBase64(base64Image);
					
					// Transformando em BufferedImage 
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
					
					// pega o tipo da imagem
					int type = bufferedImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					
					int largura = Integer.parseInt(tamanhomini.split("x")[0]);
					int altura = Integer.parseInt(tamanhomini.split("x")[1]);
					
					BufferedImage resizedImage = new BufferedImage(largura, altura, type);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, largura, altura, null);
					g.dispose();
					
					 // escrevendo novamente a imagem em tamanho menor
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
				    ImageIO.write(resizedImage, "png", baos);
				    
				    
				    // escreve um arquivo com a miniatura
				    //ImageIO.write(resizedImage, "png", new File("c:\\miniatura.jpg")); 
				    
				    // monta novamente a base64 completa
				    miniImgBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
					//--------------------------------------Fim da cria��o da miniatura---------------------------------------------
				 }
			    
			    imagens.setUrlminiimg(miniImgBase64);// seta a miniatura em base64

				// ----------------salva no banco de dados----------------
				daoImagens.salvarOuAtualizar(imagens);

			}
			// ----------------consulta todos e requireciona para index.jsp------------
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("listadeimagens", daoImagens.consultaTodos());
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
