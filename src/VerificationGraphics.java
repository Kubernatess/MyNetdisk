

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
		//����session����
		HttpSession session=request.getSession();
		//�����ַ�����,���ڱ�����֤��
		char character[]=new char[4];
		
		int width=120;
		int height=40;
		//��ȡ��������
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//��ȡ���ʶ���
		Graphics2D g=(Graphics2D) image.getGraphics();
		//��һ��ʵ�ľ���
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		//��һ���߿�(�����ľ���)
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width-1, height-1);
		
		//��ǰ׼��������
		String words="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		//�����ȡ4���ַ�
		Random random=new Random();
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial",Font.BOLD,20));
		int x=20,y=25;
		for(int i=0;i<4;i++){
			//�����ת��Ч��
			//�����ȡ����30֮��ĽǶ�
			int point=random.nextInt(60)-30;
			double radian=point*Math.PI/180;
			//������
			g.rotate(radian,x,y);
			
			int index=random.nextInt(words.length());
			char ch=words.charAt(index);
			character[i]=ch;
			//����������ȥ
			g.drawString(ch+"", x, y);
			g.rotate(-radian,x,y);
			x+=20;
		}
		//��������
		g.setColor(Color.GREEN);
		int x1,x2,y1,y2;
		for(int i=0;i<4;i++){
			x1=random.nextInt(width);
			x2=random.nextInt(width);
			y1=random.nextInt(height);
			y2=random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		
		//���ַ�����ת�����ַ���
		String str=new String(character);
		//���ַ������浽session�������
		session.setAttribute("verification", str);
		
		//�ͷ���Դ
		g.dispose();
		//���ڴ��е�ͼƬ������ͻ�����
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
