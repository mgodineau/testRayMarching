package testRayMarching;

public class Camera extends GameObject {
	
	
	public float fov;
	
	
	
	
	public Camera() {
		this(90);
	}
	
	public Camera( float fov ) {
		super();
		this.fov = fov; 
	}
	
}
