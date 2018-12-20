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
	
	private String currentDirectoryPath;
	
	public void setCurrentDirectoryPath(String currentDirectoryPath) {
		this.currentDirectoryPath = currentDirectoryPath;
	}

	public void doTag() throws JspException, IOException {
		//��OpenStack�˺�Ϊ��
		String directoryPath=null;
		System.out.println(currentDirectoryPath);
		//���currentPathΪ��,˵����ַ��display.jspû�д��ݲ���,˵�����ڷ��ʸ���Ŀ��
		if(currentDirectoryPath==null){
			//�Ȼ�ȡ��½�˺���OpenStack
			String username=(String) pc.getSession().getAttribute("username");
			//��ȡ/directory�Ĵ���·��
			String rootAbsolutePath=pc.getServletContext().getRealPath("/directory");
			//ƴ�� ,��ʱ�õ�OpenStack�Ĵ���·��
			directoryPath=rootAbsolutePath+"\\"+username;
		}
		else
			directoryPath=currentDirectoryPath;
		
		//������ļ��в�ִ�в���
		File directoryFile=new File(directoryPath);
		if(directoryFile.isDirectory()){
			//��¼��ǰ���ڵ�Ŀ¼,�Ա��ϴ��ļ���ʹ��
			pc.getSession().setAttribute("currentPath", directoryPath);
			//��ȡfile�ļ����µ������ӽڵ�
			File files[]=directoryFile.listFiles();
			//ѭ������
			for(File f:files){
				//ƴ��·����
				String fileAbsolutePath=directoryPath+"\\"+f.getName();
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
				//������name��value����ֵ��ΪfileAbsolutePath
				pc.getOut().print("<input type='hidden' name=\'"+fileAbsolutePath+"\' value=\'"+fileAbsolutePath+"\'>");
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
