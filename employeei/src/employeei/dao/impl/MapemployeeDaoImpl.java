package employeei.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import employeei.dao.MapemployeeDao;
import employeei.model.Mapemployee;

@Repository
public class MapemployeeDaoImpl implements MapemployeeDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void add(Mapemployee mapemployee) {
		sessionFactory.getCurrentSession().save(mapemployee);

	}

	@Override
	public void update(Mapemployee mapemployee) {
		sessionFactory.getCurrentSession().update(mapemployee);

	}

	@Override
	public void delete(int rid) {
		sessionFactory.getCurrentSession().delete(getMapemployee(rid));

	}

	@Override
	public Mapemployee getMapemployee(int rid) {
		return (Mapemployee)sessionFactory.getCurrentSession().get(Mapemployee.class,rid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllMapemployee() {
		return sessionFactory.getCurrentSession().createQuery("from Mapemployee").list();
	}

}
