
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Data;

/**
 * @author 黄晓霖  E-mail: example@aliyun.com
 * @version 创建时间：2018年12月15日  上午11:46:19
 * tags
 */
@WebServlet("/confirmServlet")
public class confirmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//判断该用户密码是否正确
		if(Data.confirmPassword(username,password)){
			response.getWriter().println(true);
		}else
			response.getWriter().println(false);
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
