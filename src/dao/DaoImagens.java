package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.SingletonConnetion;
import entidade.Imagens;

/**
 * Classe Respons�vel pelas opera��es no banco de dados
 * 
 * @author alex
 * 
 */
public class DaoImagens {

	private Connection connection;

	public DaoImagens() {

		connection = SingletonConnetion.getConnection();
	}

	public void salvarOuAtualizar(Imagens imagen) throws Exception {
		try {
			if (imagen.getId() == null || imagen.getId() <= 0) {// insere
				String sql = "INSERT INTO imagens(produto, fornecedor, miniatura, urlimagem, urlminiimg)VALUES ( ?, ?, ?, ?, ?);";
				PreparedStatement insert = connection.prepareStatement(sql);
				constroiStatement(imagen, insert);
				insert.execute();
			} else {// atualiza
				atualiza(imagen);
			}
			connection.commit();
		} catch (SQLException exception) {
			connection.rollback();
			exception.printStackTrace();
		}
	}

	private void atualiza(Imagens imagem) throws Exception {
		String sql = "UPDATE imagens SET produto=?, fornecedor=?, miniatura=?, urlimagem=?, urlminiimg=?  where id = "
				+ imagem.getId();
		PreparedStatement update = connection.prepareStatement(sql);

		constroiStatement(imagem, update);
		update.execute();
		connection.commit();
	}

	public void deleta(String codImg) throws Exception {
		if (!codImg.isEmpty() && codImg != null) {
			String sql = "DELETE FROM imagens where id = " + codImg;
			try {

				PreparedStatement delete = connection.prepareStatement(sql);
				delete.execute();
				connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new RuntimeException(e);
			}
		}
	}

	public Imagens consulta(Integer cod) {
		Imagens retorno = new Imagens();
		try {
			String sql = "select * FROM imagens where id = " + cod;
			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			while (resultSet.next()) {
				retorno.setId(resultSet.getInt("id"));
				retorno.setProduto(resultSet.getString("produto"));
				retorno.setFornecedor(resultSet.getString("fornecedor"));
				retorno.setMiniatura(resultSet.getString("miniatura"));
				retorno.setUrlimagem(resultSet.getString("urlimagem"));
				retorno.setUrlminiimg(resultSet.getString("urlminiimg"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retorno;
	}

	public List<Imagens> consultaTodos() {

		List<Imagens> retornoList = new ArrayList<Imagens>();
		String sql = "select id,produto,fornecedor, miniatura, urlminiimg FROM imagens order by id;";

		try {
			PreparedStatement find = connection.prepareStatement(sql);
			ResultSet resultSet = find.executeQuery();
			while (resultSet.next()) {
				Imagens retorno = new Imagens();
				retorno.setId(resultSet.getInt("id"));
				retorno.setProduto(resultSet.getString("produto"));
				retorno.setFornecedor(resultSet.getString("fornecedor"));
				retorno.setMiniatura(resultSet.getString("miniatura"));
 				retorno.setUrlminiimg(resultSet.getString("urlminiimg"));

				retornoList.add(retorno);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retornoList;
	}

	private void constroiStatement(Imagens imagen, PreparedStatement insert)
			throws Exception {
		insert.setString(1, imagen.getProduto());
		insert.setString(2, imagen.getFornecedor());
		insert.setString(3, imagen.getMiniatura());
		insert.setString(4, imagen.getUrlimagem());
		insert.setString(5, imagen.getUrlminiimg());
	}

}
