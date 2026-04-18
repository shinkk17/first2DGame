package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		//Posicion inicial del personaje (centro de la pantalla)
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		hitbox = new Rectangle();
		hitbox.x = 15;
		hitbox.y = 27;
		hitbox.width = 24;
		hitbox.height = 24;

		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = 464;
		worldY = 384;
		speed = 4;
		direction = "right";
	}
	
	public void getPlayerImage() {
		try {
			
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void update() {
		//Revisa la tecla que se toca para setear la direccion del jugador
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed) {
				direction = "up";

			} else if (keyH.downPressed) {
				direction = "down";

			} else if (keyH.leftPressed) {
				direction = "left";

			} else if (keyH.rightPressed) {
				direction = "right";
			}
			
			//Revisar collision con tile
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//Si la colision es false, el jugador se puede mover
			if (!collisionOn) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "right":
					worldX += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				}
			}
			spriteCounter++;

			if (spriteCounter > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}else if (spriteNum == 2){
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2){
				image = up2;
			}
			
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2){
				image = down2;
			}
			
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2){
				image = left2;
			}
			
			break;
		case "right":
			
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2){
				image = right2;
			}
			
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		g2.setColor(Color.red);
		g2.drawRect(screenX + hitbox.x, screenY + hitbox.y, hitbox.width, hitbox.height);
	}
}
