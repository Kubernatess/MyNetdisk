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
 * @author 黄晓霖  E-mail: example@aliyun.com
 * @version 创建时间：2018年12月16日  下午11:41:39
 * tags
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取请求参数
		String fileRelativePath=request.getParameter("fileRelativePath");
		//提取文件名
		int index=fileRelativePath.lastIndexOf("\\");
		String filename=fileRelativePath.substring(index);
		//创建输入流
		String rootAbsolutePath=(String) request.getSession().getAttribute("rootAbsolutePath");
		InputStream in=new FileInputStream(rootAbsolutePath+"\\"+fileRelativePath);
		//设置MINE类型
		String type=getServletContext().getMimeType(filename);
		response.setContentType(type);
		
		//以附件的形式打开文件
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		
		//IO的拷贝
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
