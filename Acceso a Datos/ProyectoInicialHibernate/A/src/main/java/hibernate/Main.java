package hibernate;

import hibernate.Persona;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	
	public static void lectura(Session session,Transaction transaction,int clavePrimaria) {
		Persona personaR = session.get(Persona.class,clavePrimaria); 
		if(personaR !=null) {
			System.out.println(personaR.toString());
		}
		transaction.commit();
	}
	
	public static void modificacion(Session session,Transaction transaction,int clavePrimaria) {
		Persona personaMod = session.get(Persona.class, clavePrimaria);
		if(personaMod != null) {
			personaMod.setNombre("Matteo");
		}
		transaction.commit();
	}
	
	public static void Borrar(Session session,Transaction transaction,int clavePrimaria) {
		Persona personaBor = session.get(Persona.class, clavePrimaria);
		if(personaBor != null) {
			session.remove(personaBor);
		}
		transaction.commit();
	}
	
	public static void consultaHQL(Session session,Transaction transaction,int edad) {
		String consultaHql = "from personas p where p.edad>:edadMinima";
		//List <Persona> ListaPersona = session.createQuery();
	}
	
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Persona p = new Persona("Juan", 30);
        session.save(p);   // INSERT

        transaction.commit();
        session.close();
        HibernateUtil.shutdown();

	}

}
