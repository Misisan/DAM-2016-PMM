package inicio;

import java.sql.Date;
import org.hibernate.Session;

public class aad_5a_masimeon {

	public static void main(String[] args) {
		System.out.println("Hola");
		
		//Iniciamos la sesión de Hibernate
		Session session = HibernateUtilities.getSessionFactory().openSession();
		
		//Iniciamos la transacción para poder trabajar
		session.beginTransaction();
		
		Empresa e1 = new Empresa("Empresa de prueba 1", "12345678A", 78, "C. Desconocida sn");
		Pedido p1 = new Pedido(Date.valueOf("2017-05-22"));
		Item i1 = new Item("item 1", 50);
				
		//guardamos el objeto creado en la session abierta
		session.save(e1);
		session.save(p1);
		session.save(i1);
		
		//Ejecutamos todo con el commit
		session.getTransaction().commit();
		
		//Cerramos la sesión de Hibernate
		session.close();
		//Cerramos la sesión del Factory
		HibernateUtilities.getSessionFactory().close();
		
		//Creamos una nueva transacción para recuperar los datos almacenados
		Session s2 = HibernateUtilities.getSessionFactory().openSession();	
		s2.beginTransaction();
		
		//Recuperamos los datos
		Empresa e2 = s2.get(Empresa.class, "12345678A");
		Pedido p2 = s2.get(Pedido.class, 1);
		Item i2 = s2.get(Item.class, 1);
		
		System.out.println("Se ha recuperado la empresa "+e2.getNombre()+" el pedido de "+p2.getFecha()+" con "+i2.getCantidad()+" del ítem: "+i2.getNombre());
			
		s2.getTransaction().commit();
		s2.close();
		HibernateUtilities.getSessionFactory().close();
	
	}

}
