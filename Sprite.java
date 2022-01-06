import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
 int x;
 int y;
 int hp;
 int at;
 int width;
 int height;
 protected boolean visible;
 protected Image image;
 
public Sprite(int x, int y,int hp, int at) {
	 super();
  this.x = x;
  this.y = y;
  visible = true;
  this.hp=hp;
  this.at=at;
 }
 
 public void loadImage(String fileName) {
  ImageIcon ii = new ImageIcon(fileName);
  image = ii.getImage();
 }
 public void getImageDimention() {
  width = image.getWidth(null);
  height = image.getHeight(null);
 }
 
 public boolean isVisible() {
  return visible;
 }

 public void setVisible(boolean visible) {
  this.visible = visible;
 }

 public int getX() {
  return x;
 }

 public int getY() {
  return y;
 }
public void sethp(int hp) {
	this.hp=hp;
}
public int gethp() {
	return this.hp;
}
 public Image getImage() {
  return image;
 }
 public int getWidth() {
	  return width;
	 }
 public int getHeight() {
	  return height;
	 }
 public Rectangle getBound() {
  return new Rectangle(x, y, width, height);
 }
}