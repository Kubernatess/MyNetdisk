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
		//以OpenStack账号为例
		String directoryPath=null;
		System.out.println(currentDirectoryPath);
		//如果currentPath为空,说明地址栏display.jsp没有传递参数,说明是在访问根本目鲈
		if(currentDirectoryPath==null){
			//先获取登陆账号名OpenStack
			String username=(String) pc.getSession().getAttribute("username");
			//获取/directory的磁盘路径
			String rootAbsolutePath=pc.getServletContext().getRealPath("/directory");
			//拼接 ,此时得到OpenStack的磁盘路径
			directoryPath=rootAbsolutePath+"\\"+username;
		}
		else
			directoryPath=currentDirectoryPath;
		
		//如果是文件夹才执行操作
		File directoryFile=new File(directoryPath);
		if(directoryFile.isDirectory()){
			//记录当前所在的目录,以便上传文件所使用
			pc.getSession().setAttribute("currentPath", directoryPath);
			//获取file文件夹下的所有子节点
			File files[]=directoryFile.listFiles();
			//循环遍历
			for(File f:files){
				//拼接路径名
				String fileAbsolutePath=directoryPath+"\\"+f.getName();
				//截取文件名操作:最终显示在页面上的文件名
				StringBuilder filename=new StringBuilder(f.getName());
				if(filename.length()>=10){
					//文件名如果长度超过10,则后面使用省略号
					int start=filename.lastIndexOf(".");
					int end=filename.length();
					filename.replace(start, end, "...");
				}
				//拿到每一个file对象,判断file是文件还是文件夹
				String src=null;
				if(f.isFile())
					src="images/file.png";
				else
					src="images/folder.png";
						
				//输出文件或文件夹
				pc.getOut().print("<a class='col-md-2' href='#'>");
				//隐藏域name和value属性值都为fileAbsolutePath
				pc.getOut().print("<input type='hidden' name=\'"+fileAbsolutePath+"\' value=\'"+fileAbsolutePath+"\'>");
				//title属性 鼠标悬停在图片上会显示该文件名
				pc.getOut().print("<img src=\'"+src+"\' title=\'"+f.getName()+"\' class='img-responsive'>");
				//最终显示在页面的文件名
				pc.getOut().println("<p>"+filename+"</p></a>");
			}
		}
		else{
			//如果是文件,则请求下载
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
