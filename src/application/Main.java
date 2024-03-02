package application;

import java.util.List;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Department;
import entities.Seller;

public class Main {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("-- Teste 1: FindById --");
		Seller seller = sellerDao.findById(8);
		System.out.println(seller.getName() +", "+ seller.getDepartment());
		
		System.out.println("-- Teste 2: FindByDepartment --");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department); 
		
		for (Seller seller2 : list) {
			System.out.println(seller2.getName());
		}
	}

}
