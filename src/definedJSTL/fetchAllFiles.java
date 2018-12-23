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
		//保存subDirectoryPath,供UploadServlet使用
		pc.getSession().setAttribute("subDirectoryPath", subDirectoryPath);
		//如果subDirectoryPath为空,说明地址栏display.jsp没有传递参数,说明是在访问根本目录
		System.out.println("fetchAllFiles:30--"+subDirectoryPath);
		if(subDirectoryPath==null){
			//从根目录开始遍历
			directoryAbsolutePath=rootAbsolutePath;
			directoryRelativePath="";
		}
		else{
			//从子目录开始遍历
			directoryAbsolutePath=rootAbsolutePath+subDirectoryPath;
			directoryRelativePath=subDirectoryPath;
		}
			
			
		
		File file=new File(directoryAbsolutePath);
		System.out.println("fetchAllFiles:45--"+directoryAbsolutePath);
		//如果是文件夹才执行操作
		if(file.isDirectory()){
			//获取file文件夹下的所有子节点
			File files[]=file.listFiles();
			//循环遍历
			for(File f:files){
				//拼接路径名
				String fileRelativePath=directoryRelativePath+"\\"+f.getName();
				System.out.println("fetchAllFiles:54--"+fileRelativePath);
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
				//隐藏域name和value属性值都为fileRelativePath
				pc.getOut().print("<input type='hidden' name=\'"+fileRelativePath+"\' value=\'"+fileRelativePath+"\'>");
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
