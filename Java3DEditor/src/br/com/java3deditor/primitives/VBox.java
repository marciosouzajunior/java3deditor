package br.com.java3deditor.primitives;

import java.io.Serializable;
import javax.media.j3d.Appearance;
import com.sun.j3d.utils.geometry.Box;

/**
 * Subclass of Box. Has the property name and may have more in the future.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class VBox extends Box implements Serializable {

	private static final long serialVersionUID = 1L;

	public String name = "";

	public VBox(float d1, float d2, float d3, Appearance aparencia, String Name) {

		super(d1, d2, d3, Box.GENERATE_TEXTURE_COORDS, aparencia);
		name = Name;

	}

	public String getNome() {
		return name;
	}

	public void setNome(String newNome) {
		name = newNome;
	}

}
