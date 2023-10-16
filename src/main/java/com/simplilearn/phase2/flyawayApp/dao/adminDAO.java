package com.simplilearn.phase2.flyawayApp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.simplilearn.phase2.flyawayApp.persistent.Admin;

public class adminDAO {
	
	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory sf = meta.getSessionFactoryBuilder().build();

		Session session = sf.openSession();

		Transaction t = session.beginTransaction();

		Admin admin1 = new Admin();

		admin1.setUsername("Shawn");
		admin1.setPassword("Shawn@FlyAway");
		
		Admin admin2 = new Admin();

		admin2.setUsername("Mendis");
		admin2.setPassword("Mendis@FlyAway");

		session.save(admin1);
		session.save(admin2);

		t.commit();

		System.out.println("Successfully saved...");
		
		sf.close();
		session.close();

	}
	
}
