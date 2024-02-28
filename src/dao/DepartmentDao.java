package dao;

import java.util.List;

import entities.Department;

public interface DepartmentDao {
	
	void insert(Department dpt);
	void update(Department dpt);
	void deleteById(Integer id);
	Department findById(Integer id); // irá consultar no DB de existe um obj com esse id, 
									 //se exixtir irá retornar o departamento
	List<Department> findAll();	//retornar tudo 
}
