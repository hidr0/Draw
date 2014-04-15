import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class picAchooseSound{
	JFileChooser chooser;
	File file,music;
	Clip clip;
	AudioInputStream audioIn;
	String fname;
	float b ;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton button = new JButton();
	JSlider slide = new JSlider();
	FloatControl gainControl;
	
	public static void main(String[] args) {
		picAchooseSound picSound = new picAchooseSound();
		picSound.chooseSound();
	}
	public void chooseSound() {

		frame = new JFrame("YOO");
		panel = new JPanel();
		button = new JButton("Open");
		slide = new JSlider();
		slide.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
		        if (!source.getValueIsAdjusting()) {
		        	int a = source.getValue();
		        	 b = (float)a;
		        	System.out.println(b);
		        	
		        }
				
			}
		});
		slide.setMajorTickSpacing(1);
		slide.setMaximum(6);
		
        slide.setMinorTickSpacing(1);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				music = chooser.getSelectedFile();
				try {
					// Open an audio input stream.

					 audioIn = AudioSystem.getAudioInputStream(music);
					// Get a sound clip resource.
					 clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input stream.
					clip.open(audioIn);
					gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

					gainControl.setValue(b);
					
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (UnsupportedAudioFileException w) {
					w.printStackTrace();
				} catch (IOException w) {
					w.printStackTrace();
				} catch (LineUnavailableException w) {
					w.printStackTrace();
				}


			}
		});
		
		panel.add(button);
		panel.add(slide);
		frame.add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);

	




	}




}