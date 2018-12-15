
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 黄晓霖  E-mail: example@aliyun.com
 * @version 创建时间：2018年12月15日  下午1:15:36
 * tags
 */
@WebServlet("/VerificationServlet")
public class VerificationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String verification=(String) session.getAttribute("verification");
		String verification_input=request.getParameter("verification");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		if(verification.equalsIgnoreCase(verification_input)){
			response.getWriter().write("true");
		}
		else
			response.getWriter().write("false");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
