package definedJSTL;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class fetchAllFiles extends SimpleTagSupport {

	private PageContext pc;
	
	private String subDirectoryPath;

	public void setSubDirectoryPath(String subDirectoryPath) {
		this.subDirectoryPath = subDirectoryPath;
	}

	public void doTag() throws JspException, IOException {
		String directoryAbsolutePath=null;
		String directoryRelativePath=null;
		String rootAbsolutePath=(String) pc.getSession().getAttribute("rootAbsolutePath");
		//����subDirectoryPath,��UploadServletʹ��
		pc.getSession().setAttribute("subDirectoryPath", subDirectoryPath);
		//���subDirectoryPathΪ��,˵����ַ��display.jspû�д��ݲ���,˵�����ڷ��ʸ���Ŀ¼
		System.out.println("fetchAllFiles:30--"+subDirectoryPath);
		if(subDirectoryPath==null){
			//�Ӹ�Ŀ¼��ʼ����
			directoryAbsolutePath=rootAbsolutePath;
			directoryRelativePath="";
		}
		else{
			//����Ŀ¼��ʼ����
			directoryAbsolutePath=rootAbsolutePath+subDirectoryPath;
			directoryRelativePath=subDirectoryPath;
		}
			
			
		
		File file=new File(directoryAbsolutePath);
		System.out.println("fetchAllFiles:45--"+directoryAbsolutePath);
		//������ļ��в�ִ�в���
		if(file.isDirectory()){
			//��ȡfile�ļ����µ������ӽڵ�
			File files[]=file.listFiles();
			//ѭ������
			for(File f:files){
				//ƴ��·����
				String fileRelativePath=directoryRelativePath+"\\"+f.getName();
				System.out.println("fetchAllFiles:54--"+fileRelativePath);
				//��ȡ�ļ�������:������ʾ��ҳ���ϵ��ļ���
				StringBuilder filename=new StringBuilder(f.getName());
				if(filename.length()>=10){
					//�ļ���������ȳ���10,�����ʹ��ʡ�Ժ�
					int start=filename.lastIndexOf(".");
					int end=filename.length();
					filename.replace(start, end, "...");
				}
				//�õ�ÿһ��file����,�ж�file���ļ������ļ���
				String src=null;
				if(f.isFile())
					src="images/file.png";
				else
					src="images/folder.png";
						
				//����ļ����ļ���
				pc.getOut().print("<a class='col-md-2' href='#'>");
				//������name��value����ֵ��ΪfileRelativePath
				pc.getOut().print("<input type='hidden' name=\'"+fileRelativePath+"\' value=\'"+fileRelativePath+"\'>");
				//title���� �����ͣ��ͼƬ�ϻ���ʾ���ļ���
				pc.getOut().print("<img src=\'"+src+"\' title=\'"+f.getName()+"\' class='img-responsive'>");
				//������ʾ��ҳ����ļ���
				pc.getOut().println("<p>"+filename+"</p></a>");
			}
		}
		else{
			//������ļ�,����������
			RequestDispatcher dispatcher=pc.getRequest().getRequestDispatcher("/DownloadServlet");
			try {
				dispatcher.forward(pc.getRequest(), pc.getResponse());
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setJspBody(JspFragment jspBody) {
		
	}

	public void setJspContext(JspContext pc) {
		this.pc=(PageContext) pc;
	}
	
}
