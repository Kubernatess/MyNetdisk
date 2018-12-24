
import java.util.Iterator;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		StringBuilder strBuilder=new StringBuilder("\\apache-tomcat-9.0.11\\webapps\\MyNetdisk\\WebContent\\directory\\OpenStack");
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
			System.out.println(href);
		}
	}

}
