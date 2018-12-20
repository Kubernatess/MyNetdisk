package tags;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class definedJSTL extends SimpleTagSupport {

	private PageContext pc;
	
	public void doTag() throws JspException, IOException {
		//��OpenStack�˺�Ϊ��
		//�Ȼ�ȡ��½�˺���OpenStack
		String username=(String) pc.getSession().getAttribute("username");
		//��ȡ/directory�Ĵ���·��
		String rootPath=pc.getServletContext().getRealPath("/directory");
		//ƴ�� ,��ʱ�õ�OpenStack�Ĵ���·��
		rootPath=rootPath+"\\"+username;
		//��OpenStack�ļ���Ϊ���ڵ�
		File root=new File(rootPath);
		//��������
		Queue<File> queue=new LinkedList<>();
		//�Ѹ��ڵ����
		queue.offer(root);
		//������в�Ϊ��,һֱѭ��
		while(!queue.isEmpty()){
			//�Ȼ�ȡ���ڵ� ��OpenStack����·��
			File file=queue.poll();
			
			//��ȡfile�ļ����µ������ӽڵ�
			File files[]=file.listFiles();
			//ѭ������
			for(File f:files){
				//ƴ��·����
				String pathName=rootPath+"\\"+f.getName();
				//System.out.println(pathName);
				
				//��ȡ�ļ���,��ʾ��ҳ����
				StringBuilder filename=new StringBuilder(f.getName());
				if(filename.length()>=10){
					int start=filename.lastIndexOf(".");
					int end=filename.length();
					filename.replace(start, end, "...");
				}
				//�õ�ÿһ��file����,�ж�file���ļ������ļ���
				if(f.isFile()){
					//������ļ�
					pc.getOut().write("<a class='col-md-2' href='#'><input type='hidden' name=\'"+pathName+"\' value=\'"+pathName+"\'><img src='images/folder.png' title=\'"+f.getName()+"\' class='img-responsive'><p>"+filename+"</p></a>");
				}
				else{
					//������ļ���,���
					queue.offer(f);
					//pc.getOut().write("<a class='col-md-2' href='#'><img src='images/file.jpg' title=\'"+f.getName()+"\' class='img-responsive'><p>"+filename+"</p></a>");
				}
			}
		}
	}

	public void setJspBody(JspFragment jspBody) {
		
	}

	public void setJspContext(JspContext pc) {
		this.pc=(PageContext) pc;
	}
	
}
