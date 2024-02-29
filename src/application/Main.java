package application;
import java.util.Date;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Department;
import entities.Seller;

public class Main {

	public static void main(String[] args) {
			
		//Department dpt = new Department(1, "Eletronicos");
		
		//Seller seller = new Seller(1, "vinicius", "vini@gmail", new Date(), 1000.00, dpt);
		
		SellerDao sellerDao = DaoFactory.createSellerDao(); 
		
		Seller seller = sellerDao.findById(4); 
		
		//System.out.println(dpt);
		
		System.out.println(seller);
		
		

	}

}
