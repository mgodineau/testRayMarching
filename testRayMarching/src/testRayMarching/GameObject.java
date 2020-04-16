package testRayMarching;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class GameObject {
	
	public Vector3f position;
	public Quat4f rotation;
	public Vector3f scale;
	
	public List<GameObject> children;
	
	
	public Vector3f getForward() {
		return new Vector3f(0,0,1);
	}
	public Vector3f getRight() {
		return new Vector3f(1,0,0);
	}
	public Vector3f getUp() {
		return new Vector3f(0,1,0);
	}
	
	
	
	public GameObject() {
		position = new Vector3f(0, 0, 0);
		rotation = new Quat4f();
		scale = new Vector3f(1, 1, 1);
		
		children = new LinkedList<GameObject>();
	}
	
	public GameObject( Vector3f position, Quat4f rotation, Vector3f scale) {
		this.position = position; 
		this.rotation = rotation;
		this.scale = scale;
		
		children = new LinkedList<GameObject>();
	}
	
	
}
