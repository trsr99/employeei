package employeei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employeei.dao.MapemployeeDao;
import employeei.model.Mapemployee;
import employeei.service.MapemployeeService;

@Service
public class MapemployeeServiceImpl implements MapemployeeService  {
	@Autowired
	private MapemployeeDao mapemployeedao;

	@Transactional
	public void add(Mapemployee mapemployee) {
		mapemployeedao.add(mapemployee);
		
	}

	@Transactional
	public void update(Mapemployee mapemployee) {
		mapemployeedao.update(mapemployee);

	}

	@Transactional
	public void delete(int rid) {
		mapemployeedao.delete(rid);

	}

	@Transactional
	public Mapemployee getMapemployee(int rid) {
		return mapemployeedao.getMapemployee(rid);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List getAllMapemployee() {
		return mapemployeedao.getAllMapemployee();
	}

}
