
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClipTest extends JFrame {
	int Clicks = 0;
	private static final long serialVersionUID = 1L;

	// Constructor
	public SoundClipTest() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Test Sound Clip");
		this.setSize(300, 200);
		//
		JButton mute = new JButton("mute");

		try {
			// Open an audio input stream.

			URL url = this.getClass().getClassLoader()
					.getResource("BEAT_LooP 1.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			final Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);

			mute.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					Clicks++;

					

				}
			});

			//clip.start();
			//clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		this.add(mute);
		this.setVisible(true);
	}
	public void mute() {
		Clicks++;
	}

}