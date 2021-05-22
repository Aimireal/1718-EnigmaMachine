/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import enigma.Enigma;
import gui.listener.EnigmaMenuListener;
import gui.listener.RotorListener;
import gui.listener.TypeListener;
import gui.panels.EncodePanel;
import gui.panels.PlugboardPanel;
import gui.panels.RotorsPanel;
import gui.panels.TypewriterPanel;

public class Machine extends JFrame {

	private EncodePanel epanel;
	private RotorsPanel rpanel;
	private PlugboardPanel pbpanel;
	private Enigma enigma;
	private TypewriterPanel twpanel;
	private EnigmaMenuBar menuBar;

	private final int TEXT_MODE = 1;
	private final int KB_MODE = 2;
	private int mode = TEXT_MODE;

	public Machine() {
		super("Cyber Security Assignment Enigma machine - u1652664 Dylan Hudson");

		// Create the enigma machine
		enigma = new Enigma(Enigma.I, Enigma.II, Enigma.III, Enigma.A);

		// Set frame layout
		setLayout(new BorderLayout());

		// Create Menu bar
		menuBar = new EnigmaMenuBar(this);

		// Set JMenu
		setJMenuBar(menuBar.getMenuBar());

		// Create panels
		epanel = new EncodePanel();
		rpanel = new RotorsPanel();
		pbpanel = new PlugboardPanel();
		twpanel = new TypewriterPanel();

		// Pass the enigma machine to the panel
		epanel.setEnigma(enigma);
		rpanel.setEnigma(enigma);
		twpanel.setEnigma(enigma);

		// Add panels
		add(epanel, BorderLayout.CENTER);
		add(rpanel, BorderLayout.NORTH);
		add(pbpanel, BorderLayout.SOUTH);

		// Add rotor listener
		rpanel.setRotorListener(new RotorListener() {
			public void configure(String[] leftRotor, String[] middleRotor, String[] rightRotor, char leftStart,
					char middleStart, char rightStart, char leftRing, char middleRing, char rightRing,
					String reflector) {

				// Set type
				enigma.getLeftRotor().setRotor(leftRotor);
				enigma.getMiddleRotor().setRotor(middleRotor);
				enigma.getRightRotor().setRotor(rightRotor);

				// Set rotor head
				enigma.getLeftRotor().setRotorHead(leftStart);
				enigma.getMiddleRotor().setRotorHead(middleStart);
				enigma.getRightRotor().setRotorHead(rightStart);

				// Set rotor ring
				enigma.getLeftRotor().setRingHead(leftRing);
				enigma.getMiddleRotor().setRingHead(middleRing);
				enigma.getRightRotor().setRingHead(rightRing);

				// Set reflector
				enigma.getReflector().setReflector(reflector);

				// Reset & Insert plugs
				enigma.resetPlugboard();
				Scanner scan = new Scanner(pbpanel.getPlugboard());
				while (scan.hasNext()) {
					String wire = scan.next();
					if (wire.length() == 2) {
						char from = wire.charAt(0);
						char to = wire.charAt(1);
						if (!enigma.isPlugged(from) && !enigma.isPlugged(to) && from != to)
							enigma.insertPlugboardWire(from, to);
					}
				}
				scan.close();

				// Refresh encode panel
				if (mode == TEXT_MODE)
					epanel.refresh();
			}
		});

		// Add type listener
		epanel.setTypeListener(new TypeListener() {
			public void typeAction() {
				rpanel.setStates(enigma.getLeftRotor().getRotorHead(), enigma.getMiddleRotor().getRotorHead(),
						enigma.getRightRotor().getRotorHead());
			}
		});

		twpanel.setTypeListener(new TypeListener() {
			public void typeAction() {
				rpanel.setStates(enigma.getLeftRotor().getRotorHead(), enigma.getMiddleRotor().getRotorHead(),
						enigma.getRightRotor().getRotorHead());
			}
		});

		// Set default text
		epanel.setDefaultText("INPUT YOUR TEXT HERE");

		menuBar.setEmlistener(new EnigmaMenuListener() {
			public void textBoxDisplay() {
				mode = TEXT_MODE;
				epanel.setVisible(true);
				twpanel.setVisible(false);
				add(epanel, BorderLayout.CENTER);
				revalidate();

				// Force state change
				epanel.setDefaultText("A");
				epanel.setDefaultText("");

				// Light theme
				pbpanel.lightTheme();
				rpanel.lightTheme();
			}

			public void keyboardDisplay() {
				mode = KB_MODE;
				twpanel.setVisible(true);
				epanel.setVisible(false);
				add(twpanel, BorderLayout.CENTER);
				revalidate();
				twpanel.clear();

			}
		});

		// Frame preference
		setVisible(true);
		Dimension dim = new Dimension(1150, 700);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
	}
}
