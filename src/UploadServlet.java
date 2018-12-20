

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
		//���������ļ�������
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//�������Ľ�������
		ServletFileUpload upload=new ServletFileUpload(factory);
		//����request
		try {
			List<FileItem> list=upload.parseRequest(request);
			//ѭ������
			for(FileItem fileItem:list){
				//isFormField()����true��ʾ��ͨ�ı���,����false��ʾ�ļ��ϴ���
				if(fileItem.isFormField()){
					String name=fileItem.getFieldName();
					String value=fileItem.getString();
				}else{
					//�ļ��ϴ���
					String filename=fileItem.getName();
					//��ȡ�ļ���������
					InputStream in=fileItem.getInputStream();
					//��ĳ���ļ���д��
					//��currentPathĿ¼д��
					String currentPath=(String) request.getSession().getAttribute("currentPath");
					//��ȡ�����
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
			//�ļ��ϴ����,�ض�����ҳ
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
