/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */

package gui.panels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PlugboardPanel extends JPanel{
	
	private JTextField pbField;
	private TitledBorder innerBorder;
	
	public PlugboardPanel() {
		
		// Create component
		pbField = new JTextField(95);
		pbField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.gray), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		// Adding border
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		innerBorder = BorderFactory.createTitledBorder("Plugboard (e.g. AB CD) - Press \"SAVE\"");
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		// Add component
		add(pbField);
		
		// Filter input to upper case only
		( (AbstractDocument) pbField.getDocument() ).setDocumentFilter(new DocumentFilter(){
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				for (int i = 0; i < text.length(); i++) 
					if( (text.charAt(i) < 'a' || text.charAt(i) > 'z') && (text.charAt(i) < 'A' || text.charAt(i) > 'Z') && text.charAt(0) != ' ')
						return;
				super.replace(fb, offset, length, text.toUpperCase(), attrs);
			}
		});
	}
	
	/**
	 * Get plugboard data
	 * @return plugboard data
	 */
	public String getPlugboard(){
		return pbField.getText();
	}
	
	/**
	 * Dark theme
	 */
	public void darkTheme(){
		setBackground(Color.WHITE);
		innerBorder.setTitleColor(Color.WHITE);
	}
	
	/**
	 * Light theme
	 */
	public void lightTheme(){
		setBackground(null);
		innerBorder.setTitleColor(null);
	}
}
