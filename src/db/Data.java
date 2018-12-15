package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data {

	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	//判断用户名是否存在
	public static boolean isExistUsername(String username){
		try {
			con=DBUtils.getCon();
			String sql = "select * from user where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				rs.close();
				ps.close();
				con.close();
				return true;
			}
			rs.close();
			ps.close();
			con.close();	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//判断该用户密码是否正确
	public static boolean confirmPassword(String username,String password){
		try {
			con=DBUtils.getCon();
			String sql = "select password from user where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				String pass=rs.getString("password");
				if(pass.equals(password)){
					rs.close();
					ps.close();
					con.close();
					return true;
				}	
			}
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//判断验证码输入是否正确

}
