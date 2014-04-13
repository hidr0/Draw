

import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
	SoundClipTest sound = new SoundClipTest();
	PadDraw drawPad = new PadDraw();
	JFrame drawingFrame = new JFrame();
	JPanel drawingPanel = new JPanel();
	
	JPanel buttonPanel = new JPanel();
	JFrame buttonFrame = new JFrame();
	
	JPanel toolPanel = new JPanel();
	JFrame toolFrame = new JFrame();

	// Drawing Panel Buttons
	JButton clearButton = new JButton("Clear");
	JButton plusButton = new JButton("Plus");
	JButton minusButton = new JButton("Minus");
	JButton exitButton = new JButton("Exit");
	JButton colorStatusButton = new JButton("");
	JButton toolStatusButton = new JButton("Pensil");
	JButton strokeStatusButton = new JButton("Stroke = 1");
	JButton muteButton = new JButton("Mute");
	JButton testButton = new JButton("Test");
	JButton openButton = new JButton("Open");
	JButton saveButton = new JButton("Save");
	
	//Tool Buttons
	JButton pensilButton = new JButton("Pensil");
	JButton penButton = new JButton("Pen"); 
	JButton shapeButton = new JButton("Shape");
	JButton sprayButton = new JButton("Spray");

	// Color Buttons and Colors
	JButton[] colorButtons = new JButton[13];
	final Color[] colors = new Color[13];
	
	
	public void beautify() {
		colorButtonsCreate();
		drawingFrame();
		buttonFrame();
		toolFrame();
		
	}

	void buttonFunctionality() {
		
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.mute();
				
			}
		});
		
		toolStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(toolFrame.isVisible());
				if(toolFrame.isVisible()){
					toolFrame.setVisible(false);
				} else{
					toolFrame.setVisible(true);
				}	
				
			}
		});
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.openImage();
				
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.saveImage();
				}
		});
		
		
		
		colorStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonFrame.isVisible()){
					buttonFrame.setVisible(false);
				}else{
					buttonFrame.setVisible(true);
				}	
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Ne znam dali e prawilno
			}

		});
		
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.clear();
				drawPad.setPenFlag(false);
			}

		});
		
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.stroke("+");
				
				StringBuilder sb = new StringBuilder();
				sb.append("Stroke = ");
				
				
				int strokeSize = drawPad.getstroke();
				sb.append(strokeSize);
				String strI = sb.toString();
				strokeStatusButton.setText(strI);
			}

		});
		
		minusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.stroke("-");
				StringBuilder sb = new StringBuilder();
				sb.append("Stroke = ");
				
				
				int strokeSize = drawPad.getstroke();
				sb.append(strokeSize);
				String strI = sb.toString();
				strokeStatusButton.setText(strI);
			}

		});
		
		for (int i = 0; i < colorButtons.length; i++) {
			final int j = i;
			colorButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						drawPad.paint(colors[j]);
						colorStatusButton.setBackground(colors[j]);
						buttonFrame.setVisible(false);
				}

			});
		}
		pensilButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolFrame.setVisible(false);
				toolStatusButton.setText("Pensil");
				drawPad.setTool("pensil");
		}

	});
		penButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.setTool("pen");
				toolStatusButton.setText("Pen");
				toolFrame.setVisible(false);
				drawPad.setPenFlag(false);
		}

	});
		shapeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolStatusButton.setText("Shape");
				drawPad.setTool("shape");
				toolFrame.setVisible(false);
		}

	}); 
		sprayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolStatusButton.setText("Spray");
				drawPad.setTool("spray");
				toolFrame.setVisible(false);
		}

	});
		
	
	}
	

	void setcollors() {
		colors[0]=Color.black;		
		colors[1]=Color.blue;		
		colors[2]=Color.cyan;		
		colors[3]=Color.DARK_GRAY;		
		colors[4]=Color.gray;		
		colors[5]=Color.green;		
		colors[6]=Color.LIGHT_GRAY;		
		colors[7]=Color.magenta;		
		colors[8]=Color.orange;		
		colors[9]=Color.pink;		
		colors[10]=Color.red;		
		colors[11]=Color.white;		
		colors[12]=Color.yellow;
		
		
		for (int i = 0; i < colors.length; i++) {
			colorButtons[i].setBackground(colors[i]);
			colorButtons[i].setOpaque(true);
			colorButtons[i].setBorderPainted(false);
		}
		
	
	}
	
	void colorButtonsCreate(){
		makeColorButtons();
		setcollors();
		buttonFunctionality();
	}
	
	

	void drawingFrame() {
		drawingPanel();
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		
		drawingFrame.setVisible(true);
		drawingFrame.setResizable(false);
		drawingFrame.setSize(xSize,ySize);
		JRootPane root = drawingFrame.getRootPane( );
		root.putClientProperty( "Window.shadow", Boolean.FALSE );
		
		drawingFrame.add(drawPad,BorderLayout.CENTER );
		drawingFrame.add(drawingPanel,BorderLayout.NORTH);
		drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void drawingPanel(){
		colorStatusButton.setOpaque(true);
		colorStatusButton.setBackground(Color.black);
		colorStatusButton.setBorderPainted(false);
		toolStatusButton.setOpaque(true);
		toolStatusButton.setBackground(Color.black);
		toolStatusButton.setBorderPainted(false);
		toolStatusButton.setForeground(Color.white);
		toolStatusButton.setFont(new Font("Dialog", Font.BOLD, 16));
		drawingPanel.add(openButton);
		drawingPanel.add(saveButton);
		drawingPanel.add(exitButton);
		drawingPanel.add(toolStatusButton);
		drawingPanel.add(minusButton);
		drawingPanel.add(strokeStatusButton);
		drawingPanel.add(plusButton);
		drawingPanel.add(muteButton);
		drawingPanel.add(colorStatusButton);
		drawingPanel.add(clearButton);
	}
	
	void buttonFrame(){
		buttonPanel();
		buttonFrame.add(buttonPanel);
		buttonFrame.setAlwaysOnTop(true);
		buttonFrame.setSize(180,200);
		buttonFrame.setLocation(150, 150);
		buttonFrame.setResizable(false);
		buttonFrame.setVisible(true);
		JRootPane root = buttonFrame.getRootPane( );
		root.putClientProperty( "Window.shadow", Boolean.FALSE );
	}
	
	void buttonPanel(){
		buttonPanel.setLayout(new GridLayout(5,5));
		buttonPanel.setPreferredSize(new Dimension(30,100));
		buttonPanel.setBackground(new Color(60, 60, 60));
		for (int i = 0; i < colorButtons.length; i++) {
			buttonPanel.add(colorButtons[i]);
		}
	}
	void toolFrame(){
		toolPanel();
		toolFrame.add(toolPanel);
		toolFrame.setAlwaysOnTop(true);
		toolFrame.setSize(180,200);
		toolFrame.setLocation(500, 500);
		toolFrame.setResizable(false);
		toolFrame.setVisible(true);
		JRootPane root = buttonFrame.getRootPane( );
		root.putClientProperty( "Window.shadow", Boolean.FALSE );
	}
	void toolPanel(){
		toolPanel.setLayout(new GridLayout(5,5));
		toolPanel.setPreferredSize(new Dimension(30,100));
		toolPanel.setBackground(new Color(60, 60, 60));
		toolPanel.add(pensilButton);
		toolPanel.add(penButton);
		toolPanel.add(shapeButton);
		toolPanel.add(sprayButton);
	}
	
	void makeColorButtons(){
		for (int i = 0; i < colorButtons.length; i++) {
			colorButtons[i] = new JButton();
		}
	}
	

}
