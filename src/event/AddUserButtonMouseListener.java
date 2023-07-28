package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

//public class AddUserButtonMouseListener implements MouseListener {
//	
//	@Override
//	public void mouseClicked(MouseEvent e) {
//	}
//	
//	@Override
//	public void mousePressed(MouseEvent e) {
//	}
//	
//	@Override
//	public void mouseReleased(MouseEvent e) {
//	}
//	
//	@Override
//	public void mouseEntered(MouseEvent e) {
//	}
//	
//	@Override
//	public void mouseExited(MouseEvent e) {
//	}
//}

public class AddUserButtonMouseListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDiaglog(null,"test1","test2",JOptionPane)
	}
}

