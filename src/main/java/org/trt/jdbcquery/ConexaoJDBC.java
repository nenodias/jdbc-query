package org.trt.jdbcquery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
 
public class ConexaoJDBC {
	
	private static Map<String, String> map;
	private static Connection connection = null;
	
	private static void makeMap(String[] args) {
	    map = new HashMap<>();
	    for (String arg : args) {
	        if (arg.contains("=")) {
	            map.put(arg.substring(0, arg.indexOf('=')), arg.substring(arg.indexOf('=') + 1) );
	        }
	    }
	}
	
	public static void main(String[] argv) {
		makeMap(argv);
		if (argv.length == 0 || map.get("help") != null){
			System.out.println("Passar os parametros url=<URL> usuario=<usuario> senha=<senha> driver=<driver> sql=\"<sql>\" (use aspas) \n execute=<Apenas se o sql não tiver resultset> ou help=? ");
		}else{
			executar(map);
		}
	}

	private static void executar(Map<String, String> map2) {
		String url = map.get("url");
		String driver = map.get("driver");
		String usuario = map.get("usuario");
		String senha = map.get("senha");
//		String dialeto = map.get("dialeto");
		String sql = map.get("sql");
		sql= sql.replaceAll("\"", "");
		String execute = map.get("execute");
		if(StringUtils.isNotBlank(url) && StringUtils.isNotBlank(sql) && StringUtils.isNotBlank(driver)){
			try{
				Class.forName(driver);
				if(StringUtils.isNotBlank(usuario)&&StringUtils.isNotBlank(senha)){
					connection = DriverManager.getConnection(url, usuario, senha);
				}else{
					connection = DriverManager.getConnection(url);
				}
				if(connection != null){
					try{
						connection.setAutoCommit(false);
					}catch(Exception e){
						
					}
					try(Statement statement = connection.createStatement()){
						if(StringUtils.isNotBlank(execute) ){
							statement.execute(sql);
						}else{
							ResultSet resultSet = statement.executeQuery(sql);
							ResultSetMetaData metaData = resultSet.getMetaData();
							System.out.print("|");
							for (int i = 1; i <= metaData.getColumnCount(); i++) {
								if(i!=1){
									System.out.print("|");
								}
								System.out.print(metaData.getColumnLabel(i)+"("+metaData.getColumnTypeName(i)+")" );
							}
							System.out.print("|");
							System.out.println();
							while ( resultSet.next() ) {
								System.out.print("|");
								for (int i = 1; i <= metaData.getColumnCount(); i++) {
									if(i!=1){
										System.out.print("|");
									}
									System.out.print( resultSet.getObject(i) );
								}
								System.out.print("|");
							}
						}
						connection.commit();
					}catch (Exception e) {
						throw e;
					}
					connection.close();
				}
			}catch(Exception e){
				try {
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
 
}