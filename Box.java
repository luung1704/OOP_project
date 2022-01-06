import java.awt.*;
import java.awt.event.*;

public class Box extends Sprite {
 private int dx;
 private int dy;
 private int XX;
 private int YY;
 public Box(int x, int y,int hp, int at) {
  super(x, y,hp,at);
  initBox();
 }

 private void initBox() {
  loadImage("box.png");
  getImageDimention();
 }
 
 public void move() {
  x = dx;
  y = Math.min(dy,440);
 }
 
 public void mouseMoved(MouseEvent e) {
  dx = e.getX()-50;
  dy = e.getY()-50;
 }
 public void mouseClicked(MouseEvent e) {
	XX=e.getX();
	YY=e.getY();
 }
 public int getXX() {
	  return XX;
	 }
	 
 public int getYY(){
	  return YY;
	 }
	 
 
}