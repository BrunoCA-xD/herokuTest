package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DAOLogin {

	private Connection conn;

	public DAOLogin() {
		conn = SingleConnection.getConnection();
	}

	public boolean validateLogin(String login, String password) throws Exception {

		String sql = "select id from usuario where login='" + login + "' and senha='" + password + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		return resultSet.next();		
	}
}
