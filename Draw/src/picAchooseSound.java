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
import javax.swing.filechooser.*;
public class picAchooseSound{
	JFileChooser soundChooser;
	File file;

	Clip clip;
	AudioInputStream audioIn;
	String fname;
	float b ;
	boolean check=false;

	boolean importCheck=false;
	FloatControl gainControl;
	
	
	
	public void chooseSound(){
		if(importCheck!=true){
			importCheck=true;
		soundChooser = new JFileChooser();
		soundChooser.showOpenDialog(null);
		file = soundChooser.getSelectedFile();
	
		}


	}
	
	public void playSound(){
		if(check!=true){
		try {
			check = true;
			// Open an audio input stream.

			 audioIn = AudioSystem.getAudioInputStream(file);
			// Get a sound clip resource.
			 clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
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
	}

	public void setVolume(){
		
	}
	
	
	public void stopMusic(){
		clip.stop();
		check = false;
		importCheck=false;
	}
	

	public void mute(){
		gainControl.setValue(gainControl.getMinimum());
	}

}
