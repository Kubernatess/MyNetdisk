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
		String username=(String) pc.getSession().getAttribute("username");
		String rootPath=pc.getServletContext().getRealPath("/directory/"+username);
		
		File root=new File(rootPath);
		//��������
		Queue<File> queue=new LinkedList<>();
		//�Ѹ��ڵ����
		queue.offer(root);
		//������в�Ϊ��,һֱѭ��
		while(!queue.isEmpty()){
			//�Ȼ�ȡ���ڵ�
			File file=queue.poll();
			//��ȡfile�ļ����µ������ӽڵ�
			File files[]=file.listFiles();
			//ѭ������
			for(File f:files){
				//�õ�ÿһ��file����,�ж�file���ļ������ļ���
				if(f.isFile()){
					//������ļ�
					pc.getOut().write("<a class='col-md-2' href='#'><img src='images/folder.png' class='img-responsive'><p>"+f.getName()+"</p></a>");
				}
				else{
					//������ļ���
					queue.offer(f);
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
