public class Enemy extends Sprite implements Common {
 private Status status;

 public Enemy(int x, int y,int hp,int at) {
  super(x, y,hp,at);
  initEnemy();
 }

 private void initEnemy() {
  loadImage("enemy.png");
  getImageDimention();
  int rand = (int) (Math.random() * 2 + 1);
  switch (rand) {
  case 1:
   status = Status.UP;
   break;
  case 2:
   status = Status.DOWN;
   break;
  }
 }

 public void move() {
  if (y <= 0)
   status = Status.DOWN;
  if (y >= HEIGHT - height)
   status = Status.UP;

  switch (status) {
  case UP:
   y -= ENEMY_SPEED;
   break;
  case DOWN:
   y += (ENEMY_SPEED+MainBoard.hardGameSpeed);
   break;
  }
 }
}