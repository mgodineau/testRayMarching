package testRayMarching;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.vecmath.Quat4f;
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
				ray.scale(-1);
				Vector3f rayReflect = reflect(ray, normal);
				rayReflect.scale(-1);
				float angle = (float) rayReflect.angle(world.mainLight);
				float maxAngle = ((float)Math.PI) / 12.0f;
				float specularFactor = 0;
				if ( angle < maxAngle ) {
					specularFactor = 1.0f - angle / maxAngle;
				}
				
				Color diffuse = new Color(Math.min(1, lightFactor+specularFactor), specularFactor, specularFactor);
				renderImg.setRGB(x, y, diffuse.getRGB() );
				
			}//for
		}//for
	} 
	
	
	private Vector3f reflect( Vector3f v, Vector3f axis ) {
		Quat4f rotation = new Quat4f(axis.x, axis.y, axis.z, (float) Math.PI);
		Quat4f vQuat = new Quat4f(v.x, v.y, v.z, 0);
		vQuat.mul( vQuat, rotation );
		vQuat.mulInverse(rotation, vQuat);
		
		return new Vector3f( vQuat.x, vQuat.y, vQuat.z );
	}
	
	
	public Renderer() {
		renderImg = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
	}
	
}
