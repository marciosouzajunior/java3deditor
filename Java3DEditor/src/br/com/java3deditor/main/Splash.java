package br.com.java3deditor.main;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * Show a splash screen when starting the software.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0 
 */
public class Splash {

	public Splash() {

		final JWindow splash = new JWindow();

		Toolkit tool = Toolkit.getDefaultToolkit();
		int width = (int) (tool.getScreenSize().getWidth() / 2 - 331);
		int height = (int) (tool.getScreenSize().getHeight() / 2 - 115);

		splash.setBounds(width, height, 663, 230);
		splash.setLayout(null);

		JLabel lbLogo = new JLabel(new ImageIcon(this.getClass().getResource(
				"../icons/logo.png")));
		lbLogo.setBounds(0, 0, 663, 230);
		splash.add(lbLogo);

		splash.setVisible(true);
		splash.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				splash.dispose();
			}
		});
	}
	
}
