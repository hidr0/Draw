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

public class picAchooseSound {
	JFileChooser soundChooser;
	File file;

	Clip clip;
	AudioInputStream audioIn;
	String fname;
	float b;
	boolean check = false;

	boolean importCheck = false;
	FloatControl gainControl;

	public void chooseSound() {
		if (importCheck != true) {
			soundChooser = new JFileChooser();
			soundChooser.showOpenDialog(null);
			file = soundChooser.getSelectedFile();
			if (file != null) {
				importCheck = true;
				playSound();
			} else {
				JOptionPane.showMessageDialog(null,
						"You did not select a song",
						"Why you not selecting SONG?",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	public void playSound() {
		if (file == null) {
			JOptionPane.showMessageDialog(null,
					"There is no sound selected you must now select a soung",
					"Trying to play a 'null' sound heh?",
					JOptionPane.INFORMATION_MESSAGE);
			chooseSound();
		} else {
			if (check != true) {
				try {
					check = true;
					// Open an audio input stream.

					audioIn = AudioSystem.getAudioInputStream(file);
					// Get a sound clip resource.
					clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input
					// stream.
					clip.open(audioIn);
					gainControl = (FloatControl) clip
							.getControl(FloatControl.Type.MASTER_GAIN);

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

	}

	public void setVolume(double b) {
		
	}

	public void stopMusic() {
		if (file != null) {
			clip.stop();
			check = false;
			importCheck = false;
		} else {
			JOptionPane.showMessageDialog(null,
					"There is no sound selected you must now select a soung",
					"Trying to stop a 'null' sound heh?",
					JOptionPane.INFORMATION_MESSAGE);
			chooseSound();
		}
	}

	public void mute() {
		if (file != null) {
			gainControl.setValue(gainControl.getMinimum());
		} else {
			JOptionPane.showMessageDialog(null,
					"There is no sound selected you must now select a soung",
					"Trying to mute a 'null' sound heh?",
					JOptionPane.INFORMATION_MESSAGE);
			chooseSound();
		}
	}

}