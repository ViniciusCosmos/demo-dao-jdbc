package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			 Department dpt = instanciateDepartment(rs);			 
			 Seller seller = instanciateSeller(rs, dpt);
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
	
	private Seller instanciateSeller(ResultSet rs,  Department dpt) throws SQLException {
		 Seller seller = new Seller();
		 
		 seller.setId(rs.getInt("Id"));
		 seller.setName(rs.getString("Name"));
		 seller.setEmail(rs.getString("Email"));
		 seller.setBirthDate(rs.getDate("BirthDate"));
		 seller.setBaseSalary(rs.getDouble("BaseSalary"));
		 seller.setDepartment(dpt);
		 
		 return seller;
	}
	
	private Department instanciateDepartment(ResultSet rs) throws SQLException {
		Department dpt = new Department();
		
		dpt.setId(rs.getInt("DepartmentId"));
		dpt.setName(rs.getString("DepName"));
		
		return dpt;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
			
		try {
		st = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName\r\n"
				+ "FROM seller INNER JOIN department\r\n"
				+ "ON seller.DepartmentId = department.Id\r\n"
				+ "WHERE DepartmentId = ?\r\n"
				+ "ORDER BY Name");
		
		  st.setInt(1, department.getId());
		 
		 rs = st.executeQuery();
		 
		 List<Seller> list = new ArrayList<Seller>();
		 Map<Integer, Department> map = new HashMap<>();
		 
		 while (rs.next()) {	
			 
			 Department dpt = map.get(rs.getInt("DepartmentId")); //verificar se já existe um dpt instanciado  
			 
			 if(dpt == null) {
				dpt = instanciateDepartment(rs);
				map.put(rs.getInt("DepartmentId"), dpt); //guardando o valor que já foi instanciado
			 }
			 			 
			 Seller seller = instanciateSeller(rs, dpt);
			 list.add(seller);
			 
		 } 
		 return list;
			 
		} catch(SQLException e){
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}	
	}

}
