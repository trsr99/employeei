package employeei.dao;

import java.util.List;

import employeei.model.Mapemployee;

public interface MapemployeeDao {
	public void add(Mapemployee mapemployee);
	public void update(Mapemployee mapemployee);
	public void delete(int rid);
	public Mapemployee getMapemployee(int rid);
	@SuppressWarnings("rawtypes")
	public List getAllMapemployee(); 
}
