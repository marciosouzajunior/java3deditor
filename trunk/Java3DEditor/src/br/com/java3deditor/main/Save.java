package br.com.java3deditor.main;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;


/**
 * Class to save a scene in a file. To be implemented...
 *  
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class Save {

	public Save() {

		try

		{

			FileOutputStream file = new FileOutputStream("c:\\test\\test.j3d");

			ObjectOutputStream fileObjects = new ObjectOutputStream(file);

			Enumeration<?> e = World3D.getSceneRoot().getAllChildren();

			while (e.hasMoreElements()) {

				Object object = e.nextElement();

				fileObjects.writeObject(object);
				System.out.println("write: " + object.getClass().getName());
				
			}
			
			fileObjects.flush();
			fileObjects.close();
			file.flush();
			file.close();
			System.out.println("Successfully saved!");

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

		
	
}