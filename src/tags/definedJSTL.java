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
		//以OpenStack账号为例
		//先获取登陆账号名OpenStack
		String username=(String) pc.getSession().getAttribute("username");
		//获取/directory的磁盘路径
		String rootPath=pc.getServletContext().getRealPath("/directory");
		//拼接 ,此时得到OpenStack的磁盘路径
		rootPath=rootPath+"\\"+username;
		//以OpenStack文件夹为根节点
		File root=new File(rootPath);
		//创建队列
		Queue<File> queue=new LinkedList<>();
		//把根节点入队
		queue.offer(root);
		//如果队列不为空,一直循环
		while(!queue.isEmpty()){
			//先获取根节点 即OpenStack磁盘路径
			File file=queue.poll();
			
			//获取file文件夹下的所有子节点
			File files[]=file.listFiles();
			//循环遍历
			for(File f:files){
				//拼接路径名
				String pathName=rootPath+"\\"+f.getName();
				//System.out.println(pathName);
				
				//截取文件名,显示到页面上
				StringBuilder filename=new StringBuilder(f.getName());
				if(filename.length()>=10){
					int start=filename.lastIndexOf(".");
					int end=filename.length();
					filename.replace(start, end, "...");
				}
				//拿到每一个file对象,判断file是文件还是文件夹
				if(f.isFile()){
					//如果是文件
					pc.getOut().write("<a class='col-md-2' href='#'><input type='hidden' name=\'"+pathName+"\' value=\'"+pathName+"\'><img src='images/folder.png' title=\'"+f.getName()+"\' class='img-responsive'><p>"+filename+"</p></a>");
				}
				else{
					//如果是文件夹,入队
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
