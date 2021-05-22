/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */

package gui;

import gui.listener.EnigmaMenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;


public class EnigmaMenuBar {

	private JMenuBar menuBar;
	private EnigmaMenuListener emlistener;
	
	public EnigmaMenuBar(final JFrame frame) {
		
		// Create menu bar
		menuBar = new JMenuBar();
		
		// View
		JMenu display = new JMenu("Display");
		ButtonGroup displayGroup = new ButtonGroup();
		JRadioButtonMenuItem textBox = new JRadioButtonMenuItem("Text box");
		JRadioButtonMenuItem keyboard = new JRadioButtonMenuItem("Keyboard");
		textBox.setSelected(true);
		displayGroup.add(textBox);
		displayGroup.add(keyboard);
		display.add(textBox);
		display.add(keyboard);
		
		// Add Menu to MenuBar
		menuBar.add(display);

		// Display> keyboard textBox
		keyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emlistener.keyboardDisplay();
			}
		});
		
		textBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emlistener.textBoxDisplay();
			}
		});
	}
	
	/**
	 * Get Menu Bar
	 * @return Menu Bar
	 * */
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	/**
	 * Set Enigma menu listener
	 * @param emlistener
	 */
	public void setEmlistener(EnigmaMenuListener emlistener) {
		this.emlistener = emlistener;
	}
}
