package com.towerdefense.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MouseHandler implements MouseListener, MouseMotionListener {
    public static ArrayList<MouseHandler> eventObserver = new ArrayList();

	public static void addEventObserver(MouseHandler m){
        eventObserver.add(m);
    }

	public static void fireMouseClicked(MouseEvent e){
        try {
            for (MouseHandler m : eventObserver) {
                m.mouseClicked(e);
            }
        }catch(ConcurrentModificationException m){}
    }

    @Override
	public void mouseClicked(MouseEvent e) {
		// Frame.statusBarLabel.setText(String.format("Clic à %d,%d", e.getX(),
		// e.getY()));

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Frame.statusBarLabel.setText("Drag and drop en cours!");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Frame.statusBarLabel.setText("Le curseur est dans le panel");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Frame.statusBarLabel.setText("Le curseur a quitté le panel");
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Frame.statusBarLabel.setText("Bouton pressé!");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Frame.statusBarLabel.setText("Bouton relâché!");

	}
	//

}
