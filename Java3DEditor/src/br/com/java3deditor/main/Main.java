package br.com.java3deditor.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;

import br.com.java3deditor.behaviors.SelectObjectBehavior;
import br.com.java3deditor.primitives.*;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.picking.PickResult;

/**
 * Create the software interface with menu bar, window with World3D and action
 * listeners to call methods. Also has methods to clone, delete or change object
 * color.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	// Take a toolkit to set the window size and position of InternalFrames
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static int width = (int) tool.getScreenSize().getWidth();
	private static int height = (int) tool.getScreenSize().getHeight();

	// Create a desktop to add the InternalFrames
	private final static JDesktopPane desktop = new JDesktopPane();

	// Class that creates the World3D and allows the addition objects to it
	private static World3D scene = new World3D();

	// Create a ToolBar
	private CJToolBar toolBar = new CJToolBar();

	// Control the speed when you move objects
	private static float motionFactor = 0.01f;

	Main() {

		super("Java3DEditor");
		super.setBounds(0, 0, width, height);

		// Configure the desktop
		desktop.setBounds(0, 0, width, height);
		desktop.setLayout(new BorderLayout());
		desktop.setBackground(new Color(0, 0, 0));

		// Centralize the World3D on the desktop
		int x = scene.getWidth() / 2;
		int y = scene.getHeight() / 2;
		scene.setLocation(width / 2 - x, height / 2 - y - 50);

		// Add the World3D and show
		desktop.add(scene, BorderLayout.CENTER);
		scene.show();

		// Add the toolBar
		desktop.add(toolBar.getToolBar(), BorderLayout.WEST);

		// Add the desktop
		super.add(desktop);

		super.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		super.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {

				int r = JOptionPane.showConfirmDialog(null,
						"Exit the Java3DEditor?", "Confirm Exit",
						JOptionPane.YES_NO_OPTION);

				if (r == 0)
					System.exit(0);
				else
					repaint();
			}
		});

		super.setExtendedState(JFrame.MAXIMIZED_BOTH);

		super.setJMenuBar(createMenu());

	}

	/**
	 * returns a menu bar containing the items and their actions. Some logic is
	 * already implemented in some stocks, others just call the responsible
	 * methods.
	 * 
	 * @author Márcio de Souza Júnior
	 * @version 1.0.0
	 * @return JMenuBar Menu bar containing the items
	 */

	private JMenuBar createMenu() {

		JMenuBar menuBar = new JMenuBar();

		// Main menu
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");
		JMenu objectsMenu = new JMenu("Objects");

		// Sub menu - Objects
		JMenu addObjetos = new JMenu("Add");

		// Menu items - Objects > Add
		JMenuItem itemBox = new JMenuItem("Cube");
		JMenuItem itemCone = new JMenuItem("Cone");
		JMenuItem itemCylinder = new JMenuItem("Cilinder");
		JMenuItem itemSphere = new JMenuItem("Sphere");

		// Menu items - Edit
		JMenuItem itemDelete = new JMenuItem("Delete");
		JMenuItem itemChangeColor = new JMenuItem("Change Color");
		itemChangeColor.setEnabled(true);
		JMenuItem itemTexture = new JMenuItem("Add Texture");
		itemTexture.setEnabled(false);
		JMenuItem itemClone = new JMenuItem("Clone");
		JMenuItem itemMotionFactor = new JMenuItem("Motion Factor");

		// Menu items - View
		JMenuItem itemTopView = new JMenuItem("Top");
		itemTopView.setEnabled(false);
		JMenuItem itemLeftView = new JMenuItem("Left");
		itemLeftView.setEnabled(false);
		JMenuItem itemRightView = new JMenuItem("Right");
		itemRightView.setEnabled(false);
		JMenuItem itemBottomView = new JMenuItem("Bottom");
		itemBottomView.setEnabled(false);
		JMenuItem itemResetView = new JMenuItem("Reset View");

		// Menu items - File
		JMenuItem itemNew = new JMenuItem("New");
		itemNew.setEnabled(false);
		JMenuItem itemOpen = new JMenuItem("Open");
		itemOpen.setEnabled(false);
		JMenuItem itemSave = new JMenuItem("Save");
		itemSave.setEnabled(false);
		JMenuItem itemGenerateScript = new JMenuItem("Generate Script");
		JMenuItem itemExit = new JMenuItem("Exit");

		// Add items - File
		fileMenu.add(itemNew);
		fileMenu.add(itemOpen);
		fileMenu.add(itemSave);
		fileMenu.addSeparator();
		fileMenu.add(itemGenerateScript);
		fileMenu.addSeparator();
		fileMenu.add(itemExit);

		// Add items - Objects > Add
		addObjetos.add(itemBox);
		addObjetos.add(itemCone);
		addObjetos.add(itemCylinder);
		addObjetos.add(itemSphere);

		// Add sub menu - Objects
		objectsMenu.add(addObjetos);

		// Add items - Edit
		editMenu.add(itemDelete);
		editMenu.add(itemChangeColor);
		editMenu.add(itemTexture);
		editMenu.add(itemClone);
		editMenu.addSeparator();
		editMenu.add(itemMotionFactor);

		// Add items - View
		viewMenu.add(itemTopView);
		viewMenu.add(itemLeftView);
		viewMenu.add(itemRightView);
		viewMenu.add(itemBottomView);
		viewMenu.addSeparator();
		viewMenu.add(itemResetView);

		// Add items to menu bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(objectsMenu);

		//
		// Menu actions
		//

		/*
		// Save the World3D in a file - To be implemented
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Save();
			}
		});

		// Open a saved World3D - To be implemented
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Open();
			}
		});
		 */
		
		itemGenerateScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				World3D.clearSelections();

				// Clear the list
				World3D.setObjects(new ArrayList<Object>());

				// Populate the list with actual objects
				Enumeration<?> e1 = World3D.getSceneRoot().getAllChildren();
				World3D.traverse(e1);

				GenerateScript script = new GenerateScript();

				// Create the file and generates the initial code
				script.generateInitialCode();

				if (!script.getClassName().equals("")) {
					// Generates all the code of the World3D
					script.generateCode(World3D.getObjects());
				} else {
					JOptionPane.showMessageDialog(null, "Cancelado by user.",
							"Canceled", JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int r = JOptionPane.showConfirmDialog(null,
						"Exit the Java3DEditor?", "Confirm Exit",
						JOptionPane.YES_NO_OPTION);

				if (r == 0)
					System.exit(0);
				else
					repaint();
			}
		});

		itemResetView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				World3D.resetView();
			}
		});

		/*
		 * Views - To be implemented
		 * 
		 * // Show top view itemTopView.addActionListener(new ActionListener() {
		 * public void actionPerformed(ActionEvent arg0) {
		 * 
		 * } });
		 * 
		 * // Show bottom view itemBottomView.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
		 * 
		 * } });
		 * 
		 * // Show left view itemLeftView.addActionListener(new ActionListener()
		 * { public void actionPerformed(ActionEvent arg0) {
		 * 
		 * } });
		 * 
		 * // Show right view itemRightView.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
		 * 
		 * } });
		 */

		itemChangeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeColor();
			}
		});

		/*
		 * // Add texture - To be implemented itemTexture.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
		 * 
		 * } });
		 */

		itemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteObject();
			}
		});

		itemClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cloneObject();
			}
		});

		itemMotionFactor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					motionFactor = Float.parseFloat(JOptionPane
							.showInputDialog(null, "Enter the motion factor:",
									"0.01"));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getClass() + ": "
							+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		itemBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCube();
			}
		});

		itemSphere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addSphere();
			}
		});

		itemCylinder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCylinder();
			}
		});

		itemCone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCone();
			}
		});

		return menuBar;

	}

	/**
	 * Method used to change the color of an object. First the color is
	 * selected, then a check is made in the object list. So when the selected
	 * object is found its new color is set.
	 */
	static void changeColor() {

		if (World3D.getPickcanvas().pickClosest() != null) {

			Color newColor = JColorChooser.showDialog(Main.desktop,
					"Change Color", Main.desktop.getBackground());

			if (newColor != null) {

				try {

					Color3f color = new Color3f(newColor);

					// Update the list of objects
					Enumeration<?> e1 = World3D.getSceneRoot().getAllChildren();
					World3D.traverse(e1);

					for (int x = 1; x < World3D.getObjects().size(); x++) {

						if (World3D.getObjects().get(x) instanceof SelectObjectBehavior) {

							((SelectObjectBehavior) World3D.getObjects().get(x))
									.setNewColor(color);

						}
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getClass() + ": "
							+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "No color selected.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No object selected.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method used to delete an object. First is checked what object will be
	 * deleted from the list, then picks up the parent of parent of object
	 * (BranchGroup) and removes from the World3D.
	 */
	static void deleteObject() {

		if (World3D.getPickcanvas().pickClosest() != null) {

			// Update the list of getObjects()
			Enumeration<?> e1 = World3D.getSceneRoot().getAllChildren();
			World3D.traverse(e1);

			for (int x = 1; x < World3D.getObjects().size(); x++) {

				if (World3D.getObjects().get(x) instanceof SelectObjectBehavior) {

					((BranchGroup) ((TransformGroup) ((Primitive) ((SelectObjectBehavior) (World3D
							.getObjects().get(x))).getObject().getNode(
							PickResult.PRIMITIVE)).getParent()).getParent())
							.detach();

				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "No object selected.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method used to clone an object. First is checked what kind of object is
	 * being cloned. Then capture their properties and and call the method to
	 * add the object in World3D.
	 */
	static void cloneObject() {

		if (World3D.getPickcanvas().pickClosest() != null) {

			// Update the list of getObjects()
			Enumeration<?> e1 = World3D.getSceneRoot().getAllChildren();
			World3D.traverse(e1);

			// Clear the selection because the object could get a lighter color
			// when flashing
			World3D.clearSelections();

			PickResult result = World3D.getPickcanvas().pickClosest();
			Primitive primitive = (Primitive) result
					.getNode(PickResult.PRIMITIVE);

			if (primitive instanceof Box) {

				// Appearance
				Color3f color = new Color3f();
				primitive.getAppearance().getColoringAttributes().getColor(
						color);

				// TransformGroup
				TransformGroup trg = new TransformGroup();
				trg = (TransformGroup) primitive.getParent();

				// Transforms
				Transform3D trf = new Transform3D();
				trg.getTransform(trf);

				// Scale
				Vector3d scale = new Vector3d();
				trf.getScale(scale);
				Transform3D tEscala = new Transform3D();
				tEscala.setScale(scale);

				// Rotation
				Matrix3d rotation = new Matrix3d();
				trf.getRotationScale(rotation);
				Transform3D tRotacao = new Transform3D();
				tRotacao.setRotation(rotation);

				// Temporary TransformGroup
				TransformGroup trgTemp = new TransformGroup();
				trgTemp.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
				trgTemp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				trgTemp.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

				// Apply transformations
				trgTemp.setTransform(trf);

				// Add the object
				scene.addBox(((VBox) primitive).getXdimension(),
						((VBox) primitive).getYdimension(), ((VBox) primitive)
								.getZdimension(), color, ((VBox) primitive)
								.getName(), trgTemp);

			} else if (primitive instanceof Sphere) {

				// Appearance
				Color3f color = new Color3f();
				primitive.getAppearance().getColoringAttributes().getColor(
						color);

				// TransformGroup
				TransformGroup trg = new TransformGroup();
				trg = (TransformGroup) primitive.getParent();

				// Transforms
				Transform3D trf = new Transform3D();
				trg.getTransform(trf);

				// Scale
				Vector3d scale = new Vector3d();
				trf.getScale(scale);
				Transform3D tEscala = new Transform3D();
				tEscala.setScale(scale);

				// Rotation
				Matrix3d rotation = new Matrix3d();
				trf.getRotationScale(rotation);
				Transform3D tRotacao = new Transform3D();
				tRotacao.setRotation(rotation);

				// Temporary TransformGroup
				TransformGroup trgTemp = new TransformGroup();
				trgTemp.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
				trgTemp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				trgTemp.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

				// Apply transformations
				trgTemp.setTransform(trf);

				scene.addSphere(((VSphere) primitive).getRadius(), color,
						((VSphere) primitive).getName(), trgTemp);

			} else if (primitive instanceof Cone) {

				// Appearance
				Color3f color = new Color3f();
				primitive.getAppearance().getColoringAttributes().getColor(
						color);

				// TransformGroup
				TransformGroup trg = new TransformGroup();
				trg = (TransformGroup) primitive.getParent();

				// Transformations
				Transform3D trf = new Transform3D();
				trg.getTransform(trf);

				// Scale
				Vector3d scale = new Vector3d();
				trf.getScale(scale);
				Transform3D tEscala = new Transform3D();
				tEscala.setScale(scale);

				// Rotation
				Matrix3d rotation = new Matrix3d();
				trf.getRotationScale(rotation);
				Transform3D tRotacao = new Transform3D();
				tRotacao.setRotation(rotation);

				// Temporary TransformGroup
				TransformGroup trgTemp = new TransformGroup();
				trgTemp.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
				trgTemp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				trgTemp.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

				// Apply transformations
				trgTemp.setTransform(trf);

				scene.addCone(((VCone) primitive).getRadius(),
						((VCone) primitive).getHeight(), color,
						((VCone) primitive).getName(), trgTemp);

			} else if (primitive instanceof Cylinder) {

				// Appearance
				Color3f color = new Color3f();
				primitive.getAppearance().getColoringAttributes().getColor(
						color);

				// TransformGroup
				TransformGroup trg = new TransformGroup();
				trg = (TransformGroup) primitive.getParent();

				// Transformations
				Transform3D trf = new Transform3D();
				trg.getTransform(trf);

				// Scale
				Vector3d scale = new Vector3d();
				trf.getScale(scale);
				Transform3D tEscala = new Transform3D();
				tEscala.setScale(scale);

				// Rotation
				Matrix3d rotation = new Matrix3d();
				trf.getRotationScale(rotation);
				Transform3D tRotacao = new Transform3D();
				tRotacao.setRotation(rotation);

				// TransformGroup
				TransformGroup trgTemp = new TransformGroup();
				trgTemp.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
				trgTemp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				trgTemp.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

				// Apply transformations
				trgTemp.setTransform(trf);

				scene.addCylinder(((VCylinder) primitive).getRadius(),
						((VCylinder) primitive).getHeight(), color,
						((VCylinder) primitive).getName(), trgTemp);
			}

		} else {
			JOptionPane.showMessageDialog(null, "No object selected.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Add a cube in the World3D. This method asks the properties and calls
	 * another method to add the object.
	 */
	static void addCube() {

		Color newColor = JColorChooser.showDialog(desktop, "Select Color",
				desktop.getBackground());

		if (newColor != null) {

			Color3f color = new Color3f(0.0f, 0.0f, 0.0f);
			color.set(newColor);

			// Attributes
			ColoringAttributes attributes = new ColoringAttributes();
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
			attributes.setColor(color);

			// Appearance
			Appearance appearance = new Appearance();
			appearance
					.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
			appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
			appearance.setColoringAttributes(attributes);

			try {

				String name = JOptionPane.showInputDialog(
						"Enter the name of cube (optional)", "cube");
				float x = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the width", "10"));
				float y = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the height", "10"));
				float z = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the lenght", "10"));

				if (name == null)
					name = "cube";

				scene.addBox(x / 100, y / 100, z / 100, color, name);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getClass() + ": "
						+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Add a sphere in the World3D. This method asks the properties and calls
	 * another method to add the object.
	 */
	static void addSphere() {

		Color newColor = JColorChooser.showDialog(desktop, "Select Color",
				desktop.getBackground());

		if (newColor != null) {

			Color3f color = new Color3f(0.0f, 0.0f, 0.0f);
			color.set(newColor);

			// Attributes
			ColoringAttributes attributes = new ColoringAttributes();
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
			attributes.setColor(color);

			// Appearance
			Appearance appearance = new Appearance();
			appearance
					.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
			appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
			appearance.setColoringAttributes(attributes);

			try {

				String name = JOptionPane.showInputDialog(
						"Enter the name of sphere (optional)", "sphere");
				float raio = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the radius", "10"));

				if (name == null)
					name = "sphere";

				scene.addSphere(raio / 100, color, name);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getClass() + ": "
						+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Add a cylinder in the World3D. This method asks the properties and calls
	 * another method to add the object.
	 */
	static void addCylinder() {

		Color newColor = JColorChooser.showDialog(desktop, "Select Color",
				desktop.getBackground());

		if (newColor != null) {

			Color3f color = new Color3f(0.0f, 0.0f, 0.0f);
			color.set(newColor);

			// Attributes
			ColoringAttributes attributes = new ColoringAttributes();
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
			attributes.setColor(color);

			// Appearance
			Appearance appearance = new Appearance();
			appearance
					.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
			appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
			appearance.setColoringAttributes(attributes);

			try {

				String name = JOptionPane.showInputDialog(
						"Enter the name of cylinder (optional)", "cylinder");
				float raio = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the radius", "10"));
				float altura = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the height", "30"));

				if (name == null)
					name = "cylinder";

				scene.addCylinder(raio / 100, altura / 100, color, name);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getClass() + ": "
						+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Add a cone in the World3D. This method asks the properties and calls
	 * another method to add the object.
	 */
	static void addCone() {

		Color newColor = JColorChooser.showDialog(desktop, "Select Color",
				desktop.getBackground());

		if (newColor != null) {

			Color3f color = new Color3f(0.0f, 0.0f, 0.0f);
			color.set(newColor);

			// Attributes
			ColoringAttributes attributes = new ColoringAttributes();
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
			attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
			attributes.setColor(color);

			// Appearance
			Appearance appearance = new Appearance();
			appearance
					.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
			appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
			appearance.setColoringAttributes(attributes);

			try {

				String name = JOptionPane.showInputDialog(
						"Enter the name of cone (optional)", "cone");
				float raio = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the radius", "10"));
				float altura = Float.parseFloat(JOptionPane.showInputDialog(
						"Enter the height", "30"));

				if (name == null)
					name = "cone";

				scene.addCone(raio / 100, altura / 100, color, name);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getClass() + ": "
						+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Returns the desktop. Used in class GenerateScript to display the file
	 * dialog.
	 */
	public static JDesktopPane getDesktop() {
		return desktop;
	}

	/**
	 * Returns the motion factor. Used in class MoveObjectBehavior when an
	 * object moves.
	 */
	public static float getMotionFactor() {
		return motionFactor;
	}

	public static void main(String[] args) {
		
		try {
			
			new Main().setVisible(true);
			// new Splash();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getClass() + ": "
					+ e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
			
			StringWriter sw = new StringWriter();  
			PrintWriter pw = new PrintWriter (sw);  
			e.printStackTrace (pw);  
			JOptionPane.showMessageDialog(null, sw.toString(), "Stack Trace", JOptionPane.ERROR_MESSAGE);		         
			         
			System.exit(1);
		}

	}

}