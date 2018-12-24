

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建磁盘文件工厂类
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//创建核心解析对象
		ServletFileUpload upload=new ServletFileUpload(factory);
		String subDirectoryPath=null;
		//解析request
		try {
			List<FileItem> list=upload.parseRequest(request);
			//循环遍历
			for(FileItem fileItem:list){
				String filename=fileItem.getName();
				//获取文件的输入流
				InputStream in=fileItem.getInputStream();
				//向某个文件中写入
				//向当前目录目录写入
				String rootAbsolutePath=(String) request.getSession().getAttribute("rootAbsolutePath");
				subDirectoryPath=(String) request.getSession().getAttribute("subDirectoryPath");
				String path="";
				//如果subDirectoryPath为空,说明地址栏display.jsp没有传递参数,说明是在访问根本目录
				if(subDirectoryPath==null){
					//从根目录开始输出
					path=rootAbsolutePath+"/"+filename;
				}
				else{
					//从子目录开始输出
					path=rootAbsolutePath+"/"+subDirectoryPath+"/"+filename;
				}
				//获取输出流
				OutputStream os=new FileOutputStream(path);
				int len=0;
				byte b[]=new byte[1024];
				while((len=in.read(b))!=-1){
					os.write(b,0,len);
				}
				in.close();
				os.close();	
			}
			//文件上传完毕,重定向到首页
			if(subDirectoryPath==null){
				response.sendRedirect("index.jsp");
			}
			else{
				String encodingSubDirectoryPath=URLEncoder.encode(subDirectoryPath);
				response.sendRedirect("index.jsp?subDirectoryPath="+encodingSubDirectoryPath);
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
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
