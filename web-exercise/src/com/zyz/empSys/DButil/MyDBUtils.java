package com.zyz.empSys.DButil;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
	 * c3p0���ݿ����ӳ�
	 */
public class MyDBUtils {
	
	private static ComboPooledDataSource source = null;
	private static Connection connection = null;
	
	
	/**
	 * ˽�й���
	 */
	private MyDBUtils() {
		
	}
	
	/**
	 * ��ȡ����Դ
	 * @return
	 */
	public static DataSource getDataSource() {
		source = new ComboPooledDataSource();
		return source;
	}
	
	/**
	 * ��ȡ���Ӷ���
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		connection = getDataSource().getConnection();
		if(connection != null) {
			return connection;
		}else {
			connection = getDataSource().getConnection();
		}
		return connection;
	}
	
	/**
	 * �ر����Ӷ���
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		if(connection != null){
			connection.close();
		}
	}
}
