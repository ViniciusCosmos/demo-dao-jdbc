package db;
// metodos estaticos para conectar e desconectar com o db

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	// metodo pra carregar os arquivos de conexao que estao no bloco de notas

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) { // se tiver instanciado
			try {
				Properties props = loadProperties(); // objeto pra fazer a leitura da url
				String url = props.getProperty("dburl"); // fazendo a leitura da url
				conn = DriverManager.getConnection(url, props); // faz a conexao
			} catch (SQLException e) { // tratando exceçao do tipo SQLException
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) { // se tiver instanciado
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());// passando a msg do IOEXception
		}
	}

	// metodos para fechar Statment e Result, pois sao funçoes externas e podem
	// gerar vazamento de memoria

	public static void closeStatement(Statement st) {
		if (st != null) { // se tiver instanciado
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());// passando a msg do SQLEXception
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage()); // passando a msg do SQLEXception
			}
		}
	}
}
