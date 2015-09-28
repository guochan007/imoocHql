package com.imooc.model;


import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.imooc.util.HibernateSessionFactory;

public class CustomerTest {
	private Session session = null;
	
	@Test
	public void testUnique(){
		String hql = " from Customer c where c.name='tom' ";
		Query query = session.createQuery(hql);
		Customer customer = (Customer)query.uniqueResult();
		
		System.out.println(customer.getName());
	}
	
	@Test
	public void testOrderby(){
		String hql = " from Customer order by age desc ";
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		
		for(Customer c: customers){
			System.out.println("name:"+c.getName());
			System.out.println("age:"+c.getAge());
		}
	}
	
	@Test
	public void testWhere4(){
		String hql = " from Customer c where c.age>20 ";
		Query query = session.createQuery(hql);
		
		Customer c = (Customer)query.uniqueResult();
		
		System.out.println(c.getName());
	}
	
	@Test
	public void testWhere3(){
		String hql = " from Customer c where c.address like '%±±¾©%'";
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		
		for(Customer c : customers){
			System.out.println("name:"+c.getName());
			System.out.println("address :"+ c.getAddress());
		}
	}
	
	@Test
	public void testWhere2(){
		String hql = " from Customer c where c.age not between 20 and 40 ";
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		
		for(Customer c: customers){
			System.out.println("name:"+c.getName());
			System.out.println("age:"+c.getAge());
		}
	}
	
	@Test
	public void testWhere1(){
		String hql = "  from Customer c where c.sex<>'ÄÐ'";
		Query query = session .createQuery(hql);
		List<Customer> customers = query.list();
		
		for(Customer c : customers){
			System.out.println("name:"+c.getName());
			System.out.println("sex:"+c.getSex());
		}
	}
	
//	distinct
	@Test
	public void testDistinct(){
		String hql = "select distinct c.sex from Customer c ";
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		
		for(Object obj : list){
			System.out.println(obj);
		}
	}
	
		
	@Test
	public void testFromClause(){
		String hql = " from Customer ";
		
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		
		for(Customer customer : customers){
			System.out.println("name:"+customer.getName());
		}
	}

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

}
