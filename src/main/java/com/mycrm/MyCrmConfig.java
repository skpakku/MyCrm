package com.mycrm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mycrm")
public class MyCrmConfig {

	private String dbHost;
	private String dbName;
	private int dbPort;
	private String dbUsername;
	private String dbPassword;

	public String getDbPassword() {
		return dbPassword;
	}

	public MyCrmConfig setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
		return this;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public MyCrmConfig setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
		return this;
	}

	public int getDbPort() {
		return dbPort;
	}

	public MyCrmConfig setDbPort(int dbPort) {
		this.dbPort = dbPort;
		return this;
	}

	public String getDbName() {
		return dbName;
	}

	public MyCrmConfig setDbName(String dbName) {
		this.dbName = dbName;
		return this;
	}

	public String getDbHost() {
		return dbHost;
	}

	public MyCrmConfig setDbHost(String dbHost) {
		this.dbHost = dbHost;
		return this;
	}

}
