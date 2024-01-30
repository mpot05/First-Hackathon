import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageResource {

	private ImageIcon image;			// The ImageIcon will be used to hold the Character's png.
	private int imageCount;

	// These two variables are used so that the image doesn't refresh every time the the panel is redrawn.
	// Without these variables the images would change much too quickly.
	private int imageRefreshCounter = 0;
	private static final int IMAGE_REFRESH_MAX = 10;

	private static final int SCALE = 3;

	private ImageIcon[] runningImages;
	private ImageIcon[] deathImages;
	
	public ImageResource(String imagePath) {
		runningImages = new ImageIcon[3];
		deathImages = new ImageIcon[11];
		imageCount = 0;
		
		loadImages((imagePath + "Pacman_"), runningImages);
		loadImages((imagePath + "Pacman_Death_"), deathImages);
		image = runningImages[imageCount];
	}

	private void loadImages(String imagePath, ImageIcon[] images) {

		ClassLoader cldr = this.getClass().getClassLoader();
		String newImagePath; 
		
		for(int i = 0; i < images.length; i++) {
			newImagePath = imagePath + ((i < 9) ? "0" + (i+1) : (i+1)) + ".png";//
			URL imageURL = cldr.getResource(newImagePath);	
			
			image = new ImageIcon(imageURL);	
			image.getImage();
			Image scaled = image.getImage().getScaledInstance(image.getIconWidth() * SCALE, 
					image.getIconHeight() * SCALE, image.getImage().SCALE_SMOOTH);
			images[i] = new ImageIcon(scaled);
		}
	}

	public void updateImage(int x_direction, boolean isDead) {

		imageRefreshCounter++;

		if(!isDead && imageRefreshCounter >= IMAGE_REFRESH_MAX && imageCount < 2) {
			imageCount++;
			imageRefreshCounter = 0;
			image = runningImages[imageCount];
		}	
		else if(!isDead && imageRefreshCounter >= IMAGE_REFRESH_MAX) {
			imageCount = 0;
			imageRefreshCounter = 0;
			image = runningImages[imageCount];
		}
		else if(isDead && imageRefreshCounter >= IMAGE_REFRESH_MAX && imageCount < 10) {
			imageCount++;
			imageRefreshCounter = 0;
			image = deathImages[imageCount];
		}	
		else if(isDead && imageRefreshCounter >= IMAGE_REFRESH_MAX) {
			imageCount = 0;
			imageRefreshCounter = 0;
			image = deathImages[imageCount];
		}

	}

	public void resetImageCounter() {
		imageCount = 0;
	}
	
	public ImageIcon getImage() {
		return image;
	}
}
