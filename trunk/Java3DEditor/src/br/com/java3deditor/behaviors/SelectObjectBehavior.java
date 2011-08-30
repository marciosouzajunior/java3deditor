package br.com.java3deditor.behaviors;

import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.picking.PickResult;

/**
 * class has methods that make the object flashing when selected.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class SelectObjectBehavior extends Behavior {

	Color3f originalColor = new Color3f(0.0f, 0.0f, 0.0f);
	PickResult object;

	// Variable used for the color change
	private boolean aux = true;

	public SelectObjectBehavior(PickResult obj, Color3f CorOriginal) {
		object = obj;
		originalColor = CorOriginal;
	}

	@Override
	public void initialize() {
		wakeupOn(new WakeupOnElapsedTime(200));
	}
	
	/**
	 * Receives an enumeration with the stimulus 
	 * and change the object color.
	 * 
	 * @param arg0		Enumeration with stimulus
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processStimulus(Enumeration arg0) {

		if (aux == true) {

			setColor(new Color3f(originalColor.x + 0.2f, originalColor.y + 0.2f, originalColor.z + 0.2f));
			aux = false;
			
		} else {
			
			setColor(originalColor);
			aux = true;
		}

		wakeupOn(new WakeupOnElapsedTime(200));
	}

	/**
	 * Method used to assign colors to the object when flashing.
	 * 
	 * @param cor	Color to be assigned to the object
	 * @see 		Color3f
	 */
	private void setColor(Color3f cor) {

		((Primitive) object.getNode(PickResult.PRIMITIVE)).getAppearance().getColoringAttributes().setColor(cor);
		
	}

	/**
	 * Restore the original color when the object loses the selection.
	 */
	public void restoreColor() {

		setColor(originalColor);

	}

	/**
	 * Method to assign a new color to the object.
	 * 
	 * @param newColor	Color to be assigned
	 * @see 			Color3f
	 */
	public void setNewColor(Color3f newColor) {

		originalColor = newColor;
	
	}

	public PickResult getObject() {
		return object;
	}
}