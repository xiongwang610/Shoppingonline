package JDBCTool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtils {
	
	private static final SessionFactory sessionFactory;
	
	static{
		
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
			
		}catch(Throwable ex){
			ex.printStackTrace();
			throw new ExceptionInInitializerError();
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal session = new ThreadLocal();
	
	/**
	 * 获取当前线程中的session对象
	 * @return
	 */
	public static Session currentSession(){
		
		Session s = (Session) session.get();
		
		if(s == null || !s.isOpen()){
			s = sessionFactory.openSession();
		}
		
		return s;
	}
	
	@SuppressWarnings("unchecked")
	public static void closeSession(){
		
		Session s = (Session) session.get();
		session.set(null);
		if(s != null){
			s.close();
		}
	} 
}
