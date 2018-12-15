

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Verification
 */
@WebServlet("/VerificationGraphics")
public class VerificationGraphics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificationGraphics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建session对象
		HttpSession session=request.getSession();
		//创建字符数组,用于保存验证码
		char character[]=new char[4];
		
		int width=120;
		int height=40;
		//获取画布对象
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取画笔对象
		Graphics2D g=(Graphics2D) image.getGraphics();
		//画一个实心矩形
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		//画一个边框(即空心矩形)
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width-1, height-1);
		
		//提前准备好数据
		String words="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		//随机获取4个字符
		Random random=new Random();
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial",Font.BOLD,20));
		int x=20,y=25;
		for(int i=0;i<4;i++){
			//完成旋转的效果
			//随机获取正负30之间的角度
			int point=random.nextInt(60)-30;
			double radian=point*Math.PI/180;
			//画弧线
			g.rotate(radian,x,y);
			
			int index=random.nextInt(words.length());
			char ch=words.charAt(index);
			character[i]=ch;
			//画到画布上去
			g.drawString(ch+"", x, y);
			g.rotate(-radian,x,y);
			x+=20;
		}
		//画干扰线
		g.setColor(Color.GREEN);
		int x1,x2,y1,y2;
		for(int i=0;i<4;i++){
			x1=random.nextInt(width);
			x2=random.nextInt(width);
			y1=random.nextInt(height);
			y2=random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		
		//将字符数组转换成字符串
		String str=new String(character);
		//将字符串保存到session域对象中
		session.setAttribute("verification", str);
		
		//释放资源
		g.dispose();
		//把内存中的图片输出到客户端上
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
