package employeei.dao;

import java.sql.*;

import employeei.model.LoginBean;
import employeei.service.DbConnection;
public class LoginDao {

	public static boolean validate(LoginBean bean){
		boolean status=false;
		try{
			// DB Connection
			Connection con = DbConnection.getConnection();
			
			PreparedStatement ps=con.prepareStatement("select * from users where email=? and pass=? and active = 1");
			ps.setString(1,bean.getEmail());
			ps.setString(2, bean.getPass());
			
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){}
		return status;
	}
}