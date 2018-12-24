import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ������  E-mail: example@aliyun.com
 * @version ����ʱ�䣺2018��12��16��  ����11:41:39
 * tags
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//��ȡ�������
		String fileRelativePath=request.getParameter("fileRelativePath");
		//��ȡ�ļ���
		int index=fileRelativePath.lastIndexOf("\\");
		String filename=fileRelativePath.substring(index);
		//����������
		String rootAbsolutePath=(String) request.getSession().getAttribute("rootAbsolutePath");
		InputStream in=new FileInputStream(rootAbsolutePath+"\\"+fileRelativePath);
		//����MINE����
		String type=getServletContext().getMimeType(filename);
		response.setContentType(type);
		
		//�Ը�������ʽ���ļ�
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		//IO�Ŀ���
		OutputStream os=response.getOutputStream();
		int len=0;
		byte b[]=new byte[1024];
		while((len=in.read(b))!=-1){
			os.write(b, 0, len);
		}
		in.close();
		os.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
