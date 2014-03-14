import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Field;


@SuppressWarnings("serial")
class PadDraw extends JComponent{
	int stroke = 1;
	Image image;
	//this is gonna be your image that you draw on
	Graphics2D graphics2D;
	//this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;
	//these are gonna hold our mouse coordinates

	//Now for the constructors
	public PadDraw(){
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
					System.out.println(oldX);
					System.out.println(oldY);
					graphics2D.setStroke(new BasicStroke(stroke));
					graphics2D.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					
				oldX = currentX;
				oldY = currentY;
			}
			
			public void mouseClicked(MouseEvent e){
				if(graphics2D == null){
					currentX = oldX = e.getX();
					currentY = oldY  = e.getY();
					System.out.println("if");
				}else{
					System.out.println("else");
					currentX = e.getX();
					currentY = e.getY();
				}
				//graphics2D.setStroke(new BasicStroke(stroke));
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				oldX = currentX;
				oldY = currentY;
			}
		
		});
		//if the mouse is pressed it sets the oldX & oldY
		//coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(graphics2D != null)
				graphics2D.setStroke(new BasicStroke(stroke));
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				for (int i = 0; i < 20; i++) {
					graphics2D.drawOval(currentX, currentY, i, i);
				}
				repaint();
				oldX = currentX;
				oldY = currentY;
			}
		});
		
	
		//while the mouse is dragged it sets currentX & currentY as the mouses x and y
		//then it draws a line at the coordinates
		//it repaints it and sets oldX and oldY as currentX and currentY
	}

	public void paintComponent(Graphics g){
		if(image == null){
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);// ����� ����� �� ������!?
	}

	public void clear(){
		graphics2D.setPaint(Color.white);//����� ��� ����.
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);// ����� ��� ������������, �� �� ������ ������ � ���������� �� ���������.
		graphics2D.setPaint(Color.black);// ����� ���� ���� ������ clear ����� default ����, �����
		repaint();
	}
	
	public void stroke(String porm){
		if(porm == "+"){
			if(stroke < 50){
				stroke++;
			}
		}else if(porm =="-"){
			if(stroke > 1){
				stroke--;
			}
		}
	}
	
	public int getstroke(){
		
		return stroke;
	}
	
	
	
	public void paint(String collor) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		Color colorr;
		    java.lang.reflect.Field field = Color.class.getField(collor);
		    colorr = (Color)field.get(null);
		
	
		graphics2D.setPaint(colorr);
		repaint();
	}
	
	public void paint(Color c){
		graphics2D.setPaint(c);
		repaint();
	}
	
	public void tool(){
		
	}
	
	public void rect(){
		graphics2D.drawOval(100, 100, 100, 100);
		repaint();
		
		
	}
	

}

