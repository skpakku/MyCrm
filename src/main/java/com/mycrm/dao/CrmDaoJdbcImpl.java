package com.mycrm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycrm.DbConnectionException;
import com.mycrm.DbStatementException;
import com.mycrm.MyCrmConfig;
import com.mycrm.model.User;

@Service
public class CrmDaoJdbcImpl implements CrmDao {

	private final MyCrmConfig config;

	private Connection connection;

	public CrmDaoJdbcImpl(MyCrmConfig config) {
		this.config = config;
		this.connection = initConnection();
	}

	private Connection initConnection() {

		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			throw new DbConnectionException("Exception while loading postgres Driver", e);
		}

		connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://" + config.getDbHost() + ":" + config.getDbPort() + "/" + config.getDbName(),
					config.getDbUsername(), config.getDbPassword());

			return connection;

		} catch (SQLException e) {

			throw new DbConnectionException("Exception while Connecting to DB", e);
		}

	}

	public Optional<User> getUser(String userName) {

		try {

			PreparedStatement pStmt = connection.prepareStatement("select * from tuser where username = ?");
			pStmt.setString(1, userName);
			ResultSet userTable = pStmt.executeQuery();

			boolean recordFound = userTable.next();
			if (!recordFound) {
				return Optional.empty();
			}

			User user = new User();
			user.setUsername(userTable.getString("username"));
			user.setPassword(userTable.getString("password"));

			userTable.close();
			pStmt.close();

			return Optional.of(user);

		} catch (SQLException e) {

			throw new DbStatementException("DB Statments exception ", e);
		}

	}

}
