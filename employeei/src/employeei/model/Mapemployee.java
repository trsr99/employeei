package employeei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MAPEMPLOYEE")
public class Mapemployee {
	@Column(insertable = false, updatable = false)
	public String rowid;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
     generator = "seq_empdept")
    @SequenceGenerator(name = "seq_empdept", 
     sequenceName = "seq_empdept",allocationSize=1)
	public int empdeptid;
	@Column
	public int empid;
	@Column
	public int deptid;
	
	public Mapemployee() {
	}
	
	public Mapemployee(int empdeptid, int empid, int deptid) {
		this.empdeptid = empdeptid;
		this.empid = empid;
		this.deptid = deptid;
	}
	
	public int getEmpdeptid() {
		return empdeptid;
	}
	
	public void setEmpdeptid(int empdeptid) {
		this.empdeptid = empdeptid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

}
