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
//		addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (tool == "pen") {
//					if (SwingUtilities.isLeftMouseButton(e)) {
//						if (penFlag == false) {
//							currentX = e.getX();
//							currentY = e.getY();
//							graphics2D.drawLine(currentX, currentY, currentX,
//									currentY);
//							repaint();
//							oldX = currentX;
//							oldY = currentY;
//							penFlag = true;
//						} else {
//							currentX = e.getX();
//							currentY = e.getY();
//							graphics2D.setStroke(new BasicStroke(stroke));
//							graphics2D.drawLine(oldX, oldY, currentX, currentY);
//							repaint();
//							oldX = currentX;
//							oldY = currentY;
//						}
//					} else if (SwingUtilities.isRightMouseButton(e)) {
//						penFlag = false;
//					}
//
//				}
//			}
//
//		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (tool == "pensil") {
					currentX = e.getX();
					currentY = e.getY();
					graphics2D.setStroke(new BasicStroke(stroke));
					graphics2D.drawLine(currentX, currentY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}else if (tool == "pen") {
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
							graphics2D.setStroke(new BasicStroke(stroke));
							graphics2D.drawLine(oldX, oldY, currentX, currentY);
							repaint();
							oldX = currentX;
							oldY = currentY;
						}
					} else if (SwingUtilities.isRightMouseButton(e)) {
						penFlag = false;
					}

				}
			}

		});
		// if the mouse is pressed it sets the oldX & oldY
		// coordinates as the mouses x & y coordinates
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println(tool);
				if (tool == "pensil") {
					currentX = e.getX();
					currentY = e.getY();
					if (graphics2D != null)
						graphics2D.setStroke(new BasicStroke(stroke));
					graphics2D.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}
			}
		});

		// while the mouse is dragged it sets currentX & currentY as the mouses
		// x and y
		// then it draws a line at the coordinates
		// it repaints it and sets oldX and oldY as currentX and currentY
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
	}

	public int getstroke() {
		return stroke;
	}
	public Boolean getPenFlag(){
		return penFlag;
	}
	public void setPenFlag(Boolean temp){
		penFlag = temp;
	}

	public void paint(Color c) {
		graphics2D.setPaint(c);
		repaint();
	}

	public void rect() {
		graphics2D.drawOval(100, 100, 100, 100);
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
		String[] formats = { "png", "jpg" };

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"png image", "png");
		FileNameExtensionFilter filterJPG = new FileNameExtensionFilter(
				"jpg image", "jpg");

		File saveFile = new File("savedimage.");
		if (filter.accept(saveFile)) {
			System.out.println("png");
		}
		if (filterJPG.accept(saveFile)) {
			System.out.println("jpg");

		}
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filterJPG);
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(saveFile);
		chooser.showSaveDialog(null);
		saveFile = chooser.getSelectedFile();

		try {
			ImageIO.write((RenderedImage) image, "png", saveFile);
		} catch (IOException ex) {
		}
	}

	public void setTool(String temp) {
		tool = temp;
	}
}
