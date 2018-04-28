package employeei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class Users {
	@Column(insertable = false, updatable = false)
	public String rowid;
	@Column
	public String name;
	@Id
	@Column
	public String email;
	@Column
	public String pass;
	@Column
	public int active;
	
	public Users() {
	}
	
	public Users(String name, String email, String pass, int active) {
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
}
