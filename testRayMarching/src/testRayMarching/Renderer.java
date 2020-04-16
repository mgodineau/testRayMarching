package testRayMarching;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.vecmath.Vector3f;

public class Renderer {
	
	
	private BufferedImage renderImg;
	
	
	public BufferedImage getRenderImg() {
		return renderImg;
	}

	public void setRenderImg(BufferedImage renderImg) {
		this.renderImg = renderImg;
	}
	
	
	
	public void renderWorld( World world ) {
		
		float tanX = (float) Math.tan( world.mainCamera.getFov());
		float tanY = tanX / renderImg.getWidth() * renderImg.getHeight();
		
		Camera cam = world.mainCamera;
		
		for( int x=0; x<renderImg.getWidth(); x++ ) {
			for( int y=0; y<renderImg.getHeight(); y++ ) {
				float xLocal = 2.0f*x/renderImg.getWidth() - 1.0f;
				float yLocal = 2.0f*y/renderImg.getHeight() - 1.0f;
				
				
				Vector3f ray = world.mainCamera.getForward();
				
				Vector3f right = world.mainCamera.getRight();
				Vector3f up = world.mainCamera.getUp();
				right.scale(xLocal * tanX );
				up.scale(yLocal * tanY );
				ray.add(right);
				ray.add(up);
				ray.normalize();
				
				
				Vector3f hit = (Vector3f) world.mainCamera.position.clone();
				float worldDist = world.distToObjects(hit);
				float camDist = 0;
				
				while( worldDist > cam.getMinThreshold() && camDist < cam.getClipFar()) {
					Vector3f scaledRay = (Vector3f) ray.clone();
					scaledRay.scale(worldDist);
					hit.add(scaledRay);
					camDist += worldDist;
					worldDist = world.distToObjects(hit);
				}
				if ( camDist > 2 ) {
					camDist = 2;
				}
				
				Vector3f normal = world.getNormal(hit, cam.getMinThreshold());
				float lightFactor = normal.dot(world.mainLight);
				if (lightFactor < 0) {
					lightFactor = 0;
				}
				
				Color col = new Color(lightFactor, lightFactor, lightFactor);
				renderImg.setRGB(x, y, col.getRGB() );
				
			}//for
		}//for
	} 
	
	
	public Renderer() {
		renderImg = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
	}
	
}
