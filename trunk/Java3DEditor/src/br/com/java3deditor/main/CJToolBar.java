package br.com.java3deditor.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Class that creates the tool bar.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class CJToolBar {
	
    public JToolBar toolBar = new JToolBar("Bar");
    
    JButton btCube;
    JButton btCone;
    JButton btCylinder;
    JButton btSphere;
    JButton btDelete;
    JButton btChangeColor;
    JButton btClone;
   
    JButton btResetView;

    public CJToolBar() {
    		
    	toolBar.setFloatable(false);
    	toolBar.setOrientation(JToolBar.VERTICAL);
		
        final ImageIcon iconCube = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/cube.png"));
        iconCube.setImage(iconCube.getImage().getScaledInstance(32, 32, 1));
        
        final ImageIcon iconCone = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/cone.png"));
        iconCone.setImage(iconCone.getImage().getScaledInstance(32, 32, 1));
        
        final ImageIcon iconCylinder = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/cylinder.png"));
        iconCylinder.setImage(iconCylinder.getImage().getScaledInstance(32, 32, 1));
        
        final ImageIcon iconSphere = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/sphere.png"));
        iconSphere.setImage(iconSphere.getImage().getScaledInstance(32, 32, 1));
   
        final ImageIcon iconDelete = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/delete.png"));
        iconDelete.setImage(iconDelete.getImage().getScaledInstance(32, 32, 1));
        
        final ImageIcon iconChangeColor = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/changecolor.png"));
        iconChangeColor.setImage(iconChangeColor.getImage().getScaledInstance(32, 32, 1));
        
        final ImageIcon iconClone = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/clone.png"));
        iconClone.setImage(iconClone.getImage().getScaledInstance(32, 32, 1));
        
        final ImageIcon iconResetView = new ImageIcon(getClass().getClassLoader().getResource("br/com/java3deditor/icons/resetview.png"));
        iconResetView.setImage(iconResetView.getImage().getScaledInstance(32, 32, 1));

        btCube = new JButton(iconCube);
        btCube.setToolTipText("Add Cube");
        btCube.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.addCube();
			}
		});
        
        btCone = new JButton(iconCone);
        btCone.setToolTipText("Add Cone");
        btCone.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.addCone();
			}
		});
    
        btCylinder = new JButton(iconCylinder);
        btCylinder.setToolTipText("Add Cylinder");
        btCylinder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.addCylinder();
			}
		});
    
        btSphere = new JButton(iconSphere);
        btSphere.setToolTipText("Add Sphere");
        btSphere.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.addSphere();
			}
		});
        
        btDelete = new JButton(iconDelete);
        btDelete.setToolTipText("Delete");
        btDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.deleteObject();
			}
		});
        
        btChangeColor = new JButton(iconChangeColor);
        btChangeColor.setToolTipText("Change Color");
        btChangeColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.changeColor();
			}
		});
        
        btClone = new JButton(iconClone);
        btClone.setToolTipText("Clone");
        btClone.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Main.cloneObject();
			}
		});
        
        btResetView = new JButton(iconResetView);
        btResetView.setToolTipText("Reset View");
        btResetView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				World3D.resetView();
			}
		});
        
        toolBar.add(btCube);
        toolBar.add(btCone);
        toolBar.add(btCylinder);
        toolBar.add(btSphere);
        toolBar.addSeparator();      
        toolBar.addSeparator();    
        toolBar.add(btDelete);
        toolBar.add(btChangeColor);
        toolBar.add(btClone);
        toolBar.addSeparator();
        toolBar.addSeparator();    
        toolBar.add(btResetView);

    }
    
    JToolBar getToolBar(){
		return toolBar;
    	
    }
    
}
