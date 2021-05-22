/**
* Enigma Machine
* Dylan Hudson - U1652664
* University of Huddersfield
* */

//Keyboard Listener
package gui.listener;

public interface KeyboardListener {
	public int pressAction(char c);
	public void releaseAction(int c);
}
