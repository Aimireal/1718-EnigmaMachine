/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */
package gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BulbPanel extends JPanel{
	
	private JLabel keyboardBulbs[] = new JLabel[26];
	private GridBagConstraints gc;
	private JPanel boardPanel, textPanel;
	private JTextField outputField;
	
	public BulbPanel() {
		
		// Create panels
		boardPanel = new JPanel();
		textPanel = new JPanel();
			
		// Set Layout
		setLayout(new BorderLayout());
		boardPanel.setLayout(new GridBagLayout());
		
		// Create output field
		outputField = new JTextField(95);
		outputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		outputField.setEditable(false);
		
		// Adding border
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Add empty border
		textPanel.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
		
		// Create bulbs
		for(int i=0; i<26; i++){
			keyboardBulbs[i] = new JLabel("<html><b>"+(char)('A'+i)+"</b></html>", 
					new ImageIcon(getClass().getResource("/gui/images/button_bulb.png")),SwingConstants.CENTER);
	        keyboardBulbs[i].setOpaque(false);
			keyboardBulbs[i].setHorizontalTextPosition(SwingConstants.CENTER);
			keyboardBulbs[i].setForeground(Color.WHITE);
			keyboardBulbs[i].setPreferredSize(new Dimension(50, 50));
			keyboardBulbs[i].setFont(new Font(getFont().getFontName(), Font.PLAIN, getFont().getSize()+2));
		}
		
		// Default grid configuration
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.weightx = 1;
		gc.weighty = 0.15;
		
		// Add Bulb Row 1
		String row1 = "QWERTYUIO";
		for(int i=0; i<9; i++){
			gc.gridx = i*2;
			gc.gridy = 0;
			boardPanel.add(keyboardBulbs[row1.charAt(i)-'A'], gc);
		}
		
		// Add Bulb Row 2
		String row2 = "PASDFGHJ";
		for(int i=0; i<8; i++){
			gc.gridx = i*2+1;
			gc.gridy = 1;
			boardPanel.add(keyboardBulbs[row2.charAt(i)-'A'], gc);
		}
		
		// Add Bulb Row 3
		String row3 = "KLZXCVBNM";
		for(int i=0; i<9; i++){
			gc.gridx = i*2;
			gc.gridy = 2;
			boardPanel.add(keyboardBulbs[row3.charAt(i)-'A'], gc);
		}
		
		// Add output field
		textPanel.add(outputField);
		
		// Change background to dark
		setBackground(Color.WHITE);
		boardPanel.setOpaque(false);
		textPanel.setOpaque(false);
		outputField.setBackground(null);
		outputField.setForeground(Color.DARK_GRAY);
		outputField.setOpaque(false);
		
		// Add panels
		add(boardPanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Bulb on
	 * @param c
	 */
	public void bulbOn(int c){
		keyboardBulbs[c].setForeground(Color.WHITE);
	}
	
	/**
	 * Bulb off
	 * @param c
	 */
	public void bulbOff(int c){
		keyboardBulbs[c].setForeground(Color.WHITE);
	}
	
	/**
	 * Get output field
	 * @return output field
	 */
	public JTextField getOutputField(){
		return outputField;
	}
}
