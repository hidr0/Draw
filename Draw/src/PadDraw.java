import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
class PadDraw extends JComponent {

	int width, height = 0;
	Boolean penFlag = false;
	String tool = "pensil";
	int stroke = 1;
	Image image;
	BufferedImage img;
	JFileChooser chooser;
	File file;
	// this is gonna be your image that you draw on
	Graphics2D graphics2D;
	// this is what we'll be using to draw on
	int currentX, currentY, oldX, oldY;

	// these are gonna hold our mouse coordinates

	// Now for the constructors
	public PadDraw() {
		setDoubleBuffered(false);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (tool == "pensil") {
					currentX = e.getX();
					currentY = e.getY();
					graphics2D.drawLine(currentX, currentY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				} else if (tool == "pen") {
					if (SwingUtilities.isLeftMouseButton(e)) {
						if (penFlag == false) {
							currentX = e.getX();
							currentY = e.getY();
							graphics2D.drawLine(currentX, currentY, currentX,
									currentY);
							repaint();
							oldX = currentX;
							oldY = currentY;
							penFlag = true;
						} else {
							currentX = e.getX();
							currentY = e.getY();
							graphics2D.drawLine(oldX, oldY, currentX, currentY);
							repaint();
							oldX = currentX;
							oldY = currentY;
						}
					} else if (SwingUtilities.isRightMouseButton(e)) {
						penFlag = false;
					}

				} else if (tool == "rect") {
					rect(e.getX() - width / 2, e.getY() - height / 2, width, height);
				} else if (tool == "circle") {
					circle(e.getX() - width / 2, e.getY() - height / 2, width, height);
				} else if(tool == "spray"){
					spray(e.getX() - width / 2, e.getY() - height / 2, width, height);
				}
			}

		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println(tool);
				if (tool == "pensil") {
					currentX = e.getX();
					currentY = e.getY();
					if (graphics2D != null)
						graphics2D.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

	public void stroke(String porm) {
		if (porm == "+") {
			if (stroke < 50) {
				stroke++;
			}
		} else if (porm == "-") {
			if (stroke > 1) {
				stroke--;
			}
		}
		graphics2D.setStroke(new BasicStroke(stroke));
	}

	public int getstroke() {
		return stroke;
	}

	public Boolean getPenFlag() {
		return penFlag;
	}

	public void setPenFlag(Boolean temp) {
		penFlag = temp;
	}

	public void paint(Color c) {
		graphics2D.setPaint(c);
		repaint();
	}

	public void rect(int x, int y, int width, int height) {
		graphics2D.drawRect(x, y, width, height);
		repaint();
	}

	public void circle(int x, int y, int width, int height) {
		graphics2D.drawOval(x, y, width, height);
		repaint();
	}
	public void spray(int x, int y, int width, int height){
		for (int i = 0; i < width*height/1000; i++) {
			int randx = (x + (int) (Math.random() * width));
			int randy =	(y + (int) (Math.random() * height));
			System.out.println(height+100*stroke);
			graphics2D.drawLine(randx , randy, randx, randy);
		}
		repaint();
	}

	public void openImage() {
		chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		file = chooser.getSelectedFile();
		try {
			img = ImageIO.read(file);
		} catch (IOException e1) {
		}

		graphics2D.drawImage(img, 0, 0, null);
		repaint();

	}

	public void saveImage() {
		


		FileNameExtensionFilter filterPNG = new FileNameExtensionFilter("png",
				"png");
		

		String[] extension = filterPNG.getExtensions();

		File saveFile = new File("savedimage."+extension[0]);

		

		JFileChooser chooser2 = new JFileChooser();
		chooser2.setFileFilter(filterPNG);
		
		chooser2.setSelectedFile(saveFile);
		chooser2.showSaveDialog(null);
		saveFile = chooser2.getSelectedFile();

		try {
			ImageIO.write((RenderedImage) image, "jpg", saveFile);
		} catch (IOException ex) {

			System.out.println("In exeption");
		}
	}

	public void setTool(String temp) {
		tool = temp;
	}

	public void setShpaeSize(int w, int h) {
		if (w < 1280 && h < 720) {
			height = h;
			width = w;
		}
	}

}
