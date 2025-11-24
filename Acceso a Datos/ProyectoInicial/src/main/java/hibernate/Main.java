package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		Persona persona = new Persona("test",21);
		
		session.save(persona);
	
		transaction.commit();
		session.close();
		
		HibernateUtil.shutdown();
		
	}
}
