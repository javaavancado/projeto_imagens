package entidade;

/**
 * Classe Responsável por representar o modelo de dados do banco de dados
 * @author alex
 *
 */
public class Imagens {

	// Cada atributo faz referencia a uma coluna no banco de dados com o nome igual
	private Integer id;
	private String produto;
	private String fornecedor;
	private String miniatura;
	private String urlimagem;
	private String urlminiimg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getMiniatura() {
		return miniatura;
	}

	public void setMiniatura(String miniatura) {
		this.miniatura = miniatura;
	}

	public String getUrlimagem() {
		return urlimagem;
	}

	public void setUrlimagem(String urlimagem) {
		this.urlimagem = urlimagem;
	}

	public String getUrlminiimg() {
		return urlminiimg;
	}

	public void setUrlminiimg(String urlminiimg) {
		this.urlminiimg = urlminiimg;
	}

}
