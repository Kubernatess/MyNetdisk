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
		//创建队列
		Queue<File> queue=new LinkedList<>();
		//把根节点入队
		queue.offer(root);
		//如果队列不为空,一直循环
		while(!queue.isEmpty()){
			//先获取根节点
			File file=queue.poll();
			//获取file文件夹下的所有子节点
			File files[]=file.listFiles();
			//循环遍历
			for(File f:files){
				//拿到每一个file对象,判断file是文件还是文件夹
				if(f.isFile()){
					//如果是文件
					pc.getOut().write("<a class='col-md-2' href='#'><img src='images/folder.png' class='img-responsive'><p>"+f.getName()+"</p></a>");
				}
				else{
					//如果是文件夹
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
