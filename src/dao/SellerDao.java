package dao;

import java.util.List;

import entities.Seller;

public interface SellerDao {
	void insert(Seller seller);
	void update(Seller seller);
	void deleteById(Integer id);
	Seller findById(Integer id); // irá consultar no DB de existe um obj com esse id, 
									 //se exixtir irá retornar o departamento
	List<Seller> findAll();	//retornar tudo 
}
