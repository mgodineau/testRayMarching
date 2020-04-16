package testRayMarching;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public abstract class VisibleGameObject extends GameObject {
	
	
	public abstract float getDist( Vector3f from );
	
	public abstract Vector3f getDerivative( Vector3f at );
	
	
	
	public VisibleGameObject() {
		super();
	}
	
	public VisibleGameObject( Vector3f position, Quat4f rotation, Vector3f scale ) {
		super(position, rotation, scale);
	}
	
	protected Vector3f WorldToLocal( Vector3f world ) {
		Vector3f local = (Vector3f) world.clone();
		//POSITION
		local.sub(position);
		
		//ROTATION - TODO
		
		//SCALE
		local.x /= scale.x;
		local.y /= scale.y;
		local.z /= scale.z;
		
		return local;
	}
	
}
