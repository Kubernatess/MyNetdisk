

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
		//���������ļ�������
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//�������Ľ�������
		ServletFileUpload upload=new ServletFileUpload(factory);
		String subDirectoryPath=null;
		//����request
		try {
			List<FileItem> list=upload.parseRequest(request);
			//ѭ������
			for(FileItem fileItem:list){
				String filename=fileItem.getName();
				//��ȡ�ļ���������
				InputStream in=fileItem.getInputStream();
				//��ĳ���ļ���д��
				//��ǰĿ¼Ŀ¼д��
				String rootAbsolutePath=(String) request.getSession().getAttribute("rootAbsolutePath");
				subDirectoryPath=(String) request.getSession().getAttribute("subDirectoryPath");
				String path="";
				//���subDirectoryPathΪ��,˵����ַ��display.jspû�д��ݲ���,˵�����ڷ��ʸ���Ŀ¼
				if(subDirectoryPath==null){
					//�Ӹ�Ŀ¼��ʼ���
					path=rootAbsolutePath+"/"+filename;
				}
				else{
					//����Ŀ¼��ʼ���
					path=rootAbsolutePath+"/"+subDirectoryPath+"/"+filename;
				}
				//��ȡ�����
				OutputStream os=new FileOutputStream(path);
				int len=0;
				byte b[]=new byte[1024];
				while((len=in.read(b))!=-1){
					os.write(b,0,len);
				}
				in.close();
				os.close();	
			}
			//�ļ��ϴ����,�ض�����ҳ
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
