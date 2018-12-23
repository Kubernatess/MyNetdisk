

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class sessionServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //可能会被人非法闯入 访问/loginServlet故意在后面拼接参数,需要改进
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		HttpSession session=request.getSession();
		//对成功登陆的用户进行一些处理
		if(username!=null){	
			//设置session,保存用户名
			session.setAttribute("username", username);
			
			//获取/directory的磁盘路径
			String rootAbsolutePath=getServletContext().getRealPath("/directory");
			//拼接 ,此时得到根目录磁盘路径,以便后面拼接相对路径名使用
			rootAbsolutePath=rootAbsolutePath+"\\"+username;
			System.out.println("loginServlet--"+rootAbsolutePath);
			session.setAttribute("rootAbsolutePath",rootAbsolutePath);
			session.setAttribute("subDirectoryPath","");
			
			//重定向到index.jsp
			response.sendRedirect("index.jsp");
		}
		else{
			session.invalidate();
			//重定向到登陆页面
			response.sendRedirect("login.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
