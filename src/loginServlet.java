

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
    //���ܻᱻ�˷Ƿ����� ����/loginServlet�����ں���ƴ�Ӳ���,��Ҫ�Ľ�
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		HttpSession session=request.getSession();
		//�Գɹ���½���û�����һЩ����
		if(username!=null){	
			//����session,�����û���
			session.setAttribute("username", username);
			
			//��ȡ/directory�Ĵ���·��
			String rootAbsolutePath=getServletContext().getRealPath("/directory");
			//ƴ�� ,��ʱ�õ���Ŀ¼����·��,�Ա����ƴ�����·����ʹ��
			rootAbsolutePath=rootAbsolutePath+"\\"+username;
			System.out.println("loginServlet--"+rootAbsolutePath);
			session.setAttribute("rootAbsolutePath",rootAbsolutePath);
			session.setAttribute("subDirectoryPath","");
			
			//�ض���index.jsp
			response.sendRedirect("index.jsp");
		}
		else{
			session.invalidate();
			//�ض��򵽵�½ҳ��
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
