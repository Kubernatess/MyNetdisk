

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		//解析request
		try {
			List<FileItem> list=upload.parseRequest(request);
			//循环遍历
			for(FileItem fileItem:list){
				//isFormField()返回true表示普通文本项,返回false表示文件上传项
				if(fileItem.isFormField()){
					String name=fileItem.getFieldName();
					String value=fileItem.getString();
				}else{
					//文件上传项
					String filename=fileItem.getName();
					//获取文件的输入流
					InputStream in=fileItem.getInputStream();
					//向某个文件中写入
					//向currentPath目录写入
					String currentPath=(String) request.getSession().getAttribute("currentPath");
					//获取输出流
					OutputStream os=new FileOutputStream(currentPath+"/"+filename);
					int len=0;
					byte b[]=new byte[1024];
					while((len=in.read(b))!=-1){
						os.write(b,0,len);
					}
					in.close();
					os.close();
					
					
				}
			}
			//文件上传完毕,重定向到首页
			response.sendRedirect("index.jsp");
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
