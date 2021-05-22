/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */

package gui.listener;

//Rotor Listener
public interface RotorListener {
	public void configure(
			String[] leftRotor, String[] middleRotor, String[] rightRotor, 
			char leftStart, char centerStart, char rightStart, 
			char leftRing, char centerRing, char rightRing, 
			String reflector);
}
