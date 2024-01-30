import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Board {
	private int[][] board;
	private ImageIcon[] images;
	private static final int SCALE = 2;

	public Board() {
		//board = new int[10][10];
		board = new int[][]{
			{2, 7, 7, 7, 7, 1},
			{6, 0, 0, 0 , 0, 5},
			{6, 0, 16, 0 , 0, 5},
			{6, 0, 16, 0 , 0, 5},
			{6, 0, 0, 0 , 0, 5},
			{4, 8, 8, 8 , 8, 3}
		};
		images = new ImageIcon[37];
		
		ClassLoader cldr = this.getClass().getClassLoader();
		String imagePath; 
		ImageIcon image;
		
		for(int i = 0; i < images.length; i++) {
			imagePath = "images/walls/Wall_" + ((i < 10) ? "0" + (i) : (i)) + ".png";//
			
			URL imageURL = cldr.getResource(imagePath);	
			
			image = new ImageIcon(imageURL);	
			image.getImage();
			Image scaled = image.getImage().getScaledInstance(image.getIconWidth() * SCALE, 
					image.getIconHeight() * SCALE, image.getImage().SCALE_SMOOTH);
			images[i] = new ImageIcon(scaled);
		}
	}

	public int[][] getBoard(){
		return board;
	}
	
	// method: draw
	// description: This method is used to draw the image onto the GraphicsPanel.  You shouldn't need to 
	//				modify this method.
	// parameters: Graphics g - this object draw's the image.
	//			   Component c - this is the component that the image will be drawn onto.
	public void draw(Graphics g, Component c) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				ImageIcon image = images[board[col][row]];
				//System.out.println(image.getIconWidth());
				g2.drawImage(image.getImage(), row * image.getIconWidth(), 
						col * image.getIconHeight(), image.getIconWidth(), image.getIconHeight(), null);
				//g2.drawRect(row * image.getIconWidth(), col * image.getIconHeight(), image.getIconWidth(), image.getIconHeight());		
			}
		}
		
	}
}
