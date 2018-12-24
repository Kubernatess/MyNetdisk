package definedJSTL;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Stack;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class fetchFilePath extends SimpleTagSupport {

	private PageContext pc;
	
	private String subDirectoryPath;
	
	public void setSubDirectoryPath(String subDirectoryPath) {
		this.subDirectoryPath = subDirectoryPath;
	}

	public void doTag() throws JspException, IOException {
		String username=(String) pc.getSession().getAttribute("username");
		pc.getOut().print("<a href='index.jsp'>"+username+"</a>");//必须输出的
	
		if(subDirectoryPath!=null){
			//对子目录路径名进行分割
			StringBuilder strBuilder=new StringBuilder(subDirectoryPath);
			int index=strBuilder.lastIndexOf("\\");
			Stack<String> stack=new Stack<>();
			while(index!=-1){
				String substr=strBuilder.substring(index+1);
				strBuilder.delete(index,strBuilder.length()).toString();
				index=strBuilder.lastIndexOf("\\");
				stack.push(substr);
			}
			String href="";
			while(!stack.empty()){
				String pop=stack.pop();
				href=href+"\\"+pop;
				System.out.println("fetchFilePath:42--"+href);
				String encondinghref=URLEncoder.encode(href);
				pc.getOut().print("<span class='glyphicon glyphicon-chevron-right'></span><a href=\'index.jsp?subDirectoryPath="+encondinghref+"\'>"+pop+"</a>");
				
			}
			
		}
	}

	public void setJspBody(JspFragment jspBody) {
		// TODO Auto-generated method stub
		super.setJspBody(jspBody);
	}

	public void setJspContext(JspContext pc) {
		this.pc=(PageContext) pc;
	}
	
}
