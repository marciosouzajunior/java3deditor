package br.com.java3deditor.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Class to open a scene saved to a file. To be implemented...
 * 
 * @author Márcio de Souza Júnior
 * @version 1.0.0
 */
public class Open {

	public Open() {
		
		System.out.println("Recovering object: ");
		
		boolean eof = false;
		int x = 0;

		try
		{

			FileInputStream file = new FileInputStream("c:\\test\\test.j3d");

			ObjectInputStream fileObjects = new ObjectInputStream(file);

			// Clear the list
			World3D.setObjects(new ArrayList<Object>());

			while (!eof) {

				try {
					World3D.getObjects().add(fileObjects.readObject());
					x++;
				} catch (EOFException e) {
					break;
				}

			}

			for (int y = 0; y < World3D.getObjects().size(); y++) {
				if (World3D.getObjects().get(y) != null)
					System.out.println(World3D.getObjects().get(y).getClass().getSimpleName());

			}

			fileObjects.close();

			file.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}
	
	}
	
}
