
public class Main {

	public static void main(String[] args) {
		StringBuilder s=new StringBuilder("������������.jpg");
		
		if(s.length()>=10){
			int start=s.lastIndexOf(".");
			int end=s.length();
			s.replace(start, end, "...");
		}
		
		System.out.println(s);

		
	}

}
