/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */

package gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import enigma.Enigma;
import gui.listener.RotorListener;

public class RotorsPanel extends JPanel{
	
	private GridBagConstraints gc;
	private JComboBox reflectorBox;
	private JComboBox leftRotorBox;
	private JComboBox middleRotorBox;
	private JComboBox rightRotorBox;
	
	private JComboBox leftRotorStartBox;
	private JComboBox middleRotorStartBox;
	private JComboBox rightRotorStartBox;
	
	private JComboBox leftRotorRingBox;
	private JComboBox middleRotorRingBox;
	private JComboBox rightRotorRingBox;
	
	private JButton applyBtn;
	
	private JTextField leftState;
	private JTextField middleState;
	private JTextField rightState;
	
	private JPanel reflectorPanel;
	private JPanel leftRotorPanel;
	private JPanel middleRotorPanel;
	private JPanel rightRotorPanel;
	private JPanel stateRotorPanel;
	
	private String[][] ENIGMA_ROTORS = {Enigma.I, Enigma.II, Enigma.III, Enigma.IV, Enigma.V}; 
	private String[] ENIGMA_REFLECTORS = {Enigma.A, Enigma.B, Enigma.C}; 
	
	private RotorListener rotorListener;
	private Enigma enigma;
	
	private JLabel rLabels[] = {
			new JLabel("Type: "),
			new JLabel("Type: "),
			new JLabel("Start: "),
			new JLabel("Ring: "),
			new JLabel("Type: "),
			new JLabel("Start: "),
			new JLabel("Ring: "),
			new JLabel("Type: "),
			new JLabel("Start: "),
			new JLabel("Ring: "),
			new JLabel("L"),
			new JLabel("M"),
			new JLabel("R")
	};
	
	private TitledBorder titleBorders[] = {
			BorderFactory.createTitledBorder("Reflector"),
			BorderFactory.createTitledBorder("Left Rotor"),
			BorderFactory.createTitledBorder("Middle Rotor"),
			BorderFactory.createTitledBorder("Right Rotor"),
			BorderFactory.createTitledBorder("State")
	};
	
	public RotorsPanel() {
		
		// Grid layout
		setLayout(new GridBagLayout());
		
		// Create panels
		reflectorPanel = new JPanel();
		reflectorPanel.setLayout(new GridBagLayout());
		reflectorPanel.setPreferredSize(new Dimension(100,100));
		
		leftRotorPanel = new JPanel();
		leftRotorPanel.setLayout(new GridBagLayout());
		leftRotorPanel.setPreferredSize(new Dimension(220,100));
		
		middleRotorPanel = new JPanel();
		middleRotorPanel.setLayout(new GridBagLayout());
		middleRotorPanel.setPreferredSize(new Dimension(220,100));
		
		rightRotorPanel = new JPanel();
		rightRotorPanel.setLayout(new GridBagLayout());
		rightRotorPanel.setPreferredSize(new Dimension(220,100));
		
		stateRotorPanel = new JPanel();
		stateRotorPanel.setLayout(new GridBagLayout());
		stateRotorPanel.setPreferredSize(new Dimension(130,100));
		
		// Common components
		String[] rotorBoxOption = {"I" ,"II", "III", "IV", "V"};
		String[] rotorBoxLetterOption = new String[26];
		for(int i=0; i < 26; i++) rotorBoxLetterOption[i] = (char) ('A' + i)+"";
		
		// Create labels
		leftState = new JTextField("A", 1);
		leftState.setEditable(false);
		middleState = new JTextField("A", 1);
		middleState.setEditable(false);
		rightState = new JTextField("A", 1);
		rightState.setEditable(false);
		
		// Create reflector
		String[] reflectorBoxOption = {"A" ,"B", "C"};
		reflectorBox = new JComboBox(reflectorBoxOption);
		reflectorBox.setSelectedIndex(0);
		
		// Create left rotor
		leftRotorBox = new JComboBox(rotorBoxOption);
		leftRotorBox.setSelectedIndex(0);
		
		// Create middle rotor
		middleRotorBox = new JComboBox(rotorBoxOption);
		middleRotorBox.setSelectedIndex(1);
		
		// Create right rotor
		rightRotorBox = new JComboBox(rotorBoxOption);
		rightRotorBox.setSelectedIndex(2);
		
		// Create left rotor start option
		leftRotorStartBox = new JComboBox(rotorBoxLetterOption);
		leftRotorStartBox.setSelectedIndex(0);
	
		// Create middle rotor start option
		middleRotorStartBox = new JComboBox(rotorBoxLetterOption);
		middleRotorStartBox.setSelectedIndex(0);
		
		// Create right rotor start option
		rightRotorStartBox = new JComboBox(rotorBoxLetterOption);
		rightRotorStartBox.setSelectedIndex(0);

		// Create left rotor ring option
		leftRotorRingBox = new JComboBox(rotorBoxLetterOption);
		leftRotorRingBox.setSelectedIndex(0);
	
		// Create middle rotor ring option
		middleRotorRingBox = new JComboBox(rotorBoxLetterOption);
		middleRotorRingBox.setSelectedIndex(0);
		
		// Create right rotor ring option
		rightRotorRingBox = new JComboBox(rotorBoxLetterOption);
		rightRotorRingBox.setSelectedIndex(0);
		
		// Create save button
		applyBtn = new JButton("SAVE", new ImageIcon(getClass().getResource("/gui/images/save.png")));
		applyBtn.setBorderPainted(false); 
        applyBtn.setContentAreaFilled(false); 
        applyBtn.setFocusPainted(false); 
        applyBtn.setOpaque(false);
		applyBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		applyBtn.setForeground(Color.white);
		applyBtn.setPreferredSize(new Dimension(86, 50));
		applyBtn.setPressedIcon(new ImageIcon(getClass().getResource("/gui/images/save_clicked.png")));
		
		// Adding borders to panels
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		reflectorPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, titleBorders[0]));
		leftRotorPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, titleBorders[1]));
		middleRotorPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, titleBorders[2]));
		rightRotorPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, titleBorders[3]));
		stateRotorPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, titleBorders[4]));
		
		// Default grid configuration
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 10, 0, 10);
		gc.anchor = GridBagConstraints.NORTHWEST;
		int y = 0;
		gc.weightx = 1;
		gc.weighty = 0.15;
		
		// Add ReflectorBox
		gridPosition(0, y);
		reflectorPanel.add(rLabels[0],gc);
		gridPosition(0, y+1);
		reflectorPanel.add(reflectorBox, gc);
		
		// Add LeftRotorBox
		gridPosition(0, y);
		leftRotorPanel.add(rLabels[1],gc);
		gridPosition(0, y+1);
		leftRotorPanel.add(leftRotorBox, gc);
		gridPosition(1, y);
		leftRotorPanel.add(rLabels[2],gc);
		gridPosition(1, y+1);
		leftRotorPanel.add(leftRotorStartBox, gc);
		gridPosition(2, y);
		leftRotorPanel.add(rLabels[3],gc);
		gridPosition(2, y+1);
		leftRotorPanel.add(leftRotorRingBox, gc);
		
		// Add MiddleRotorBox
		gridPosition(0, y);
		middleRotorPanel.add(rLabels[4],gc);
		gridPosition(0, y+1);
		middleRotorPanel.add(middleRotorBox, gc);
		gridPosition(1, y);
		middleRotorPanel.add(rLabels[5],gc);
		gridPosition(1, y+1);
		middleRotorPanel.add(middleRotorStartBox, gc);
		gridPosition(2, y);
		middleRotorPanel.add(rLabels[6],gc);
		gridPosition(2, y+1);
		middleRotorPanel.add(middleRotorRingBox, gc);
		
		// Add MiddleRotorBox
		gridPosition(0, y);
		rightRotorPanel.add(rLabels[7],gc);
		gridPosition(0, y+1);
		rightRotorPanel.add(rightRotorBox, gc);
		gridPosition(1, y);
		rightRotorPanel.add(rLabels[8],gc);
		gridPosition(1, y+1);
		rightRotorPanel.add(rightRotorStartBox, gc);
		gridPosition(2, y);
		rightRotorPanel.add(rLabels[9],gc);
		gridPosition(2, y+1);
		rightRotorPanel.add(rightRotorRingBox, gc);
		
		// Add MiddleRotorBox
		gridPosition(0, y);
		stateRotorPanel.add(rLabels[10],gc);
		gridPosition(0, y+1);
		stateRotorPanel.add(leftState, gc);
		gridPosition(1, y);
		stateRotorPanel.add(rLabels[11],gc);
		gridPosition(1, y+1);
		stateRotorPanel.add(middleState, gc);
		gridPosition(2, y);
		stateRotorPanel.add(rLabels[12],gc);
		gridPosition(2, y+1);
		stateRotorPanel.add(rightState, gc);
		
		// Add panels
		add(reflectorPanel);
		add(leftRotorPanel);
		add(middleRotorPanel);
		add(rightRotorPanel);
		add(stateRotorPanel);
		add(applyBtn);
		
		// Add button listener
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotorListener.configure(
						ENIGMA_ROTORS[leftRotorBox.getSelectedIndex()], ENIGMA_ROTORS[middleRotorBox.getSelectedIndex()], ENIGMA_ROTORS[rightRotorBox.getSelectedIndex()],
						(char) ('A' + leftRotorStartBox.getSelectedIndex()), (char) ('A' + middleRotorStartBox.getSelectedIndex()), (char) ('A' + rightRotorStartBox.getSelectedIndex()), 
						(char) ('A' + leftRotorRingBox.getSelectedIndex()), (char) ('A' + middleRotorRingBox.getSelectedIndex()), (char) ('A' + rightRotorRingBox.getSelectedIndex()), 
						ENIGMA_REFLECTORS[reflectorBox.getSelectedIndex()]);
				
				// Update states
				leftState.setText( enigma.getLeftRotor().getRotorHead() + "");
				middleState.setText( enigma.getMiddleRotor().getRotorHead() + "");
				rightState.setText( enigma.getRightRotor().getRotorHead() + "");
			}
		});
		
		// Remove panels background
		reflectorPanel.setOpaque(false);
		leftRotorPanel.setOpaque(false);
		middleRotorPanel.setOpaque(false);
		rightRotorPanel.setOpaque(false);
		stateRotorPanel.setOpaque(false);
		
		// Set size
		setPreferredSize(new Dimension(100,100));
	}
	
	/**
	 * Set rotor listener
	 * @param rotorListener
	 */
	public void setRotorListener(RotorListener rotorListener){
		this.rotorListener = rotorListener; 
	}
	
	/**
	 * Set states
	 * @param l
	 * @param c
	 * @param r
	 */
	public void setStates(char l, char m, char r){
		leftState.setText(l+"");
		middleState.setText(m+"");
		rightState.setText(r+"");
	}
	
	/**
	 * Set enigma
	 * @param enigma
	 */
	public void setEnigma(Enigma enigma){
		this.enigma = enigma;
	}
	
	/**
	* Set grid position
	* @param x X-Position
	* @param y Y-Position
	* */
	private void gridPosition(int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
	}
	
	/**
	 * Dark theme
	 */
	public void darkTheme(){
		setBackground(Color.DARK_GRAY);
		for(int i=0; i<rLabels.length; i++)
			rLabels[i].setForeground(Color.WHITE);
		for(int i=0; i<titleBorders.length; i++)
			titleBorders[i].setTitleColor(Color.WHITE);
	}
	
	/**
	 * Light theme
	 */
	public void lightTheme(){
		setBackground(null);
		for(int i=0; i<rLabels.length; i++)
			rLabels[i].setForeground(null);
		for(int i=0; i<titleBorders.length; i++)
			titleBorders[i].setTitleColor(null);
	}
}