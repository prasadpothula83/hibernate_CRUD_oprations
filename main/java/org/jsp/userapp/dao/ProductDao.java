package org.jsp.userapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userapp.dto.Product;
import org.jsp.userapp.dto.User;

public class ProductDao
{
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
  public Product saveProduct(Product product,int user_id)
  {
	  User u=manager.find(User.class, user_id);
	  if(u!=null) {
		  u.getProducts().add(product); // adding product to user
		  product.setUser(u); // assigning  user to product
		  EntityTransaction transaction=manager.getTransaction();
		  manager.persist(product);
		  transaction.begin();
		  transaction.commit();
		  return product;
	  }
	return null;
	  
  }
  public Product updateProduct(Product product,int user_id) {
	  User u=manager.find(User.class, user_id);
	  if(u!=null) {
		  u.getProducts().add(product);
		  product.setUser(u);
		  EntityTransaction transaction =manager.getTransaction();
		  manager.merge(product);
		  transaction.begin();
		  transaction.commit();
		  
		  return product;
	  }
	return null;
	  
  }
  public Product findProductById(int id) {
	  
	return manager.find(Product.class, id) ;
	  
  }
  public boolean deleteProduct(int id) {
	  Product p=findProductById(id);
	  if(p!=null) {
		  manager.remove(p);
		  EntityTransaction t=manager.getTransaction();
		  t.begin();
		  t.commit();
		  return true;
	  }
	return false; 
  }
  
   public List<Product>findProductsByUserId(int user_id){
	   Query q=manager.createQuery("select u.products from User u where u.id=?1");
	   q.setParameter(1,user_id);
	 return q.getResultList();
	   
   }
   public Product viewProductsByCategory(String category) {
	   String hql="select p from Product p where p.category=?1";
	   Query q=manager.createQuery(hql);
	   q.setParameter(1, category);
	   try {
		   return (Product) q.getSingleResult();
	   }
	   catch (NoResultException e){
		  
		 System.err.println("invalid category");
		 return null;
	   }
   }
   
   public Product viewProductByBrand(String brand) {
	   String hql="select p from Product p where p.brand=?1";
	   Query q=manager.createQuery(hql);
	   q.setParameter(1,brand);
	   try {
		   return (Product)q.getSingleResult();
	   }
	   catch(NoResultException e) {
		   System.out.println("invalid brand");
		   return null;
	   }
   }
}
