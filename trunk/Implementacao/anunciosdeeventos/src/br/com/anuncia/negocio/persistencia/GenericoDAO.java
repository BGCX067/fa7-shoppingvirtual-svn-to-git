package br.com.anuncia.negocio.persistencia;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class GenericoDAO<T> {
	private static EntityManagerFactory factory;

	private EntityManager entityManager;

	private Class<T> persistentClass;

	public GenericoDAO() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("anunciaPU");
		}
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null)
			entityManager = factory.createEntityManager();
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery(
				"from " + persistentClass.getName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public String add_and(String clausula_where) {

		if ((clausula_where != null) && (!clausula_where.equals(""))) {
			clausula_where = clausula_where + " and ";
		} else {
			if (clausula_where == null)
				clausula_where = "";
		}

		return clausula_where;
	}

	@SuppressWarnings("unchecked")
	public String add_or(String clausula_where) {

		if ((clausula_where != null) && (!clausula_where.equals(""))) {
			clausula_where = clausula_where + " or ";
		} else {
			if (clausula_where == null)
				clausula_where = "";
		}

		return clausula_where;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String clausula_where) {
		
		String statment = "from " + persistentClass.getName();
		if (clausula_where != null)
			statment += " where " + clausula_where;
		
		return getEntityManager().createQuery(statment).getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(persistentClass, id);
	}

	public void remove(T instance) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(instance);
		entityManager.getTransaction().commit();
	}

	public void save(T instance) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(instance);
		entityManager.getTransaction().commit();
	}

	public void update(T instance) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(instance);
		entityManager.getTransaction().commit();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	public boolean existe(String clausula_where) {
		
		String statment = "select count(*) from " + persistentClass.getSimpleName() + " o ";
		if (clausula_where != null)
			statment += " where " + clausula_where;
		
		System.out.println(statment);
		
		Integer qtd = (Integer)getEntityManager().createNativeQuery(statment).getSingleResult();
		
		if (qtd == 0) 
			return false;
		else 
			return true;
	}
}