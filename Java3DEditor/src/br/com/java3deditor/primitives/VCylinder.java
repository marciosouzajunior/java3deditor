package br.com.java3deditor.primitives;

import java.io.Serializable;
import javax.media.j3d.Appearance;
import com.sun.j3d.utils.geometry.Cylinder;

/**
 * Subclass of Cylinder. Has the property name and may have more in the future.
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class VCylinder extends Cylinder implements Serializable {

	private static final long serialVersionUID = 1L;

	public String nome = "";

	public VCylinder(float raio, float altura, Appearance aparencia, String Name) {

		super(raio, altura, Cylinder.GENERATE_TEXTURE_COORDS, aparencia);
		nome = Name;

	}

	public String getName() {
		return nome;
	}

	public void setName(String newNome) {
		nome = newNome;
	}

}
