package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data {

	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	public static boolean isExistUsername(String username){
		try {
			con=DBUtils.getCon();
			String sql = "select * from user where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
