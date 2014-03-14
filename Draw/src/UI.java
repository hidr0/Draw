import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

	PadDraw drawPad = new PadDraw();
	JFrame drawingFrame = new JFrame();
	JPanel drawingPanel = new JPanel();
	
	JPanel buttonPanel = new JPanel();
	JFrame buttonFrame = new JFrame();
	
	Boolean showColorFrame = true;
	
	int tool = 2;

	// Drawing Panel Buttons
	JButton[] drawingPanelButtons = new JButton[7];
	
	JButton clearButton = new JButton("Clear");
	JButton plusButton = new JButton("Plus");
	JButton minusButton = new JButton("Minus");
	JButton exitButton = new JButton("Exit");
	JButton colorStatusButton = new JButton("");
	JButton strokeStatusButton = new JButton("Stroke = 1");
	JButton colorButton = new JButton("Colors");
	
	

	// Color Buttons and Colors
	JButton[] colorButtons = new JButton[13];
	final Color[] colors = new Color[13];

	public void beautify() {
		colorButtonsCreate();
		drawingFrame();
		buttonFrame();
		//drawPad.rect();
	}

	void functionality() {
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonFrame.isVisible()){
					buttonFrame.setVisible(false);
					showColorFrame = false;
				} else if(!buttonFrame.isVisible()){
					buttonFrame.setVisible(true);
					showColorFrame = true;
				}	
			}
		});
		
		colorStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonFrame.isVisible()){
					buttonFrame.setVisible(false);
					showColorFrame = false;
				} else if(!buttonFrame.isVisible()){
					buttonFrame.setVisible(true);
					showColorFrame = true;
				}	
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawingFrame.dispose();
				buttonFrame.dispose();
				System.exit(0); // Ne znam dali e prawilno
			}

		});
		
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.clear();
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
		functionality();
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
		drawingPanel.add(exitButton);
		drawingPanel.add(colorButton);
		drawingPanel.add(minusButton);
		drawingPanel.add(strokeStatusButton);
		drawingPanel.add(plusButton);
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
	
	void makeColorButtons(){
		for (int i = 0; i < colorButtons.length; i++) {
			colorButtons[i] = new JButton();
		}
	}

}
