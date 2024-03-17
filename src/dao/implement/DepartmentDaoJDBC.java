package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.DepartmentDao;
import db.DB;
import db.DbException;
import entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department dpt) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO department "
					+ "(Name)" + "VALUES (?)", +
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, dpt.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) { //confirmar se foi inserido
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1); //pegando o Id da primeira coluna das chaves
					dpt.setId(id); //passa o Id criado para o objeto
					System.out.println("Linhas afetadas: " + rowsAffected);
				}
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally { 
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Department dpt) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE department "
					+ "SET Name = (?) "
					+ "WHERE Id = (?) ");
			
			st.setString(1, dpt.getName());
			st.setInt(2, dpt.getId());
			
			int rowsAffected = st.executeUpdate();
			System.out.println("update realizado! linhas afetadas: " + rowsAffected);
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE Id = (?) ");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			System.out.println("Departamento deletado! linhas afetadas: " + rowsAffected); 
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
