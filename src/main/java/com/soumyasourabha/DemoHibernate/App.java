package com.soumyasourabha.DemoHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
	public static void main(String[] args)  {

		employee emp = new employee();
		
//		emp.setDomain("docker"); 
//		emp.setId(78452);
//		emp.setName("navya");
//		emp.setSalary(45000);

		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(employee.class);

		SessionFactory sf = con.buildSessionFactory();
		
		//1st session start

		Session session = sf.openSession();
		
		session.beginTransaction();
		
		
		Query q1 = session.createQuery("from employee where id=78452");
		q1.setCacheable(true);
		emp = (employee) q1.uniqueResult();

		
//		emp = session.get(employee.class, 78452);
		
		System.out.println(emp);

		session.getTransaction().commit();
		
		session.close();
		
		//2nd session start
		
		Session session2 = sf.openSession();
		
		session2.beginTransaction();
		

		Query q2 = session2.createQuery("from employee where id=78452");
		q2.setCacheable(true);
		employee emp2 = (employee) q2.uniqueResult();
		
		
//		employee emp2 = session2.get(employee.class, 78452); //fetching same data
		
		System.out.println(emp2);
		
		session2.getTransaction().commit();
		
		session2.close();
		
	}
}
