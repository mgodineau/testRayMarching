package testRayMarching;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Vector3f;

public class World {
	
	
	public Camera mainCamera;
	public Vector3f mainLight;
	
	
	public List<VisibleGameObject> objToShow;
	
	
	public float distToObjects( Vector3f from ) {
		float dist = Float.MAX_VALUE;
		for ( VisibleGameObject obj : objToShow ) {
//			dist = Math.min( dist, obj.getDist(from) );
			dist = smoothMin( dist, obj.getDist(from), 0.5f );
		}
		return dist;
	}
	
	public Vector3f getNormal( Vector3f at, float threshold ) {
//		Vector3f normal = new Vector3f();
//		for ( VisibleGameObject obj : objToShow ) {
//			normal.add(obj.getDerivative(at));
//		}
//		normal.normalize();
//		return normal;
		
		// /!\ USINE A GAZ /!\
		Vector3f forward = new Vector3f(0, 0, threshold);
		Vector3f up = new Vector3f(0, threshold, 0);
		Vector3f right = new Vector3f(threshold, 0, 0);
		
		forward.add(at);
		up.add(at);
		right.add(at);
		
		float distRef = distToObjects(at);
		float distForward = distToObjects(forward);
		float distUp = distToObjects(up);
		float distRight = distToObjects(right); 
		
		Vector3f normal = new Vector3f( distRight - distRef, distUp - distRef, distForward-distRef );
//		normal.scale(1.0f / threshold);
		normal.normalize();
		return normal;
	}
	
	public World() {
		mainLight = new Vector3f(-1, 1, 0);
		mainLight.normalize();
		
		objToShow = new LinkedList<VisibleGameObject>();
	}
	
	
	private float smoothMin( float f1, float f2, float k ) {
		float h = Math.max(k-Math.abs(f1-f2), 0) / k;
		return Math.min(f1, f2) - h*h*h*k / 6.0f;
	}
	
}
