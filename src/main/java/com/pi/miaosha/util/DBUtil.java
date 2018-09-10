package com.pi.miaosha.util;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	public  Connection getConn() throws Exception{
		Class.forName(driverClassName);
		return DriverManager.getConnection(url,username, password);
	}
}
