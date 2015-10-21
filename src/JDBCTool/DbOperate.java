package JDBCTool;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import JvavBean.Goods;
import JvavBean.User;

public class DbOperate {
	
	
	/**
	 * 根据userName 来获取User
	 * @param userName 用户名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getUserByUserName(String userName){
		
		Session session = HibernateUtils.currentSession();
		User user = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("from User where username=:username");
			query.setParameter("username", userName);
			List<User> users = query.list();
			
			if(!users.isEmpty()){
				user = users.get(0);
				tx.commit();
			}
		}catch(HibernateException ex){
			
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		
		session.close();
		return user;
	}
	
	
	/**
	 * 根据user_id获取User对象
	 * @param user_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getUserById(String user_id){
		
		Session session = HibernateUtils.currentSession();
		User user = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("from User where id=:id");
			query.setParameter("id", new Integer(user_id));
			List<User> users = query.list();
			if(!users.isEmpty()){
				user = users.get(0);
				tx.commit();
			}
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		session.close();
		return user;
	}
	
	
	/**
	 * 分页查询Goods对象
	 * @param first 查询的起始位置
	 * @param maxpage 查询一页最大条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByPage(int first,int maxpage){
		
		Session session = HibernateUtils.currentSession();
		List<Goods> list = null;
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			Query query = session.createQuery("from Goods");
			query.setFirstResult(first);
			query.setMaxResults(maxpage);
			list = query.list();
			tx.commit();
			
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		session.close();
		return list;
	}
	
	
	/**
	 * 根据外键user_id获取Goods对象集
	 * @param user_id 外键用户id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByUser_id(String user_id){
		
		Session session = HibernateUtils.currentSession();
		List<Goods> list = new ArrayList<Goods>();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			Query query = session.createQuery("from Goods where user_id=:user_id");
			query.setParameter("user_id", user_id);
			list = query.list();
			tx.commit();
			
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		session.close();
		return list;
	}
	
	/**
	 * 根据关键字查询goods
	 * @param key 关键字
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Goods> getGoodsByKey(String key){
		
		Session session = HibernateUtils.currentSession();
		List<Goods> list = null;
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			Query query = session.createQuery("from Goods where name like:name");
			query.setString("name", key);
			list = query.list();
			tx.commit();
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		
		session.close();
		return list;
	}
	/**
	 * 存储一个对象
	 * @param object
	 */
	public void save(Object object){
		
		Session session = HibernateUtils.currentSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			session.save(object);
			tx.commit();
			
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		
		session.close();
	}
	
	
	/**
	 * 更新数据库中的对象
	 * @param object
	 */
	public void update(Object object){
		
		Session session = HibernateUtils.currentSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			session.update(object);
			tx.commit();
			
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		
		session.close();
	}
	
	
	/**
	 * 删除数据库中的对象
	 * @param object
	 */
	public void delete(Object object){
		
		Session session = HibernateUtils.currentSession();
		Transaction tx = null;
		
		try{
			
			tx = session.beginTransaction();
			session.delete(object);
			tx.commit();
			
		}catch(HibernateException ex){
			if(tx != null){
				tx.rollback();
			}
			throw ex;
		}
		
		session.close();
	}
}
