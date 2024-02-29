package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.SellerDao;
import db.DB;
import db.DbException;
import entities.Department;
import entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public  SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}//injeçao de dependencia

	@Override
	public void insert(Seller seller) {
	}

	@Override
	public void update(Seller seller) {	
	}

	@Override
	public void deleteById(Integer id) {	
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
			
		try {
		st = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName\r\n"
				+ "FROM seller INNER JOIN department\r\n"
				+ "ON seller.DepartmentId = department.Id\r\n"
				+ "WHERE seller.Id = ?");
		
		  st.setInt(1, id);
		 
		 rs = st.executeQuery();
		 
		 //percorrer pela tabela do BD para instanciar os objetos
		 if(rs.next()) {
			 
			 Department dpt = new Department();
			 dpt.setId(rs.getInt("DepartmentId"));
			 dpt.setName(rs.getString("DepName")); 
			 
			 Seller seller = new Seller();
			 seller.setId(rs.getInt("Id"));
			 seller.setName(rs.getString("Name"));
			 seller.setEmail(rs.getString("Email"));
			 seller.setBirthDate(rs.getDate("BirthDate"));
			 seller.setBaseSalary(rs.getDouble("BaseSalary"));
			 seller.setDepartment(dpt);
			 return seller;
		 }
		 return null;
		
		} catch(SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}

}
