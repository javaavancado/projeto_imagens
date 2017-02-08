package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoImagens;
import entidade.Imagens;

/**
 * Controller Servlet que irá controlar o cadastro das imagens
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

			if (acao.equalsIgnoreCase("deletar")) {// se for deletar
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
				imagens.setUrlimagem(urlimagemBase64);
				imagens.setUrlminiimg(urlimagemBase64);
				// -----------------------------------------------------

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
