package employeei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
public class Department {
		@Column(insertable = false, updatable = false)
		public String rowid;
		@Id
		@Column
		@GeneratedValue(strategy = GenerationType.SEQUENCE, 
	     generator = "seq_dept")
	    @SequenceGenerator(name = "seq_dept", 
	     sequenceName = "seq_dept",allocationSize=1)
		public int deptid;
		@Column
		public String deptname;
		@Column
		public String description;
	
		public Department() {
		}
		
		public Department(int deptid,String deptname,String description)
		{
			this.deptid = deptid;
			this.deptname = deptname;
			this.description = description;
		}

		public String getRowid() {
			return rowid;
		}

		public int getDeptid() {
			return deptid;
		}

		public void setDeptid(int deptid) {
			this.deptid = deptid;
		}

		public String getDeptname() {
			return deptname;
		}

		public void setDeptname(String deptname) {
			this.deptname = deptname;
		}
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
}
