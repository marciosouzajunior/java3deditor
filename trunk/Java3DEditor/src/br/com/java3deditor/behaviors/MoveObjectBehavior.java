package br.com.java3deditor.behaviors;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3d;
import br.com.java3deditor.main.Main;

/**
 * Class with methods to move, rotate and scale the objects in the scene.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class MoveObjectBehavior extends Behavior {

	float x = 0.0f;
	float y = 0.0f;
	float z = 0.0f;

	WakeupOnAWTEvent w1 = new WakeupOnAWTEvent(MouseEvent.MOUSE_PRESSED); // Not implemented...
	WakeupOnAWTEvent w2 = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);

	WakeupCriterion[] criteria = { w1, w2 };
	WakeupCondition conditions = new WakeupOr(criteria);

	Object object;

	public MoveObjectBehavior(Object obj) {
		object = obj;
	}

	@Override
	public void initialize() {
		wakeupOn(conditions);
	}
	
	/**
	 * Receives an enumeration with the stimulus and
	 * identifies what types and then call the method.
	 * 
	 * @param arg0		Enumeration with stimulus
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processStimulus(Enumeration arg0) {

		int i = 0;
		while (arg0.hasMoreElements()) {
			
			AWTEvent[] events = ((WakeupOnAWTEvent) (arg0.nextElement())).getAWTEvent();

			// Keyboard events
			if (events[i] instanceof KeyEvent) {

				// Move object
				if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_NUMPAD4) 
				{
					x = x - Main.getMotionFactor();
					moveObject(x, y, z, object);
					
				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_NUMPAD6) 
				{
					x = x + Main.getMotionFactor();
					moveObject(x, y, z, object);
					
				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_NUMPAD7) 
				{
					y = y + Main.getMotionFactor();
					moveObject(x, y, z, object);
					
				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_NUMPAD9) 
				{
					y = y - Main.getMotionFactor();
					moveObject(x, y, z, object);
					
				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_NUMPAD8) 
				{
					z = z - Main.getMotionFactor();
					moveObject(x, y, z, object);
					
				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_NUMPAD2) 
				{
					z = z + Main.getMotionFactor();
					moveObject(x, y, z, object);
					
				}

				// Scale object
				else if (((KeyEvent) (events[i])).getKeyCode() == 109 
						|| ((KeyEvent) (events[i])).getKeyCode() == 45) 
				{
					scaleObjeto(0.99f, object); // -

				} else if (((KeyEvent) (events[i])).getKeyCode() == 107 
						|| ((KeyEvent) (events[i])).getKeyCode() == 61) 
				{
					scaleObjeto(1.01f, object); // +

				}

				// Scale object non-uniform
				else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_D) 
				{
					scaleNonUniform(1.02f, 1.0f, 1.0f, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_A) 
				{
					scaleNonUniform(0.98f, 1.0f, 1.0f, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_W) 
				{
					scaleNonUniform(1.0f, 1.02f, 1.0f, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_S) 
				{
					scaleNonUniform(1.0f, 0.98f, 1.0f, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_Q) 
				{
					scaleNonUniform(1.0f, 1.0f, 1.02f, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_E) 
				{
					scaleNonUniform(1.0f, 1.0f, 0.98f, object);

				}

				// Rotate object
				// Y axis
				else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_PAGE_DOWN) 
				{
					y = y - 0.05f;
					rotateObjectY(y, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_DELETE) 
				{
					y = y + 0.05f;
					rotateObjectY(y, object);

				}
				// X axis
				else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_HOME) 
				{
					x = x - 0.05f;
					rotateObjectX(x, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_END) 
				{
					x = x + 0.05f;
					rotateObjectX(x, object);

				}
				// Z axis
				else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_INSERT) 
				{
					z = z + 0.05f;
					rotateObjectZ(z, object);

				} else if (((KeyEvent) (events[i])).getKeyCode() == KeyEvent.VK_PAGE_UP) 
				{
					z = z - 0.05f;
					rotateObjectZ(z, object);

				}

				// Resets the values ​​because the transformation is accumulated
				x = 0.0f;
				y = 0.0f;
				z = 0.0f;

			}
			
			// Mouse events
			// Not implemented yet
			/*
			 * else if (events[i] instanceof MouseEvent) { if
			 * (((MouseEvent)(events[i])).getButton() == MouseEvent.BUTTON1) {
			 * 
			 * }else if (((MouseEvent)(events[i])).getButton() ==
			 * MouseEvent.BUTTON2) {
			 * 
			 * }
			 * 
			 * }
			 */
		}

		wakeupOn(conditions);
	}

	/**
	 * Method that applies transformations to move the object.
	 * 
	 * @param x		X coordinate of the new position
	 * @param y     Y coordinate of the new position
	 * @param z		Z coordinate of the new position
	 * @param obj   Object to be moved
	 * @see 		Transform3D
	 */
	void moveObject(float x, float y, float z, Object obj) {

		// Creates a Transform3D to receive the transformation
		Transform3D transformation = new Transform3D();
		transformation.set(new Vector3d(x, y, z));

		// Other Transform3D receives the current transformations
		Transform3D tempTransform = new Transform3D();
		((TransformGroup) obj).getChild(0).getLocalToVworld(tempTransform);

		// Accumulate the transformations
		tempTransform.mul(transformation);

		// Apply the transformations
		TransformGroup tg = (TransformGroup) ((Node) obj);
		tg.setTransform(tempTransform);

	}

	/**
	 * Method that applies transformations to rotate the object in X axis.
	 * 
	 * @param x		X coordinate of rotation
	 * @param obj   Object to be moved
	 * @see 		Transform3D
	 */
	void rotateObjectX(float x, Object obj) {

		// Creates a Transform3D to receive the transformation
		Transform3D transformation = new Transform3D();
		transformation.rotX(x);

		// Other Transform3D receives the current transformations
		Transform3D tempTransform = new Transform3D();
		((TransformGroup) obj).getChild(0).getLocalToVworld(tempTransform);

		// Accumulate the transformations
		tempTransform.mul(transformation);

		// Apply the transformations
		TransformGroup tg = (TransformGroup) ((Node) obj);
		tg.setTransform(tempTransform);

	}

	/**
	 * Method that applies transformations to rotate the object in Y axis.
	 * 
	 * @param y		Y coordinate of rotation
	 * @param obj   Object to be moved
	 * @see 		Transform3D
	 */
	void rotateObjectY(float y, Object obj) {

		// Creates a Transform3D to receive the transformation
		Transform3D transformation = new Transform3D();
		transformation.rotY(y);

		// Other Transform3D receives the current transformations
		Transform3D tempTransform = new Transform3D();
		((TransformGroup) obj).getChild(0).getLocalToVworld(tempTransform);

		// Accumulate the transformations
		tempTransform.mul(transformation);

		// Apply the transformations
		TransformGroup tg = (TransformGroup) ((Node) obj);
		tg.setTransform(tempTransform);

	}

	/**
	 * Method that applies transformations to rotate the object in Z axis.
	 * 
	 * @param z		Z coordinate of rotation
	 * @param obj   Object to be moved
	 * @see 		Transform3D
	 */
	void rotateObjectZ(float z, Object obj) {

		// Creates a Transform3D to receive the transformation
		Transform3D transformation = new Transform3D();
		transformation.rotZ(z);

		// Other Transform3D receives the current transformations
		Transform3D tempTransform = new Transform3D();
		((TransformGroup) obj).getChild(0).getLocalToVworld(tempTransform);

		// Accumulate the transformations
		tempTransform.mul(transformation);

		// Apply the transformations
		TransformGroup tg = (TransformGroup) ((Node) obj);
		tg.setTransform(tempTransform);

	}

	/**
	 * Method that applies transformations to scale the object.
	 * 
	 * @param x		Scale factor of the object
	 * @param obj   Object to be scaled
	 * @see 		Transform3D
	 */
	void scaleObjeto(float x, Object obj) {

		// Creates a Transform3D to receive the transformation
		Transform3D transformation = new Transform3D();
		transformation.set(x);

		// Other Transform3D receives the current transformations
		Transform3D tempTransform = new Transform3D();
		((TransformGroup) obj).getChild(0).getLocalToVworld(tempTransform);

		// Accumulate the transformations
		tempTransform.mul(transformation);

		// Apply the transformations
		TransformGroup tg = (TransformGroup) ((Node) obj);
		tg.setTransform(tempTransform);

	}
	
	/**
	 * Method that applies transformations to scale the object non-uniform.
	 * 
	 * @param x		Scale factor of the object
	 * @param obj   Object to be scaled
	 * @see 		Transform3D
	 */
	void scaleNonUniform(float x, float y, float z, Object obj) {

		// Creates a Transform3D to receive the transformation
		Transform3D transformation = new Transform3D();
		transformation.setScale(new Vector3d(x, y, z));

		// Other Transform3D receives the current transformations
		Transform3D tempTransform = new Transform3D();
		((TransformGroup) obj).getChild(0).getLocalToVworld(tempTransform);

		// Accumulate the transformations
		tempTransform.mul(transformation);

		// Apply the transformations
		TransformGroup tg = (TransformGroup) ((Node) obj);
		tg.setTransform(tempTransform);

	}

}