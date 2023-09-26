package org.jsp.userapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userapp.dao.ProductDao;
import org.jsp.userapp.dao.UserDao;
import org.jsp.userapp.dto.Product;
import org.jsp.userapp.dto.User;

public class UserController 
{
	static Scanner s=new Scanner(System.in);
	static ProductDao pDao=new ProductDao();
	static UserDao uDao=new UserDao();
	public static void main(String[] args)
	{
		System.out.println("1.Register");
		System.out.println("2.Update");
		System.out.println("3.Verify User by phone and password");
		System.out.println("4.Verify User by email and password");
		System.out.println("5.AddProduct");
		System.out.println("6.View Products by User id");
		System.out.println("7.View Products by category");
		System.out.println("8.view Products by brand");
		System.out.println("enter your choice");
	int choice =s.nextInt();
	switch(choice) {
	case 1:
	{
		save();
		break;
	}
	case 2:
	{
		update();
		break;
	}
	case 3 :
	{
		verifyphonePhoneandPasswoed();
		break;
	}
	case 4:{
		verifyEmailAndPassword();
		break;
	}
	case 5:
	{
		addProduct();
		break;
	}
	case 6:
	{
		viewProductsByUserId();
		break;
	}
	case 7:
	{
		verifyCatagory();
		break;
	}
	case 8:
	{
		verifyBrand();
		break;
	}
	}
	}
	


public static void save() {
	System.out.println("enter your name ,email ,phone and password ");
	String name=s.next();
	String email=s.next();
	long phone =s.nextLong();
	String pasword=s.next();
	User u=new User();
	u.setName(name);
	u.setEmail(email);
	u.setPhone(phone);
	u.setPassword(pasword);
	u=uDao.saveUser(u);
	System.out.println("you are registed with id :" +u.getId());
}

public static void update() {
	System.out.println("enter your id");
	int id=s.nextInt();
	System.out.println("enter your name ,email ,phone and password ");
	String name=s.next();
	String email=s.next();
	long phone =s.nextLong();
	String pasword=s.next();
	User u=new User();
	u.setId(id);
	u.setName(name);
	u.setEmail(email);
	u.setPhone(phone);
	u.setPassword(pasword);
	u=uDao.updateUser(u);
	System.out.println("you are updated with id :" +u.getId());
}
public  static void verifyphonePhoneandPasswoed() {
	// TODO Auto-generated method stub
		System.out.println("enter user phone and password");
		long phone=s.nextLong();
		String password=s.next();
		User u=uDao.verifyUser(phone, password);
		if(u!=null) {
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getEmail());
		}
		}
public static void verifyEmailAndPassword() {
	System.out.println("enter  user email and password");
	String email=s.next();
	String password=s.next();
	User u=uDao.verifyUser(email, password);
	if(u!=null) {
		System.out.println(u.getId());
		System.out.println(u.getName());
		System.out.println(u.getEmail());
		System.out.println(u.getPhone());
		System.out.println(u.getPassword());
	}
}

public static void addProduct() {
	System.out.println("enter your id to add the product");
	int uid=s.nextInt();
	System.out.println("enter the name brand category price");
	String name=s.next();
	String brand=s.next();
	String category=s.next();
	double price=s.nextDouble();
	Product p=new Product();
	p.setBrand(brand);
	p.setCategory(category);
	p.setName(name);
    p.setPrice(price);
   p=pDao.saveProduct(p, uid);
    if(p!=null)
    {
    	System.out.println("product add to the cart with id"+p.getId());
    	
    }else {
    	System.out.println("user with the enterdid not present");
    }
}
public static void verifyCatagory() {
	System.out.println("enter category");
	String category=s.next();
	Product p=pDao.viewProductsByCategory(category);
	if(p!=null) {
		System.out.println(p.getId());
		System.out.println(p.getBrand());
		System.out.println(p.getCategory());
         System.out.println(p.getName());
		System.out.println(p.getPrice());
	}
	
}

public static void verifyBrand() {
	System.out.println("enter brand");
	String brand=s.next();
	Product p=pDao.viewProductByBrand(brand);
	if(p!=null) {
		System.out.println(p.getId());
		System.out.println(p.getName());
		System.out.println(p.getBrand());
		System.out.println(p.getCategory());
		System.out.println(p.getPrice());
	}
}

public static void viewProductsByUserId() {
	System.out.println("enter your id to display products");
  List<Product>	products =pDao.findProductsByUserId(s.nextInt());
	for(Product p: products)
	{
		System.out.println(p);
	}
}
}
