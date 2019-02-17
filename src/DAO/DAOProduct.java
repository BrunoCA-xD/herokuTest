package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ProductBean;
import beans.UserBean;
import connection.SingleConnection;

public class DAOProduct {

	private Connection conn;

	public DAOProduct() {
		conn = SingleConnection.getConnection();
	}

	public void save(ProductBean product) {
		try {
			String sql = "insert into produto values(null,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getNome());
			statement.setDouble(2, product.getQuantidade());
			statement.setDouble(3, product.getValor());
			statement.execute();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void delete(String id) {

		try {
			String sql = "delete from produto where id ='" + id + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void update(ProductBean product) {
		try {
			String sql = "update produto set nome=?, quantidade=?, valor=? where id =" + product.getId();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, product.getNome());
			statement.setDouble(2, product.getQuantidade());
			statement.setDouble(3, product.getValor());
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<ProductBean> listAll() throws SQLException {
		List<ProductBean> lst = new ArrayList<ProductBean>();
		String sql = "select * from produto";

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			ProductBean product = new ProductBean(res.getLong("id"),
					res.getString("nome"), res.getDouble("quantidade"),
					res.getDouble("valor"));
			lst.add(product);
		}
		return lst;
	}

	public ProductBean findById(String id) throws SQLException {
		String sql = "select * from produto where id='" + id + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			ProductBean product = new ProductBean(res.getLong("id"),
					res.getString("nome"), res.getDouble("quantidade"),
					res.getDouble("valor"));
			return product;
		}
		return null;
	}

	public boolean isNameValid(String name, String id) throws SQLException {
		String sql = "select count(1) as qtd, id from produto where nome='" + name + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			if (res.getInt("qtd") == 0)
				return true;
			if (res.getString("id").equals(id))
				return true;
		}

		return false;
	}
}
