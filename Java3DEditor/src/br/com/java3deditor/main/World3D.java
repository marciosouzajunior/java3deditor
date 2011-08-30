package br.com.java3deditor.main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineArray;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JInternalFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import br.com.java3deditor.behaviors.MoveObjectBehavior;
import br.com.java3deditor.behaviors.SelectObjectBehavior;
import br.com.java3deditor.primitives.VBox;
import br.com.java3deditor.primitives.VCone;
import br.com.java3deditor.primitives.VCylinder;
import br.com.java3deditor.primitives.VSphere;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import edu.brown.cs.exploratories.components.java3d.primitive.GridImpl;

/**
 * Class that creates the default scene with the grid and axes.
 * Also has methods to add, select and move objects.
 * 
 * @author			Márcio de Souza Júnior
 * @version     	1.0.0
 */

public class World3D extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	// Default objects of the scene 
	private static BranchGroup sceneRoot = new BranchGroup();
	private static OrbitBehavior orbit = new OrbitBehavior();
	private static Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
	private static SimpleUniverse universe = new SimpleUniverse(canvas);
	private static ViewingPlatform view = universe.getViewingPlatform();

	// List used in various parts of the program to cycle through the objects
	// in the scene. The traverse method is used to populate it with objects.
	private static List<Object> objects = new ArrayList<Object>();

	// Used to capture the object clicked
	private final static PickCanvas pickCanvas = new PickCanvas(canvas, sceneRoot);

	World3D() {

		super("Scene");

		// Default settings
		view.setNominalViewingTransform();
		sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		sceneRoot.setCapability(BranchGroup.ALLOW_DETACH);
		orbit.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
		view.setNominalViewingTransform();
		view.setViewPlatformBehavior(orbit);
		pickCanvas.setMode(PickCanvas.BOUNDS);

		// Background
		Color3f corFundo = new Color3f(new Color(1, 166, 201));
		Background bg = new Background(corFundo);
		bg.setApplicationBounds(new BoundingSphere());
		sceneRoot.addChild(bg);

		// Create the grid
		TransformGroup tGrid = new TransformGroup();
		GridImpl grid = new GridImpl();
		grid.getRoot().setPickable(false); // Can not be selected
		tGrid.addChild(grid.getRoot());
		Vector3d posicao = new Vector3d(-0.5f, -0.0f, -0.5f);
		Transform3D transformacao = new Transform3D();
		transformacao.set(posicao);
		tGrid.setTransform(transformacao);
		sceneRoot.addChild(tGrid);

		// Create the axes
		BranchGroup axis = Axis();
		axis.setPickable(false);
		sceneRoot.addChild(axis);

		// Compile and add the scene
		sceneRoot.compile();
		universe.addBranchGraph(sceneRoot);
		setSize(800, 600);
		getContentPane().add(canvas);

		// Listener to select and move objects
		canvas.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent e) {

				// Update the list of objects
				Enumeration<?> e2 = World3D.sceneRoot.getAllChildren();
				traverse(e2);

				// Get clicked object
				pickCanvas.setShapeLocation(e);
				PickResult result = pickCanvas.pickClosest();

				if (result == null) {

					clearSelections();

				} else {

					Primitive p = (Primitive) result.getNode(PickResult.PRIMITIVE);

					if (p != null) {
						
						// Object is flashing
						enableSelection(result);
						
						enableMovement(result.getNode(PickResult.PRIMITIVE));

					}
				}
			}

		});
	}
	
	/**
	 * Add a sphere in the scene.
	 * 
	 * @param radius	Sphere radius
	 * @param color		Sphere color
	 * @param name		Name used in script
	 * @see 			VSphere
	 */
	void addSphere(float radius, Color3f color, String name) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		TransformGroup trg = new TransformGroup();
		trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);

		// PolygonAttributes
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(paFill);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(pa);

		// Create the object
		VSphere sphere = new VSphere(radius, appearance, name);
		sphere.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		sphere.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		sphere.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		sphere.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Sphere sphereLine = new Sphere(radius, appearanceLine);
		sphereLine.setPickable(false);

		// Add
		trg.addChild(sphere);
		trg.addChild(sphereLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}
	
	/**
	 * Add a sphere in the scene. Used to clone objects.
	 * 
	 * @param radius	Sphere radius
	 * @param color		Sphere color
	 * @param name		Name used in script
	 * @param trg		TransformGroup containing the necessary transformations
	 * @see 			VSphere
	 */
	void addSphere(float radius, Color3f color, String name, TransformGroup trg) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		// TransformGroup trg = new TransformGroup();
		// trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		// trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);

		// PolygonAttributes
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(paFill);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(pa);

		// Create the object
		VSphere sphere = new VSphere(radius, appearance, name);
		sphere.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		sphere.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		sphere.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		sphere.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Sphere sphereLine = new Sphere(radius, appearanceLine);
		sphereLine.setPickable(false);

		// Add
		trg.addChild(sphere);
		trg.addChild(sphereLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}
	
	/**
	 * Add a cylinder in the scene.
	 * 
	 * @param radius	Cylinder radius
	 * @param height	Cylinder height
	 * @param color		Cylinder color
	 * @param name		Name used in script
	 * @see 			VCylinder
	 */
	void addCylinder(float radius, float height, Color3f color, String name) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		TransformGroup trg = new TransformGroup();
		trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);
		
		// PolygonAttributes
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(paFill);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(pa);

		// Create the object
		VCylinder cylinder = new VCylinder(radius, height, appearance, name);
		cylinder.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		cylinder.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		cylinder.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		cylinder.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Cylinder cylinderLine = new Cylinder(radius, height, appearanceLine);
		cylinderLine.setPickable(false);

		// Add
		trg.addChild(cylinder);
		trg.addChild(cylinderLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}


	/**
	 * Add a cylinder in the scene. Used to clone objects.
	 * 
	 * @param radius	Cylinder radius
	 * @param height	Cylinder height
	 * @param color		Cylinder color
	 * @param name		Name used in script
	 * @param trg		TransformGroup containing the necessary transformations 
	 * @see 			VCylinder
	 */
	void addCylinder(float radius, float height, Color3f color, String name,
			TransformGroup trg) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		// TransformGroup trg = new TransformGroup();
		// trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		// trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);
		
		// PolygonAttributes
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(paFill);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(pa);

		// Create the object
		VCylinder cylinder = new VCylinder(radius, height, appearance, name);
		cylinder.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		cylinder.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		cylinder.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		cylinder.setCapability(Cylinder.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Cylinder cylinderLine = new Cylinder(radius, height, appearanceLine);
		cylinderLine.setPickable(false);

		// Add
		trg.addChild(cylinder);
		trg.addChild(cylinderLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}

	/**
	 * Add a cone in the scene.
	 * 
	 * @param radius	Cone radius
	 * @param height	Cone height
	 * @param color		Cone color
	 * @param name		Name used in script
	 * @see 			VCone
	 */
	void addCone(float radius, float height, Color3f color, String name) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		TransformGroup trg = new TransformGroup();
		trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);
		
		// PolygonAttributes
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(pa);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(paFill);

		// Create the object
		VCone cone = new VCone(radius, height, appearance, name);
		cone.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		cone.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		cone.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		cone.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);
		
		// Create the object wire frame
		Cone coneLine = new Cone(radius, height, appearanceLine);
		coneLine.setPickable(false);

		// Add
		trg.addChild(cone);
		trg.addChild(coneLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}

	/**
	 * Add a cone in the scene. Used to clone objects.
	 * 
	 * @param radius	Cone radius
	 * @param height	Cone height
	 * @param color		Cone color
	 * @param name		Name used in script
	 * @see 			VCone
	 */
	void addCone(float radius, float height, Color3f color, String name,
			TransformGroup trg) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		// TransformGroup trg = new TransformGroup();
		// trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		// trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_READ);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);
		
		// PolygonAttributes
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(pa);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(paFill);

		// Create the object
		VCone cone = new VCone(radius, height, appearance, name);
		cone.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		cone.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		cone.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		cone.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Cone coneLine = new Cone(radius, height, appearanceLine);
		coneLine.setPickable(false);

		// Add
		trg.addChild(cone);
		trg.addChild(coneLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}

	/**
	 * Add a box in the scene.
	 * 
	 * @param d1		x dimension of cube
	 * @param d2        y dimension of cube
	 * @param d3        z dimension of cube
	 * @param color		Cone color
	 * @param name		Name used in script
	 * @see 			VBox
	 */

	void addBox(float d1, float d2, float d3, Color3f color, String name) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		TransformGroup trg = new TransformGroup();
		trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);

		// PolygonAttributes
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(pa);

		// Appearance of the wire frame
		Appearance appearanceLine = new Appearance();
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		appearanceLine.setPolygonAttributes(paFill);

		// Create the object
		VBox box = new VBox(d1, d2, d3, appearance, name);
		box.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		box.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		box.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		box.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Box boxLine = new Box(d1, d2, d3, appearanceLine);
		boxLine.setPickable(false);

		// Add
		trg.addChild(box);
		trg.addChild(boxLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}

	/**
	 * Add a box in the scene. Used to clone objects.
	 * 
	 * @param d1		x dimension of cube
	 * @param d2        y dimension of cube
	 * @param d3        z dimension of cube
	 * @param color		Cone color
	 * @param name		Name used in script
	 * @see 			VBox
	 */
	void addBox(float d1, float d2, float d3, Color3f color, String name,
			TransformGroup trg) {

		// BranchGroup
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_DETACH);
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// TransformGroup
		// TransformGroup trg = new TransformGroup();
		// trg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		// trg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// trg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		// Appearance
		ColoringAttributes attributes = new ColoringAttributes();
		attributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		attributes.setColor(color);
		Appearance appearance = new Appearance();
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		appearance.setColoringAttributes(attributes);

		// PolygonAttributes
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		appearance.setPolygonAttributes(pa);

		// Appearance of the wire frame
		Appearance aparenciaLine = new Appearance();
		PolygonAttributes paFill = new PolygonAttributes();
		paFill.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		aparenciaLine.setPolygonAttributes(paFill);

		// Create the object
		VBox box = new VBox(d1, d2, d3, appearance, name);
		box.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
		box.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		box.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
		box.setCapability(Primitive.ENABLE_APPEARANCE_MODIFY);

		// Create the object wire frame
		Box boxLine = new Box(d1, d2, d3, aparenciaLine);
		boxLine.setPickable(false);

		// Add
		trg.addChild(box);
		trg.addChild(boxLine);
		bg.addChild(trg);
		sceneRoot.addChild(bg);

	}

	/**
	 * Clean the behaviors of selection and movement of objects.
	 * 
	 * @see 	SelectObjectBehavior
	 * @see		MoveObjectBehavior 		
	 */
	static void clearSelections() {

		for (int x = 0; x < objects.size(); x++) {

			if (objects.get(x) instanceof SelectObjectBehavior) {

				((SelectObjectBehavior) objects.get(x)).restoreColor();
				((BranchGroup) (((Node) objects.get(x)).getParent())).detach();
				objects.remove(objects.get(x));

			}

			if (objects.get(x) instanceof MoveObjectBehavior) {

				((BranchGroup) (((Node) objects.get(x)).getParent())).detach();
				objects.remove(objects.get(x));
			}

		}

	}

	/**
	 * Traverse all objects in the scene and puts them in an array.
	 * 
	 * @param e		Enumeration containing all objects in the scene 
	 */
	public static void traverse(Enumeration<?> e) {

		while (e.hasMoreElements()) {

			Object object = e.nextElement();

			if (object instanceof Primitive) {

				// Do not add primitive type POLYGON_LINE. 
				// They do not appear on the scene generated by the script.
				if (((Primitive) object).getAppearance().getPolygonAttributes()
						.getPolygonMode() == PolygonAttributes.POLYGON_FILL) 
				{
					objects.add(object);
				}
				
			} else if ((object instanceof SelectObjectBehavior)
					|| (object instanceof MoveObjectBehavior)) 
			{
				
				objects.add(object);

			// If BranchGroup or TransformGroup, call recursive to return the children's them.
			} else if (object instanceof BranchGroup) 
			{
				
				BranchGroup f = (BranchGroup) object;
				Enumeration<?> en = f.getAllChildren();
				traverse(en);
				
			} else if (object instanceof TransformGroup) 
			{
				
				TransformGroup f = (TransformGroup) object;
				Enumeration<?> en = f.getAllChildren();
				traverse(en);
			
			}
		}
	}

	/**
	 * Add behavior to move the object.
	 * 
	 * @param obj	The object to be moved
	 * @see			MoveObjectBehavior
	 */
	static void enableMovement(Object obj) {

		// Create behavior and passes the object TransformGroup
		MoveObjectBehavior mr = new MoveObjectBehavior(((Node) obj).getParent());
		mr.setSchedulingBounds(new BoundingSphere());

		// Create a BranchGroup and add behavior
		BranchGroup brg = new BranchGroup();
		brg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		brg.setCapability(BranchGroup.ALLOW_DETACH);
		brg.addChild(mr);

		// Add BranchGroup in TransformGroup of object
		((TransformGroup) ((Node) obj).getParent()).addChild(brg);

	}

	/**
	 * Add behavior to select the object.
	 * 
	 * @param obj	The object to be selected
	 * @see			SelectObjectBehavior
	 */
	static void enableSelection(Object obj) {

		clearSelections();

		// Create behavior and passes the object with its color
		Color3f currentColor = new Color3f();
		((Primitive) ((PickResult) obj).getNode(PickResult.PRIMITIVE))
				.getAppearance().getColoringAttributes().getColor(currentColor);
		SelectObjectBehavior mr = new SelectObjectBehavior((PickResult) obj,
				currentColor);
		mr.setSchedulingBounds(new BoundingSphere());

		// Create a BranchGroup and add behavior
		BranchGroup brg = new BranchGroup();
		brg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		brg.setCapability(BranchGroup.ALLOW_DETACH);
		brg.addChild(mr);

		// adiciona o branch no trg do mundo3d
		((TransformGroup) ((Node) ((PickResult) obj)
				.getNode(PickResult.PRIMITIVE)).getParent()).addChild(brg);

	}
	
	/**
	 * Reset the view of scene.
	 */	
	static void resetView() {
		
		OrbitBehavior orbit = World3D.orbit;
		orbit.setSchedulingBounds(new BoundingSphere(
				new Point3d(0.0, 0.0, 0.0), 100.1));
		World3D.view.setNominalViewingTransform();
		World3D.view.setViewPlatformBehavior(orbit);
		
	}
	
	public static List<Object> getObjects() {
		return objects;
	}

	public static void setObjects(List<Object> objects) {
		World3D.objects = objects;
	}	
	
	public static BranchGroup getSceneRoot() {
		return sceneRoot;
	}

	public static PickCanvas getPickcanvas() {
		return pickCanvas;
	}	
	
	/**
	 * Create the axes in the scene
	 * 
	 * @return		BranchGroup
	 */
	/*
	 * axisapp.java 1.0 98/11/25
	 * 
	 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
	 * 
	 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to
	 * use, modify and redistribute this software in source and binary code
	 * form, provided that i) this copyright notice and license appear on all
	 * copies of the software; and ii) Licensee does not utilize the software in
	 * a manner which is disparaging to Sun.
	 * 
	 * This software is provided "AS IS," without a warranty of any kind. ALL
	 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
	 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
	 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT
	 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
	 * MODIFYING OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT
	 * WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA,
	 * OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
	 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
	 * ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS
	 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
	 * 
	 * This software is not designed or intended for use in on-line control of
	 * aircraft, air traffic, aircraft navigation or aircraft communications; or
	 * in the design, construction, operation or maintenance of any nuclear
	 * facility. Licensee represents and warrants that it will not use or
	 * redistribute the Software for such purposes.
	 */

	public BranchGroup Axis() {

		BranchGroup axisBG = new BranchGroup();

		// create line for X axis
		LineArray axisXLines = new LineArray(2, LineArray.COORDINATES);
		axisBG.addChild(new Shape3D(axisXLines));

		axisXLines.setCoordinate(0, new Point3f(-1.2f, 0.0f, 0.0f));
		axisXLines.setCoordinate(1, new Point3f(1.2f, 0.0f, 0.0f));

		Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
		Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
		Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);

		// create line for Y axis
		LineArray axisYLines = new LineArray(2, LineArray.COORDINATES
				| LineArray.COLOR_3);
		axisBG.addChild(new Shape3D(axisYLines));

		axisYLines.setCoordinate(0, new Point3f(0.0f, -1.2f, 0.0f));
		axisYLines.setCoordinate(1, new Point3f(0.0f, 1.2f, 0.0f));

		axisYLines.setColor(0, green);
		axisYLines.setColor(1, blue);

		// create line for Z axis
		Point3f z1 = new Point3f(0.0f, 0.0f, -1.2f);
		Point3f z2 = new Point3f(0.0f, 0.0f, 1.2f);

		LineArray axisZLines = new LineArray(10, LineArray.COORDINATES
				| LineArray.COLOR_3);
		axisBG.addChild(new Shape3D(axisZLines));

		axisZLines.setCoordinate(0, z1);
		axisZLines.setCoordinate(1, z2);
		axisZLines.setCoordinate(2, z2);

		Color3f colors[] = new Color3f[9];

		colors[0] = new Color3f(0.0f, 1.0f, 1.0f);
		for (int v = 0; v < 9; v++) {
			colors[v] = red;
		}

		axisZLines.setColors(1, colors);

		return axisBG;

	}

}
