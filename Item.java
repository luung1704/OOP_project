

public class Item extends Sprite implements Common{

	public Item(int x, int y,int hp,int at) {
		  super(x, y,hp,at);
		  initItem();
	 }


 private void initItem() {
  loadImage("item.png");
  getImageDimention();
 }
 public void move() {
	  y-=BULLET_SPEED;
	  }
}