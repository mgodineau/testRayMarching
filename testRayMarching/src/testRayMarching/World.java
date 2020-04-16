package testRayMarching;

import javax.vecmath.Vector3f;

public class World {
	
	
	private Vector3f spherePos = new Vector3f(1,0,0);
	private float sphereRadius = 1.5f;
	
	public Camera mainCamera;
	
	
	public float distToObjects( Vector3f from ) {
		return (float) (Math.sqrt( 
			Math.pow(from.x-spherePos.x, 2) +
			Math.pow(from.y-spherePos.y, 2) +
			Math.pow(from.z-spherePos.z, 2))
		- sphereRadius);
	}
	
	
}
