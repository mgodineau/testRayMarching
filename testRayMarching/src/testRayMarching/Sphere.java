package testRayMarching;

import javax.vecmath.Vector3f;

public class Sphere extends VisibleGameObject {
	
	
	
	
	@Override
	public float getDist(Vector3f from) {
		Vector3f local = WorldToLocal(from);
		return local.length() - 1;
	}

	@Override
	public Vector3f getDerivative(Vector3f at) {
		Vector3f derivative = WorldToLocal(at);
		derivative.scale(1.0f / derivative.length());
		
		//SCALE
		derivative.x *= scale.x;
		derivative.z *= scale.y;
		derivative.y *= scale.z;
		
		//ROTATION - TODO
		
		return derivative;
	}
	

}
