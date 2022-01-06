import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JPanel;
import java.util.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MainBoard extends JPanel implements ActionListener, Common {
 private ArrayList < Item > items;
 private ArrayList < Enemy > enemies;
 private Box box;
 private Timer timer;
 private boolean inGame;
 private int point;
 long XX=0;
 long YY=0;
 long Start = System.currentTimeMillis();   
 long x=1000;
 long x2=1000;
 long elapsedTimeMillis1 = System.currentTimeMillis()-Start;
 long elapsedTimeMillis2 = System.currentTimeMillis()-Start;
 long lol1 =0,lol2=0;
 int updateAT=0,updateBulletCount=0;
 long updatePointX,updatePointY;
 public static int hardGameSpeed=0,hardGameHP=0;
 long money=0;
 long kt=0;
 public MainBoard() {
  initBoard();
 
 }
 
 private void initBoard() {
	 point = 0;
	 XX=0;
	 YY=0;
	 x=1000;
	 x2=1000;
	 items= new ArrayList<Item>();
	 enemies= new ArrayList<Enemy>();
	 Start = System.currentTimeMillis();
	 elapsedTimeMillis1 = System.currentTimeMillis()-Start;
	 elapsedTimeMillis2 = System.currentTimeMillis()-Start;
	 lol1 =0;lol2=0;
	  hardGameSpeed=0;hardGameHP=0;
	  inGame = true;
	  point = 0;
	  addMouseMotionListener(new TAdapter());
	  addMouseListener(new CustomMouseListener());
	  setFocusable(true);
	  setBackground(Color.DARK_GRAY);
	  setDoubleBuffered(true);
	  box = new Box(INIT_BOX_X, INIT_BOX_Y,MY_HP,BULLET_AT);
	  enemies = new ArrayList < Enemy >();
	  items = new ArrayList <Item >();
	  initEnemy();
	  initItem();
	  inGame = true;
	  timer = new Timer(DELAY, this);
	  timer.start();
	 }
 private void initEnemy() {
	  int initX = (int) (Math.random() * (Common.WIDTH - 50));
	  int initY = Common.HEIGHT-800;
	  Enemy enemy = new Enemy(initX, initY,ENEMY_HP+hardGameHP,ENEMY_HP+hardGameHP);
	  enemies.add(enemy);
	 }
 private void initItem() {
	  int initX = (int)box.getX()+20;
	  int initY = (int) box.getY();
	  int GG = (updateAT*(updateAT+1)/2);
	  Item item = new Item(initX, initY,BULLET_AT+GG,BULLET_AT+GG);
	  items.add(item);
	 }
 @Override
 protected void paintComponent(Graphics g) {
  super.paintComponent(g);
  if (inGame) {
	  
   drawObject(g);
  } else {
   drawGameOver(g);
  }
 }
 private void drawObject(Graphics g) {
	  // Draw Box
	  g.drawImage(box.getImage(), box.getX(), box.getY(), this);
	  // Draw Enemies
	  for (Enemy enemy : enemies) {
	   g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
	  }
	  // Draw Item
	  for (Item item : items) {
	   g.drawImage(item.getImage(), item.getX(), item.getY(), this);
	  }
	  
	  // Draw Point
	  Font font = new Font("Arial", Font.BOLD, 16);
	  g.setColor(Color.GREEN);
	  g.setFont(font);
	  g.drawString("HP: " + box.gethp(), 150 , 548);
	  long XDD=BULLET_AT+updateAT;
	  g.drawString("AT: " + XDD,30, 548);
	  g.drawString("point "+ point,5, 25);
	  g.drawString("$ "+ money,100, 25);
	  long SLD=updateBulletCount+1;
	  g.drawString("SL dan: "+ SLD,253, 548);
	 }
 private void drawGameOver(Graphics g) {
	 repaint();
	  Sprite UpdateAT = new Sprite(1,1,1,1) ;
	  Sprite UpdateBLC = new Sprite(1,1,1,1) ;
	  Sprite tieptuc = new Sprite(1,1,1,1) ;
	  tieptuc.loadImage("continue.PNG");
	  UpdateAT .loadImage("updateAT.PNG");
	  UpdateBLC.loadImage("updateBulletCount.PNG");
	  Font font = new Font("Helvetica", Font.BOLD, 20);
	  g.setColor(Color.BLACK);
	  g.setFont(font);
	  g.drawString("Game over!", Common.WIDTH / 2-70, Common.HEIGHT / 2-50);
	  g.drawString("Score: " + point, Common.WIDTH / 2-70, Common.HEIGHT / 2 + 30-50);
	  g.drawImage(tieptuc.getImage(), Common.WIDTH / 2-70, (Common.HEIGHT / 2),this);
	  g.drawImage(UpdateAT.getImage(), Common.WIDTH / 2-70, (Common.HEIGHT / 2)+30,this);
	  
	  g.drawString(( (updateAT+1)*(updateAT+2))/2+" $",Common.WIDTH / 2-70+150, (Common.HEIGHT / 2)+50);
	  g.drawImage(UpdateBLC .getImage(), Common.WIDTH / 2-70, (Common.HEIGHT / 2)+60,this);
	  
	  if (updateBulletCount==0) g.drawString("20 $",Common.WIDTH / 2-70+150,  (Common.HEIGHT / 2)+80);
	  else
	  if (updateBulletCount==1) g.drawString("500 $",Common.WIDTH / 2-70+150,  (Common.HEIGHT / 2)+80);
	  else
	  if (updateBulletCount==2) g.drawString("MAX" ,Common.WIDTH / 2-70+150,  (Common.HEIGHT / 2)+80);
	  long XDD=BULLET_AT+updateAT;
	  g.drawString("AT: " + XDD,30, 548);
	  g.drawString("point "+ point,5, 25);
	  g.drawString("$ "+ money,100, 25);
	  long SLD=updateBulletCount+1;
	  g.drawString("SL dan: "+ SLD,253, 548);
	 }
 @Override
 public void actionPerformed(ActionEvent e) {
  inGame();
  
  // Update Object
  updateBox();
  updateEnemy(); updateItem();
  
  if ( elapsedTimeMillis1 > x) {
  initEnemy();
  x+=(300);
  lol1 ++;
  }
  if ( elapsedTimeMillis2 > x2) {
  initItem();
  x2+=(200);
  lol2 ++;
  if (updateBulletCount>=1 ) {
  int initX = (int)box.getX()+75;
  int initY = (int) box.getY();
  int GG = (updateAT*(updateAT+1)/2);
  Item item = new Item(initX, initY,BULLET_AT+GG,BULLET_AT+GG);
  items.add(item);
  }
  if (updateBulletCount>=2 ) {
	  int initX = (int)box.getX()+48;
	  int initY = (int) box.getY();
	  int GG = (updateAT*(updateAT+1)/2);
	  Item item = new Item(initX, initY,BULLET_AT+GG,BULLET_AT+GG);
	  items.add(item);
	  }
  }
  // Check collision
  checkCollision();
  // Repaint
  repaint();
 }
 private void inGame() {
	  if (!inGame) {
	   timer.stop();
	  // initBoard();
	  }
	 }
 private void updateBox() {
	  box.move();
	 }
 private void updateEnemy() {
	  for (int i = 0; i < enemies.size(); i++) {
		  enemies.get(i).move();	
				  if (enemies.get(i).getY()>=500) {
					  enemies.remove(i);
					  box.sethp(box.gethp()-enemies.get(i).gethp());
					  if (box.gethp() <=0) {inGame=false;return;}
				  }
	  }
	 }
 private void updateItem() {
	 for(int i = 0 ; i < items.size(); i++) {
	 items.get(i).move();
	 if (items.get(i).getY() <= 0)
		 items.remove(i);
	 else
		  if (items.get(i).getY()>= 600)
			  items.remove(i);
	 }
 }
 public void checkCollision() {
	 
	 elapsedTimeMillis1 = System.currentTimeMillis()-Start;
	 elapsedTimeMillis2 = System.currentTimeMillis()-Start;
	 long T=enemies.size();
	 for(int i = 0 ; i < enemies.size(); i++)
	  if(box.getBound().intersects(enemies.get(i).getBound())) {
		  box.sethp(box.gethp()-1);
		  if (box.gethp() <=0) {inGame=false;return;}
		  enemies.remove(i);
		  money ++;
		  point ++;
		  T= enemies.size();
		  i=0;
	  }
	  T= enemies.size();
	 long  TT= items.size();
	 for(int j=0 ; j < enemies.size(); j++)
	 for(int i = 0 ; i < items.size(); i++) {
		 if (j >= enemies.size() || i >= items.size()) break;
		  if(enemies.get(j).getBound().intersects(items.get(i).getBound())) {
			  
		  if (!enemies.isEmpty())  {
			  enemies.get(j).sethp(enemies.get(j).gethp()-items.get(i).gethp());
			  
			  if (enemies.get(j).gethp() <=0) {
			  enemies.remove(j);
			  T= enemies.size();
			  point ++;
			  money ++;
			  }
			  }
		  if (!items.isEmpty()) {
			  items.get(i).sethp(items.get(i).gethp()-enemies.get(j).gethp());
			  if (items.get(i).gethp() <=0) {
			  items.remove(i);
			  TT= items.size();
			  }
			  }

		  
		  
		  }
		  
	 }
	 hardGameSpeed=point/300;
	 hardGameHP=point/10;
 }
 private class TAdapter extends MouseMotionAdapter{
	  @Override
	  public void mouseMoved(MouseEvent e) {
	   box.mouseMoved(e);
	  }
	 }
 private class CustomMouseListener implements MouseListener{
     public void mouseClicked(MouseEvent e) {  		
    	 if (!inGame) {
    		kt++; 
    	 
        if (e.getX() >= 230 && e.getX()<= 355)
        	if (e.getY() >= 300 && e.getY() <= 325)
        		 {
        		initBoard();
        		}
        if (e.getX() >= 230 && e.getX()<= 355)
        	if (e.getY() >= 330 && e.getY() <= 355)
        		if (money >= ((updateAT+1)*(updateAT+2))/2 && kt >0) {
        		updateAT++;
        		money-= (updateAT*(updateAT+1)/2);
        		kt--;
        		}
        if (updateBulletCount ==0 ) 
        if (e.getX() >= 230 && e.getX()<= 355)
        	if (e.getY() >= 360 && e.getY() <= 385) 
        			if (money >= 20) {
        		updateBulletCount++;
        			money-=20;
        			}
        if (updateBulletCount ==1 ) 
            if (e.getX() >= 230 && e.getX()<= 355)
            	if (e.getY() >= 360 && e.getY() <= 385) 
        			if (money >= 500) {
        				updateBulletCount++;
        			money-=200;
        			}
    	 }
        	
     }

     public void mousePressed(MouseEvent e) {
     }

     public void mouseReleased(MouseEvent e) {
     }

     public void mouseEntered(MouseEvent e) {
     }

     public void mouseExited(MouseEvent e) {
     }
 }
}