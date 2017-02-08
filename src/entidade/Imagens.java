package entidade;

public class Imagens {

	private Long id;
	private String produto;
	private String fornecedor;
	private String miniatura;
	private String urlimagem;
	private String urlminiimg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
