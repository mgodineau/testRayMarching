package testRayMarching;

public class Camera extends GameObject {
	
	
	private float fov;
	private float clipFar;
	private float minThreshold;
	
	
	//GETTERS - SETTERS
	public float getFov() {
		return fov;
	}
	
	public void setFov(float fov) {
		this.fov = Math.max(fov, 0);
	}
	
	public float getClipFar() {
		return clipFar;
	}
	
	public void setClipFar(float clipFar) {
		this.clipFar = Math.max(clipFar, 0);
	}
	
	
	public float getMinThreshold() {
		return minThreshold;
	}

	public void setMinThreshold(float minThreshold) {
		this.minThreshold = Math.max(minThreshold, 0);
	}
	
	
	
	public Camera() {
		this(90);
	}
	
	public Camera( float fov ) {
		super();
		this.setFov(fov);
		clipFar = 100;
		minThreshold = 0.01f;
	}


	
}
