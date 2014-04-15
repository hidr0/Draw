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
	File file;
	//File music;
	Clip clip;
	AudioInputStream audioIn;
	String fname;
	float b ;

	//JSlider slide ;
	FloatControl gainControl;
	
	
	
	public void chooseSound(){
		
		chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		file = chooser.getSelectedFile();


	}
	
	public void playSound(){
		try {
			// Open an audio input stream.

			 audioIn = AudioSystem.getAudioInputStream(file);
			// Get a sound clip resource.
			 clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

			gainControl.setValue(6.0f);
			
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

	public void setVolume(){
		
	}



}
