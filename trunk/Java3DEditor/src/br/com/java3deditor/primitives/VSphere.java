package br.com.java3deditor.primitives;

import java.io.Serializable;
import javax.media.j3d.Appearance;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * Subclass of Sphere. Has the property name and may have more in the future.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class VSphere extends Sphere implements Serializable {

	private static final long serialVersionUID = 1L;

	public String nome = "";

	public VSphere(float raio, Appearance aparencia, String Name) {

		super(raio, Sphere.GENERATE_TEXTURE_COORDS, aparencia);
		nome = Name;

	}

	public String getName() {
		return nome;
	}

	public void setNome(String newName) {
		nome = newName;
	}

}
