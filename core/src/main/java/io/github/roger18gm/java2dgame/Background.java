package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture dirtTile, grassTile;
    private int tileSize = 54;

//     Draws each tile on the screen in the form of a grid
    private int[][] tileGrid = {
        {1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1 },
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },

};

    public Background(){
//        Initializes the tiles
        grassTile = new Texture("tiles/PNG/Tiles/grass-a.png");
        dirtTile = new Texture("tiles/PNG/Tiles/dirt-a.png");
    }
     public void render(SpriteBatch batch){
        for (int row = 0; row < tileGrid.length; row++){
            for (int col = 0; col < tileGrid[row].length; col++){

                int x = col * tileSize;
                int y = (tileGrid.length - 1 - row) * tileSize;

                // Determines which tile corresponds with each number in the grid
                if (tileGrid[row][col] == 1){
                    // tilesize, tilesize, sets the dimensions to be different than the origional
                    batch.draw(grassTile, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 2){
                    batch.draw(dirtTile, x, y, tileSize, tileSize);
                }
            }
        }
    }

    public void dispose(){
        grassTile.dispose();
        dirtTile.dispose();
    }
}
