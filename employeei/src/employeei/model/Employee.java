package employeei.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	@Column(insertable = false, updatable = false)
	public String rowid;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
     generator = "seq_emp")
    @SequenceGenerator(name = "seq_emp", 
     sequenceName = "seq_emp",allocationSize=1)
	public int empid;
	@Column
	public String empname;
	@Column
	public String empphone;
	@Column
	public String address;
	@Column
	public Date doj;
	
	public Employee() {
	}
	
	public Employee(int empid,String empname,String empphone,String address,Date doj)
	{
		this.empid = empid;
		this.empname = empname;
		this.empphone = empphone;
		this.address = address;
		this.doj = doj;
	}
	
	public String getRowid() {
		return rowid;
	}
	
	public int getEmpid() {
		return empid;
	}

	public String getEmpname() {
		return empname;
	}

	public String getEmpphone() {
		return empphone;
	}

	public String getAddress() {
		return address;
	}

	public Date getDoj() {
		return doj;
	}
	
	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
}

