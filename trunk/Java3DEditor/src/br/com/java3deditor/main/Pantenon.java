package br.com.java3deditor.main;

/******************************************************
* Code automatically generated by Java3DEditor 1.0.0  *
* Developer: Márcio de Souza Júnior                   *
* Email/MSN: marciosouzajunior@gmail.com              *
* Site: java3deditor.com                              *		
* Version of Java 3D: 1.5.3                           *
*******************************************************/

import java.awt.GraphicsConfiguration;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

/**
 * Class example of a script generated by the software.
 */
public class Pantenon {

	// Constructor
 	public Pantenon () {

 		// Default code of the scene
 		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
 		Canvas3D scene = new Canvas3D(config);
		BranchGroup brg = new BranchGroup ();

		/*****************************************
				    Object Declarations	          
		******************************************/

		//
		// Object: cube6
		//

		// TransformGroup
 		TransformGroup trgcube6  = new TransformGroup ();
		trgcube6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcube6 = new Color3f(0.4f, 0.4f, 0.4f);
		ColoringAttributes colorAttributescube6 = new ColoringAttributes();
		colorAttributescube6.setColor(corcube6);
		Appearance appearancecube6 = new Appearance();
		appearancecube6.setColoringAttributes(colorAttributescube6);

		// Declaration
		Box cube6 = new Box(0.1f, 0.1f,0.1f, Box.GENERATE_TEXTURE_COORDS, appearancecube6);
		trgcube6.addChild(cube6);

		// Position
		Vector3d v3DPositioncube6 = new Vector3d(0.0, 0.032005900034077515, 0.051400856790878476);
		Transform3D trfPositioncube6 = new Transform3D();
		trfPositioncube6.set(v3DPositioncube6);

		// Scale
		Vector3d v3DScalecube6 = new Vector3d(5.374262813794287, 0.29026348913521677, 2.570042896988859);
		Transform3D trfScalecube6 = new Transform3D();
		trfScalecube6.setScale(v3DScalecube6);
		trfScalecube6.mul(trfPositioncube6); // Accumulating
		trgcube6.setTransform(trfScalecube6);

		// Rotation
		Matrix3d m3DRotationcube6 = new Matrix3d(5.374262813794287, 0.0, 0.0,
												0.0, 0.29026348913521677, 0.0,
												0.0, 0.0, 2.570042896988859);
		trfPositioncube6.setRotation(m3DRotationcube6);

		// Apply the transforms
		trgcube6.setTransform(trfPositioncube6);

		// Add to BranchGroup
		brg.addChild(trgcube6);


		//
		// Object: cylinder8
		//

		// TransformGroup
 		TransformGroup trgcylinder8  = new TransformGroup ();
		trgcylinder8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder8 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder8 = new ColoringAttributes();
		colorAttributescylinder8.setColor(corcylinder8);
		Appearance appearancecylinder8 = new Appearance();
		appearancecylinder8.setColoringAttributes(colorAttributescylinder8);

		// Declaration
		Cylinder cylinder8 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder8);
		trgcylinder8.addChild(cylinder8);

		// Position
		Vector3d v3DPositioncylinder8 = new Vector3d(-0.2480455043967009, 0.40120408511001543, 0.15682837278434425);
		Transform3D trfPositioncylinder8 = new Transform3D();
		trfPositioncylinder8.set(v3DPositioncylinder8);

		// Scale
		Vector3d v3DScalecylinder8 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder8 = new Transform3D();
		trfScalecylinder8.setScale(v3DScalecylinder8);
		trfScalecylinder8.mul(trfPositioncylinder8); // Accumulating
		trgcylinder8.setTransform(trfScalecylinder8);

		// Rotation
		Matrix3d m3DRotationcylinder8 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder8.setRotation(m3DRotationcylinder8);

		// Apply the transforms
		trgcylinder8.setTransform(trfPositioncylinder8);

		// Add to BranchGroup
		brg.addChild(trgcylinder8);


		//
		// Object: cylinder10
		//

		// TransformGroup
 		TransformGroup trgcylinder10  = new TransformGroup ();
		trgcylinder10.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder10 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder10 = new ColoringAttributes();
		colorAttributescylinder10.setColor(corcylinder10);
		Appearance appearancecylinder10 = new Appearance();
		appearancecylinder10.setColoringAttributes(colorAttributescylinder10);

		// Declaration
		Cylinder cylinder10 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder10);
		trgcylinder10.addChild(cylinder10);

		// Position
		Vector3d v3DPositioncylinder10 = new Vector3d(-0.409340148934099, 0.40120408511001543, 0.15682837278434425);
		Transform3D trfPositioncylinder10 = new Transform3D();
		trfPositioncylinder10.set(v3DPositioncylinder10);

		// Scale
		Vector3d v3DScalecylinder10 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder10 = new Transform3D();
		trfScalecylinder10.setScale(v3DScalecylinder10);
		trfScalecylinder10.mul(trfPositioncylinder10); // Accumulating
		trgcylinder10.setTransform(trfScalecylinder10);

		// Rotation
		Matrix3d m3DRotationcylinder10 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder10.setRotation(m3DRotationcylinder10);

		// Apply the transforms
		trgcylinder10.setTransform(trfPositioncylinder10);

		// Add to BranchGroup
		brg.addChild(trgcylinder10);


		//
		// Object: cylinder12
		//

		// TransformGroup
 		TransformGroup trgcylinder12  = new TransformGroup ();
		trgcylinder12.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder12 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder12 = new ColoringAttributes();
		colorAttributescylinder12.setColor(corcylinder12);
		Appearance appearancecylinder12 = new Appearance();
		appearancecylinder12.setColoringAttributes(colorAttributescylinder12);

		// Declaration
		Cylinder cylinder12 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder12);
		trgcylinder12.addChild(cylinder12);

		// Position
		Vector3d v3DPositioncylinder12 = new Vector3d(0.2555085078176117, 0.40120408511001543, 0.15682837278434425);
		Transform3D trfPositioncylinder12 = new Transform3D();
		trfPositioncylinder12.set(v3DPositioncylinder12);

		// Scale
		Vector3d v3DScalecylinder12 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder12 = new Transform3D();
		trfScalecylinder12.setScale(v3DScalecylinder12);
		trfScalecylinder12.mul(trfPositioncylinder12); // Accumulating
		trgcylinder12.setTransform(trfScalecylinder12);

		// Rotation
		Matrix3d m3DRotationcylinder12 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder12.setRotation(m3DRotationcylinder12);

		// Apply the transforms
		trgcylinder12.setTransform(trfPositioncylinder12);

		// Add to BranchGroup
		brg.addChild(trgcylinder12);


		//
		// Object: cylinder14
		//

		// TransformGroup
 		TransformGroup trgcylinder14  = new TransformGroup ();
		trgcylinder14.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder14 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder14 = new ColoringAttributes();
		colorAttributescylinder14.setColor(corcylinder14);
		Appearance appearancecylinder14 = new Appearance();
		appearancecylinder14.setColoringAttributes(colorAttributescylinder14);

		// Declaration
		Cylinder cylinder14 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder14);
		trgcylinder14.addChild(cylinder14);

		// Position
		Vector3d v3DPositioncylinder14 = new Vector3d(-0.08675085985930388, 0.40120408511001543, 0.15682837278434425);
		Transform3D trfPositioncylinder14 = new Transform3D();
		trfPositioncylinder14.set(v3DPositioncylinder14);

		// Scale
		Vector3d v3DScalecylinder14 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder14 = new Transform3D();
		trfScalecylinder14.setScale(v3DScalecylinder14);
		trfScalecylinder14.mul(trfPositioncylinder14); // Accumulating
		trgcylinder14.setTransform(trfScalecylinder14);

		// Rotation
		Matrix3d m3DRotationcylinder14 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder14.setRotation(m3DRotationcylinder14);

		// Apply the transforms
		trgcylinder14.setTransform(trfPositioncylinder14);

		// Add to BranchGroup
		brg.addChild(trgcylinder14);


		//
		// Object: cylinder16
		//

		// TransformGroup
 		TransformGroup trgcylinder16  = new TransformGroup ();
		trgcylinder16.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder16 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder16 = new ColoringAttributes();
		colorAttributescylinder16.setColor(corcylinder16);
		Appearance appearancecylinder16 = new Appearance();
		appearancecylinder16.setColoringAttributes(colorAttributescylinder16);

		// Declaration
		Cylinder cylinder16 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder16);
		trgcylinder16.addChild(cylinder16);

		// Position
		Vector3d v3DPositioncylinder16 = new Vector3d(0.4050011051937368, 0.40120408511001543, 0.15682837278434425);
		Transform3D trfPositioncylinder16 = new Transform3D();
		trfPositioncylinder16.set(v3DPositioncylinder16);

		// Scale
		Vector3d v3DScalecylinder16 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder16 = new Transform3D();
		trfScalecylinder16.setScale(v3DScalecylinder16);
		trfScalecylinder16.mul(trfPositioncylinder16); // Accumulating
		trgcylinder16.setTransform(trfScalecylinder16);

		// Rotation
		Matrix3d m3DRotationcylinder16 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder16.setRotation(m3DRotationcylinder16);

		// Apply the transforms
		trgcylinder16.setTransform(trfPositioncylinder16);

		// Add to BranchGroup
		brg.addChild(trgcylinder16);


		//
		// Object: cylinder18
		//

		// TransformGroup
 		TransformGroup trgcylinder18  = new TransformGroup ();
		trgcylinder18.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder18 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder18 = new ColoringAttributes();
		colorAttributescylinder18.setColor(corcylinder18);
		Appearance appearancecylinder18 = new Appearance();
		appearancecylinder18.setColoringAttributes(colorAttributescylinder18);

		// Declaration
		Cylinder cylinder18 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder18);
		trgcylinder18.addChild(cylinder18);

		// Position
		Vector3d v3DPositioncylinder18 = new Vector3d(0.08241181611894174, 0.40120408511001543, 0.15682837278434425);
		Transform3D trfPositioncylinder18 = new Transform3D();
		trfPositioncylinder18.set(v3DPositioncylinder18);

		// Scale
		Vector3d v3DScalecylinder18 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder18 = new Transform3D();
		trfScalecylinder18.setScale(v3DScalecylinder18);
		trfScalecylinder18.mul(trfPositioncylinder18); // Accumulating
		trgcylinder18.setTransform(trfScalecylinder18);

		// Rotation
		Matrix3d m3DRotationcylinder18 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder18.setRotation(m3DRotationcylinder18);

		// Apply the transforms
		trgcylinder18.setTransform(trfPositioncylinder18);

		// Add to BranchGroup
		brg.addChild(trgcylinder18);


		//
		// Object: cylinder20
		//

		// TransformGroup
 		TransformGroup trgcylinder20  = new TransformGroup ();
		trgcylinder20.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder20 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder20 = new ColoringAttributes();
		colorAttributescylinder20.setColor(corcylinder20);
		Appearance appearancecylinder20 = new Appearance();
		appearancecylinder20.setColoringAttributes(colorAttributescylinder20);

		// Declaration
		Cylinder cylinder20 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder20);
		trgcylinder20.addChild(cylinder20);

		// Position
		Vector3d v3DPositioncylinder20 = new Vector3d(-0.409340148934099, 0.40120408511001543, -0.12731350231324784);
		Transform3D trfPositioncylinder20 = new Transform3D();
		trfPositioncylinder20.set(v3DPositioncylinder20);

		// Scale
		Vector3d v3DScalecylinder20 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder20 = new Transform3D();
		trfScalecylinder20.setScale(v3DScalecylinder20);
		trfScalecylinder20.mul(trfPositioncylinder20); // Accumulating
		trgcylinder20.setTransform(trfScalecylinder20);

		// Rotation
		Matrix3d m3DRotationcylinder20 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder20.setRotation(m3DRotationcylinder20);

		// Apply the transforms
		trgcylinder20.setTransform(trfPositioncylinder20);

		// Add to BranchGroup
		brg.addChild(trgcylinder20);


		//
		// Object: cylinder22
		//

		// TransformGroup
 		TransformGroup trgcylinder22  = new TransformGroup ();
		trgcylinder22.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcylinder22 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescylinder22 = new ColoringAttributes();
		colorAttributescylinder22.setColor(corcylinder22);
		Appearance appearancecylinder22 = new Appearance();
		appearancecylinder22.setColoringAttributes(colorAttributescylinder22);

		// Declaration
		Cylinder cylinder22 = new Cylinder(0.1f, 0.3f, Cylinder.GENERATE_TEXTURE_COORDS, appearancecylinder22);
		trgcylinder22.addChild(cylinder22);

		// Position
		Vector3d v3DPositioncylinder22 = new Vector3d(0.4050011051937368, 0.40120408511001543, -0.12731350231324784);
		Transform3D trfPositioncylinder22 = new Transform3D();
		trfPositioncylinder22.set(v3DPositioncylinder22);

		// Scale
		Vector3d v3DScalecylinder22 = new Vector3d(0.39340158083564225, 2.390051175970323, 0.35517735181082244);
		Transform3D trfScalecylinder22 = new Transform3D();
		trfScalecylinder22.setScale(v3DScalecylinder22);
		trfScalecylinder22.mul(trfPositioncylinder22); // Accumulating
		trgcylinder22.setTransform(trfScalecylinder22);

		// Rotation
		Matrix3d m3DRotationcylinder22 = new Matrix3d(0.39340158083564225, 0.0, 0.0,
												0.0, 2.390051175970323, 0.0,
												0.0, 0.0, 0.35517735181082244);
		trfPositioncylinder22.setRotation(m3DRotationcylinder22);

		// Apply the transforms
		trgcylinder22.setTransform(trfPositioncylinder22);

		// Add to BranchGroup
		brg.addChild(trgcylinder22);


		//
		// Object: cube24
		//

		// TransformGroup
 		TransformGroup trgcube24  = new TransformGroup ();
		trgcube24.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcube24 = new Color3f(0.4f, 0.4f, 0.4f);
		ColoringAttributes colorAttributescube24 = new ColoringAttributes();
		colorAttributescube24.setColor(corcube24);
		Appearance appearancecube24 = new Appearance();
		appearancecube24.setColoringAttributes(colorAttributescube24);

		// Declaration
		Box cube24 = new Box(0.1f, 0.1f,0.1f, Box.GENERATE_TEXTURE_COORDS, appearancecube24);
		trgcube24.addChild(cube24);

		// Position
		Vector3d v3DPositioncube24 = new Vector3d(0.0, 0.7772225548152496, -1.3183898417423734E-16);
		Transform3D trfPositioncube24 = new Transform3D();
		trfPositioncube24.set(v3DPositioncube24);

		// Scale
		Vector3d v3DScalecube24 = new Vector3d(4.776012152697794, 0.29689907228608087, 2.109174383542468);
		Transform3D trfScalecube24 = new Transform3D();
		trfScalecube24.setScale(v3DScalecube24);
		trfScalecube24.mul(trfPositioncube24); // Accumulating
		trgcube24.setTransform(trfScalecube24);

		// Rotation
		Matrix3d m3DRotationcube24 = new Matrix3d(4.776012152697794, 0.0, 0.0,
												0.0, 0.29689907228608087, 0.0,
												0.0, 0.0, 2.109174383542468);
		trfPositioncube24.setRotation(m3DRotationcube24);

		// Apply the transforms
		trgcube24.setTransform(trfPositioncube24);

		// Add to BranchGroup
		brg.addChild(trgcube24);


		//
		// Object: cube26
		//

		// TransformGroup
 		TransformGroup trgcube26  = new TransformGroup ();
		trgcube26.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcube26 = new Color3f(0.4f, 0.4f, 0.4f);
		ColoringAttributes colorAttributescube26 = new ColoringAttributes();
		colorAttributescube26.setColor(corcube26);
		Appearance appearancecube26 = new Appearance();
		appearancecube26.setColoringAttributes(colorAttributescube26);

		// Declaration
		Box cube26 = new Box(0.25f, 0.02f,0.1f, Box.GENERATE_TEXTURE_COORDS, appearancecube26);
		trgcube26.addChild(cube26);

		// Position
		Vector3d v3DPositioncube26 = new Vector3d(-0.23256167198500027, 0.8675837141828011, -6.938893903907228E-18);
		Transform3D trfPositioncube26 = new Transform3D();
		trfPositioncube26.set(v3DPositioncube26);

		// Scale
		Vector3d v3DScalecube26 = new Vector3d(1.0009855635450564, 1.033156779964035, 1.950503729296005);
		Transform3D trfScalecube26 = new Transform3D();
		trfScalecube26.setScale(v3DScalecube26);
		trfScalecube26.mul(trfPositioncube26); // Accumulating
		trgcube26.setTransform(trfScalecube26);

		// Rotation
		Matrix3d m3DRotationcube26 = new Matrix3d(0.9556792424388788, -0.30236958998724967, 0.0,
												0.29867505497665575, 0.9876376150027666, 0.0,
												0.0, 0.0, 1.950503729296005);
		trfPositioncube26.setRotation(m3DRotationcube26);

		// Apply the transforms
		trgcube26.setTransform(trfPositioncube26);

		// Add to BranchGroup
		brg.addChild(trgcube26);


		//
		// Object: cube28
		//

		// TransformGroup
 		TransformGroup trgcube28  = new TransformGroup ();
		trgcube28.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcube28 = new Color3f(0.4f, 0.4f, 0.4f);
		ColoringAttributes colorAttributescube28 = new ColoringAttributes();
		colorAttributescube28.setColor(corcube28);
		Appearance appearancecube28 = new Appearance();
		appearancecube28.setColoringAttributes(colorAttributescube28);

		// Declaration
		Box cube28 = new Box(0.25f, 0.02f,0.1f, Box.GENERATE_TEXTURE_COORDS, appearancecube28);
		trgcube28.addChild(cube28);

		// Position
		Vector3d v3DPositioncube28 = new Vector3d(0.23190485318112428, 0.8684976016106095, 0.005683788632702329);
		Transform3D trfPositioncube28 = new Transform3D();
		trfPositioncube28.set(v3DPositioncube28);

		// Scale
		Vector3d v3DScalecube28 = new Vector3d(1.0009855635450562, 1.950503729296005, 1.0331567799640342);
		Transform3D trfScalecube28 = new Transform3D();
		trfScalecube28.setScale(v3DScalecube28);
		trfScalecube28.mul(trfPositioncube28); // Accumulating
		trgcube28.setTransform(trfScalecube28);

		// Rotation
		Matrix3d m3DRotationcube28 = new Matrix3d(0.9593336259276861, 0.2904806961237625, 0.0070811525544081,
												-0.3115746414916834, 0.9836407629885948, -0.002634236170957309,
												-0.014580103589821291, 6.050513062294399E-4, 1.9504491412210287);
		trfPositioncube28.setRotation(m3DRotationcube28);

		// Apply the transforms
		trgcube28.setTransform(trfPositioncube28);

		// Add to BranchGroup
		brg.addChild(trgcube28);


		//
		// Object: cube30
		//

		// TransformGroup
 		TransformGroup trgcube30  = new TransformGroup ();
		trgcube30.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcube30 = new Color3f(0.4f, 0.4f, 0.4f);
		ColoringAttributes colorAttributescube30 = new ColoringAttributes();
		colorAttributescube30.setColor(corcube30);
		Appearance appearancecube30 = new Appearance();
		appearancecube30.setColoringAttributes(colorAttributescube30);

		// Declaration
		Box cube30 = new Box(0.1f, 0.1f,0.1f, Box.GENERATE_TEXTURE_COORDS, appearancecube30);
		trgcube30.addChild(cube30);

		// Position
		Vector3d v3DPositioncube30 = new Vector3d(0.0, 0.4004172584095885, -0.18702921118047694);
		Transform3D trfPositioncube30 = new Transform3D();
		trfPositioncube30.set(v3DPositioncube30);

		// Scale
		Vector3d v3DScalecube30 = new Vector3d(4.756942491027489, 3.7149651354775837, 0.2239792717171387);
		Transform3D trfScalecube30 = new Transform3D();
		trfScalecube30.setScale(v3DScalecube30);
		trfScalecube30.mul(trfPositioncube30); // Accumulating
		trgcube30.setTransform(trfScalecube30);

		// Rotation
		Matrix3d m3DRotationcube30 = new Matrix3d(4.756942491027489, 0.0, 0.0,
												0.0, 3.7149651354775837, 0.0,
												0.0, 0.0, 0.2239792717171387);
		trfPositioncube30.setRotation(m3DRotationcube30);

		// Apply the transforms
		trgcube30.setTransform(trfPositioncube30);

		// Add to BranchGroup
		brg.addChild(trgcube30);


		//
		// Object: cube32
		//

		// TransformGroup
 		TransformGroup trgcube32  = new TransformGroup ();
		trgcube32.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		// Appearance
		Color3f corcube32 = new Color3f(0.6f, 0.6f, 0.6f);
		ColoringAttributes colorAttributescube32 = new ColoringAttributes();
		colorAttributescube32.setColor(corcube32);
		Appearance appearancecube32 = new Appearance();
		appearancecube32.setColoringAttributes(colorAttributescube32);

		// Declaration
		Box cube32 = new Box(0.1f, 0.1f,0.1f, Box.GENERATE_TEXTURE_COORDS, appearancecube32);
		trgcube32.addChild(cube32);

		// Position
		Vector3d v3DPositioncube32 = new Vector3d(0.0, 0.07654075988155792, 0.0);
		Transform3D trfPositioncube32 = new Transform3D();
		trfPositioncube32.set(v3DPositioncube32);

		// Scale
		Vector3d v3DScalecube32 = new Vector3d(4.776012152697794, 0.29689907228608087, 2.109174383542468);
		Transform3D trfScalecube32 = new Transform3D();
		trfScalecube32.setScale(v3DScalecube32);
		trfScalecube32.mul(trfPositioncube32); // Accumulating
		trgcube32.setTransform(trfScalecube32);

		// Rotation
		Matrix3d m3DRotationcube32 = new Matrix3d(4.776012152697794, 0.0, 0.0,
												0.0, 0.29689907228608087, 0.0,
												0.0, 0.0, 2.109174383542468);
		trfPositioncube32.setRotation(m3DRotationcube32);

		// Apply the transforms
		trgcube32.setTransform(trfPositioncube32);

		// Add to BranchGroup
		brg.addChild(trgcube32);


		/*****************************************
					     Final Declarations       
		******************************************/

		// Background color
		Color3f bgColor= new Color3f(94,170,227);
		Background bg = new Background(bgColor);
		bg.setApplicationBounds(new BoundingSphere());
		brg.addChild(bg);

		// Compile the scene and create the universe
		brg.compile();
		SimpleUniverse universe = new SimpleUniverse (scene);
		universe.addBranchGraph(brg);
		universe.getViewingPlatform().setNominalViewingTransform();

 		// Behavior for visualization
		OrbitBehavior orbit = new OrbitBehavior();
		orbit.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0));
		ViewingPlatform view = universe.getViewingPlatform();
		view.setNominalViewingTransform();
		view.setViewPlatformBehavior(orbit);

 		// Create the window
		JFrame window = new JFrame ("Java3DEditor");
		window.setBounds(0,0,800,600);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(scene);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		new Pantenon();
	}
}