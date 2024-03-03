package application;
import java.util.Date;
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
		System.out.println(seller.getName() +", "+ seller.getDepartment().getName());
		
		System.out.println("\n-- Teste 2: FindByDepartment --");
		Department department = new Department(4, null); //qual departamento vai ser retornado
		List<Seller> list = sellerDao.findByDepartment(department); 
		for (Seller seller2 : list) {
			System.out.println(seller2.getName());
		}
		
		System.out.println("\n-- Teste 3: FindAll --");
		List<Seller> list3 = sellerDao.findAll(); 
		for (Seller seller2 : list3) {
			System.out.println(seller2);
		}
		
		System.out.println("\n-- Teste 4: Insert --");
		Department dpt = new Department(4, null);
		Seller seller3 = new Seller(null, "joao silva", "joao@gmail", new Date(), 2000.00, dpt);
		sellerDao.insert(seller3);
		System.out.println("Inserção feita! \nId: "+ seller3.getId());
		
		System.out.println("\n-- Teste 5: Update --");
		Seller sellerUp = sellerDao.findById(8); //busca o seller
		sellerUp.setBaseSalary(200.00); //passa o valor
		sellerDao.update(sellerUp); //atualiza 
		System.out.println("Atualizaçao feita!");
	
		
		System.out.println("\n-- Teste 6: DeleteById --");
		sellerDao.deleteById(10);

		
		
	}

}
