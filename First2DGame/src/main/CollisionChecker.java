package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {

		// Ubicacion en el mapa de las hitbox de la entidad
		int entityLeftWorldX = entity.worldX + entity.hitbox.x;
		int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
		int entityTopWorldY = entity.worldY + entity.hitbox.y;
		int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

		// Columnas de la hitbox de la entidad
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;

		// Filas de la hitbox de la entidad
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			// Pregunta si el tile al que se esta queriendo caminar es solido o no, desde
			// ambox extremos de la hitbox
			if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

			// Pregunta si el tile al que se esta queriendo caminar es solido o no, desde
			// ambox extremos de la hitbox
			if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
				entity.collisionOn = true;
			}

			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			// Pregunta si el tile al que se esta queriendo caminar es solido o no, desde
			// ambox extremos de la hitbox
			if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			// Pregunta si el tile al que se esta queriendo caminar es solido o no, desde
			// ambox extremos de la hitbox
			if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
}
