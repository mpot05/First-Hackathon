// Class: GraphicsPanel
// Written by: Mr. Swope
// Date: 1/27/2020
// Description: This class is the main class for this project.  It extends the Jpanel class and will be drawn on
// 				on the JPanel in the GraphicsMain class.  
//
// Since you will modify this class you should add comments that describe when and how you modified the class.  

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;





public class GraphicsPanel extends JPanel implements KeyListener {

	private Timer timer; // The timer is used to move objects at a consistent time interval.

	private PacMan pacman; // create a Sprite object

	private ArrayList<Item> fruit;
	
	private ArrayList<Item> enimes; 
	
	private ArrayList<Item> special;
	
	private int fcounter = 0;
	
	private int frespawnTimer = 500;
	
	private int ecounter = 0;
	
	private int erespawnTimer = 250;
	
	private static int score = 0;
	
	private int highScore = 0;
	
	private int lives = 0; 
	
	private int cnum = 0;
	
	private int stim = 0;
	
	private int flash = 0;
	
	private boolean cheatcode;
	
	private boolean check = true;
	
	private boolean menu;
	
	private boolean opmenu;
	
	private boolean dmenu;
	
	private static int optionsNum = 1;
	
	

	public GraphicsPanel() {
		// The PacMan constuctor has two parameter - - the x coordinate and y coordinate
		pacman = new PacMan(400, 650);
		
		fruit = new ArrayList<>();
		enimes = new ArrayList<>();
		special = new ArrayList<>();
		

		setPreferredSize(new Dimension(800, 700));
		// This line of code sets the dimension of the panel equal to the dimensions
		// of the background image.

		timer = new Timer(5, new ClockListener(this)); // This object will call the ClockListener's
		// action performed method every 5 milliseconds once the
		// timer is started. You can change how frequently this
		// method is called by changing the first parameter.
		timer.start();
		this.setFocusable(true); // for keylistener
		this.addKeyListener(this);
	}

	// method: paintComponent
	// description: This method will paint the items onto the graphics panel. This
	// method is called when the panel is
	// first rendered. It can also be called by this.repaint(). You'll want to draw
	// each of your objects.
	// This is the only place that you can draw objects.
	// parameters: Graphics g - This object is used to draw your images onto the
	// graphics panel.
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3 = (Graphics2D) g;
		Graphics2D g4 = (Graphics2D) g;
		Graphics2D b1 = (Graphics2D) g;
		Graphics2D b2 = (Graphics2D) g;
		Graphics2D b3 = (Graphics2D) g;
		
		Item m1 = new Item(-500,-500,"images/objects/Node.png");
		Item m2 = new Item(-500,-500,"images/objects/Node.png");
	
		
		b1.setColor(Color.white);
		b2.setColor(Color.white);
		b3.setColor(Color.white);
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, 800, 800);

		pacman.draw(g2, this);

		for (Item f : fruit) {
			f.draw(g2, this);
		}
		
		for (Item e : enimes) {
			e.draw(g2, this);
		}
		
		for (Item s : special) {
			s.draw(g2, this);
		}
		
		
		
		
		if (score > highScore)
			highScore = score;
		
		int fontSize = 30;
		g3.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g3.setColor(Color.white);
		
		
//		if(lives<=0) {
//			g3.drawString("Lives: "+"You died!", 590, 30);
//			g3.drawString("Press \"R\" to reset.", 300 , 400);
//			
//		}
//		else
		if(lives>0){
			g3.drawString("Lives: "+lives, 690, 30);
			g3.drawString("Score: "+score, 5, 30);
		}
		
		if(stim>0) {
			flash++;
			if(flash%40<=10) {
				g4.drawString("POWERUP", 350, 100);
			}
		}
		if(opmenu) {
			g3.drawString("HIGHSCORE", 325, 200);
			if(highScore==0) {
				g3.drawString("SCORE:" + " No high score", 276, 250);

			}
			else {
				g3.drawString("SCORE:" + " " + highScore, 337, 250);
			}
		
		}
		if(dmenu) {
			g3.drawString("Easy", 362, 200);
			g3.drawString("Normal", 347, 300);
			g3.drawString("Hard", 362, 400);
			
			if(optionsNum==1) {
				m1.setPos(250, 150);
				m1.draw(g2, this);
				m2.setPos(465, 150);
				m2.draw(g2, this);
			}
			else if(optionsNum==2) {
				m1.setPos(250, 250);
				m1.draw(g2, this);
				m2.setPos(465, 250);
				m2.draw(g2, this);
			}
			else if(optionsNum==3) {
				m1.setPos(250, 350);
				m1.draw(g2, this);
				m2.setPos(465, 350);
				m2.draw(g2, this);
			}
			
		}
		if(lives<=0 && menu && !opmenu && !dmenu) {
			b1.drawString("PLAY", 350, 200);
			b2.drawString("OPTIONS", 330, 300);
			b3.drawString("QUIT", 350, 400);
			
			if(optionsNum==1) {
				m1.setPos(250, 150);
				m1.draw(g2, this);
				m2.setPos(465, 150);
				m2.draw(g2, this);
			}
			else if(optionsNum==2) {
				m1.setPos(250, 250);
				m1.draw(g2, this);
				m2.setPos(465, 250);
				m2.draw(g2, this);
			}
			else if(optionsNum==3) {
				m1.setPos(250, 350);
				m1.draw(g2, this);
				m2.setPos(465, 350);
				m2.draw(g2, this);
			}
			
		
		}
		
	}

	// method:clock
	// description: This method is called by the clocklistener every 5 milliseconds.
	// You should update the coordinates
	// of one of your characters in this method so that it moves as time changes.
	// After you update the
	// coordinates you should repaint the panel.
	public void clock() {
		
		ecounter++;
		fcounter++;
		stim--;
		// You can move any of your objects by calling their move methods.
		pacman.move(this);

		// move each fruit
		for (Item f : fruit) {
			f.move(this);
			;
		}
		
		for (Item e : enimes) {
			e.move(this);
			;
		}
		
		for (Item s : special) {
			s.move(this);
			;
		}
		
		
		

		// remove the fruit if it reaches the bottom of the screen
		
		for (int i = fruit.size() - 1; i >= 0; i--) {
			if (fruit.get(i).getY() > 800) {
//				fruit.get(i).setPos(-500, -500);
				fruit.remove(i);
			}
				
//			if (fruit.get(i).getY() == pacman.getY() && fruit.get(i).getX() == pacman.getX())
//				fruit.remove(i);
			if(pacman.collision(fruit.get(i))) {
				fruit.remove(i);
				score += 10;
			}
		}
		
		for (int q = special.size() - 1; q >= 0; q--) {
			if (special.get(q).getY() > 800) {
//				special.get(q).setPos(-500, -500);
				special.remove(q);
			}
				
//			if (fruit.get(i).getY() == pacman.getY() && fruit.get(i).getX() == pacman.getX())
//				fruit.remove(i);
			if(pacman.collision(special.get(q))) {
				special.remove(q);
				check=true;
				stim=2000;
			}
		}
			
		for (int j = enimes.size() - 1; j >= 0; j--) {
			if (enimes.get(j).getY() > 800) {
//				enimes.get(j).setPos(-500, -500);
				enimes.remove(j);
			}
			
			if(stim>0) {
				if (pacman.collision(enimes.get(j))) {
					enimes.remove(j);
					score+=20;
				}
			}
			else {
				if (pacman.collision(enimes.get(j))) {
					lives--;
					enimes.remove(j);
				}
			}
			
		}
		if(lives==0) {
			pacman.die();
			cnum++;
			if(cnum==50) {
			pacman.stop();
			lives--;
			}
		}
		if(lives<=0) {
			pacman.removePac();
			menu=true;
		}
		
//		if(score>=200) {
//			frespawnTimer=400;
//			erespawnTimer=175;
//		}
//		else {
//			frespawnTimer=500;
//			erespawnTimer=250;
//		}
		
		
		if((score%150==0 && score != 0 && check) || cheatcode) {
			special.add(new Item((int)(800 * Math.random() + 1),0,"images/objects/Fruit_Melon.png"));
			cheatcode=false;
			check = false;
		}
		if(stim==0) {
			check=true;
		}
		
		
		
		
		// You can also check to see if two objects intersect like this. In this case if
		// the sprite collides with the
		// item, the item will get smaller.
		// if(pacman.collision(item) && pacman.getY() < item.getY()) {
		// System.out.println("stop");
		// pacman.stop_Vertical();
		// }
		if(lives>0) {
		if(fcounter % frespawnTimer == 0) {
			fruit.add(new Item((int)(800 * Math.random() + 1), 0, "images/objects/Fruit_Apple.png"));
		}
		
		if(ecounter % erespawnTimer == 0) {
			enimes.add(new Item((int)(800 * Math.random() + 1), 0, "images/ghosts/Ghost_Vulnerable_Blue_01.png"));
		}
		}
		
		if(fcounter== Integer.MAX_VALUE)
			fcounter=0;
		if(ecounter== Integer.MAX_VALUE)
			ecounter=0;
		
		this.repaint();
	}

	// method: keyPressed()
	// description: This method is called when a key is pressed. You can determine
	// which key is pressed using the
	// KeyEvent object. For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would
	// test to see if
	// the left key was pressed.
	// parameters: KeyEvent e
	@Override
	public void keyPressed(KeyEvent e) {

//		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
//			pacman.moveRight();
//		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
//			pacman.moveLeft();
//		else if (e.getKeyCode() == KeyEvent.VK_UP)
//			pacman.moveUp();
//		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
//			pacman.moveDown();
		
		
		if(lives>0) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			pacman.moveRight();
			pacman.stop_Vertical();
		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			pacman.moveLeft();
			pacman.stop_Vertical();
		}
		else if (e.getKeyCode() == KeyEvent.VK_W) {
			pacman.moveUp();
			pacman.stop_Horizontal();
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			pacman.moveDown();
			pacman.stop_Horizontal();
		}
//		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//			pacman.jump();
//		}
		}
//		if (e.getKeyCode() == KeyEvent.VK_R) {
//			pacman.resurrect();
//			pacman.resetPos();
//			pacman.moveDown();
//			lives=3;
//			score=0;
//			stim=0;
//			for(int c = fruit.size()-1; c>=0;c--) {
//				fruit.remove(c);
//			}
//			for(int f = enimes.size()-1; f>=0;f--) {
//				enimes.remove(f);
//			}
//		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			cheatcode=true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if(menu) {
				if(optionsNum>1) {
					optionsNum--;
				}
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(menu) {
				if (optionsNum < 3)
					optionsNum++;
				
			}
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(optionsNum==1) {
				
				if(dmenu) {
					frespawnTimer=500;
					erespawnTimer=250;
					pacman.resurrect();
					pacman.resetPos();
					pacman.moveDown();
					lives=3;
					score=0;
					stim=0;
					for(int c = fruit.size()-1; c>=0;c--) {
						fruit.remove(c);
					}
					for(int f = enimes.size()-1; f>=0;f--) {
						enimes.remove(f);
					}
					dmenu=false;
					menu=false;
					opmenu=false;
				}
				if(menu) {
					dmenu=true;
					menu=false;
					
				}
			}
			else if(optionsNum==2) {
				if(dmenu) {
					frespawnTimer=500;
					erespawnTimer=200;
					pacman.resurrect();
					pacman.resetPos();
					pacman.moveDown();
					lives=3;
					score=0;
					stim=0;
					for(int c = fruit.size()-1; c>=0;c--) {
						fruit.remove(c);
					}
					for(int f = enimes.size()-1; f>=0;f--) {
						enimes.remove(f);
					}
					dmenu=false;
					menu=false;
					opmenu=false;
				}
				if(menu) {
					menu=false;
					opmenu=true;
				}
				
			}
			else if(optionsNum==3) {
				if(dmenu) {
					frespawnTimer=500;
					erespawnTimer=150;
					pacman.resurrect();
					pacman.resetPos();
					pacman.moveDown();
					lives=3;
					score=0;
					stim=0;
					for(int c = fruit.size()-1; c>=0;c--) {
						fruit.remove(c);
					}
					for(int f = enimes.size()-1; f>=0;f--) {
						enimes.remove(f);
					}
					dmenu=false;
					menu=false;
					opmenu=false;
				}
				if(menu) {
					System.exit(0);
				}
			}
		}
		
		
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(opmenu) {
				opmenu=false;
				menu=true;
			}
		}
		
		
	}

	// method: keyTyped()
	// description: This method is called when a key is pressed and released. It
	// basically combines the keyPressed and
	// keyReleased functions. You can determine which key is typed using the
	// KeyEvent object.
	// For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would test to see if the
	// left key was typed.
	// You probably don't want to do much in this method, but instead want to
	//) implement the keyPresses and keyReleased methods.
	// parameters: KeyEvent e
	@Override
	public void keyTyped(KeyEvent e) {
		
		

	}

	// method: keyReleased()
	// description: This method is called when a key is released. You can determine
	// which key is released using the
	// KeyEvent object. For example if(e.getKeyCode() == KeyEvent.VK_LEFT) would
	// test to see if
	// the left key was pressed.
	// parameters: KeyEvent e
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
