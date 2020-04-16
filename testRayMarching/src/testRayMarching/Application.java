package testRayMarching;

import javax.swing.JFrame;
import javax.vecmath.Vector3f;

public class Application {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);

		Renderer rend = new Renderer();
		RenderPanel pan = new RenderPanel(rend);
		frame.setContentPane(pan);
		
		
		World world = new World();
		world.mainCamera = new Camera();
		world.mainCamera.position = new Vector3f(0,0,-2);
		
		Sphere boule = new Sphere();
		boule.position = new Vector3f(1,0.1f,0);
		world.objToShow.add(boule);
		Sphere grosseBoule = new Sphere();
		grosseBoule.position = new Vector3f(-1,0.1f,0);
		grosseBoule.scale = new Vector3f(1.2f, 1.2f, 1.2f);
		world.objToShow.add(grosseBoule);
		
		
		rend.renderWorld(world);
		pan.revalidate();
		
	}

}
