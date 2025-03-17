package io.github.roger18gm.java2dgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture dirtTileA, dirtTileB, grassTileA, grassTileB, mudTileA, mudTileB, buildingTileA,
        stoneTileTopLeft, stoneTileTop, stoneTileTopRight, stoneTileLeft, stoneTileBotLeft, stoneTileBot,
        stoneTileBotRight, stoneTileRight, stoneTileCenter, longHedge, shortHedge, log, flag, target;
    private int tileSize = 30;

    //     Draws each tile on the screen in the form of a grid
    private int[][] tileGrid = {
        {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1, 2, 2, 2},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 3, 1},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 4, 1},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 3, 2},
        {1, 4, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 3, 3, 4, 4, 4, 1},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 3, 4, 3, 1},
        {1, 4, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 3, 1},
        {1, 4, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 8, 9, 10, 3, 1},
        {1, 4, 3, 4, 3, 4, 3, 4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 11, 12, 13, 3, 1},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 14, 15, 16, 3, 1},
        {1, 4, 3, 4, 4, 4, 3, 4, 3, 3, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 1},
        {1, 4, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 4, 1},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 4, 1},
        {1, 4, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 3, 1},
        {1, 3, 4, 4, 3, 3, 3, 4, 4, 4, 3, 4, 3, 3, 4, 3, 4, 4, 4, 3, 4, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1 }

    };
    private int[][] propGrid = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {8, 0, 0, 7, 0, 0, 0, 4, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0},
        {2, 0, 0, 7, 0, 0, 0, 4, 0, 0, 4, 0, 0, 7, 0, 0, 0, 0, 4, 0, 0, 0},
        {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 4, 0},
        {0, 0, 2, 0, 2, 0, 0, 4, 0, 0, 4, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 6, 6, 0, 0, 0},
        {0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 6, 0, 0, 0},
        {0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 4, 0, 0, 7, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0},
        {0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

    };

    public Background(){
//        Initializes the tiles
        grassTileA = new Texture("tiles/PNG/Tiles/grass-a.png");
        grassTileB = new Texture("tiles/PNG/Tiles/grass-b.png");
        dirtTileA = new Texture("tiles/PNG/Tiles/dirt-a.png");
        dirtTileB = new Texture("tiles/PNG/Tiles/dirt-b.png");
        mudTileA = new Texture("tiles/PNG/Tiles/mud-a.png");
        mudTileB = new Texture("tiles/PNG/Tiles/mud-b.png");
        buildingTileA = new Texture("tiles/PNG/Buildings/Building_A_01.png");
        stoneTileTopLeft = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_01.png");
        stoneTileTop = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_02.png");
        stoneTileTopRight = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_03.png");
        stoneTileLeft = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_04.png");
        stoneTileCenter = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_05.png");
        stoneTileRight = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_06.png");
        stoneTileBotLeft = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_07.png");
        stoneTileBot = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_08.png");
        stoneTileBotRight = new Texture("tiles/PNG/Decor_Tiles/Decor_Tile_B_09.png");
        longHedge = new Texture("tiles/PNG/Blocks/long-hedge.png");
        shortHedge = new Texture("tiles/PNG/Blocks/short-hedge.png");
        log = new Texture("tiles/PNG/Props/Log.png");
        flag = new Texture("tiles/PNG/Props/Flag_A.png");
        target = new Texture("tiles/PNG/Props/Dot_A.png");


    }
    public void render(SpriteBatch batch){
        for (int row = 0; row < tileGrid.length; row++){
            for (int col = 0; col < tileGrid[row].length; col++){

                int x = col * tileSize;
                int y = (tileGrid.length - 1 - row) * tileSize;

                // Determines which tile corresponds with each number in the grid
                if (tileGrid[row][col] == 1){
                    // tilesize, tilesize, sets the dimensions to be different than the origional
                    batch.draw(grassTileA, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 2){
                    batch.draw(grassTileB, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 3){
                    batch.draw(dirtTileA, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 4){
                    batch.draw(dirtTileB, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 5){
                    batch.draw(mudTileA, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 6){
                    batch.draw(mudTileB, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 7){
                    batch.draw(buildingTileA, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 8){
                    batch.draw(stoneTileTopLeft, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 9){
                    batch.draw(stoneTileTop, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 10){
                    batch.draw(stoneTileTopRight, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 11){
                    batch.draw(stoneTileLeft, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 12){
                    batch.draw(stoneTileCenter, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 13){
                    batch.draw(stoneTileRight, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 14){
                    batch.draw(stoneTileBotLeft, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 15){
                    batch.draw(stoneTileBot, x, y, tileSize, tileSize);
                }
                else if (tileGrid[row][col] == 16){
                    batch.draw(stoneTileBotRight, x, y, tileSize, tileSize);
                }
            }
        }


        // Prop map grid rules
        for (int row = 0; row < tileGrid.length; row++){
            for (int col = 0; col < tileGrid[row].length; col++){

                int x = col * tileSize;
                int y = (tileGrid.length - 1 - row) * tileSize;

                if (propGrid[row][col] == 1)
                    batch.draw(buildingTileA, x, y, tileSize, tileSize);
                else if(propGrid[row][col] == 2)
                    batch.draw(longHedge, x, y, tileSize * 2, tileSize);
                else if (propGrid[row][col] == 3)
                    batch.draw(shortHedge, x, y, tileSize, tileSize);
                else if (propGrid[row][col] == 4){
                    batch.draw(longHedge,
                        x, y,
                        tileSize / 2f, tileSize / 2f,
                        tileSize * 2, tileSize,
                        1f, 1f,
                        90,
                        0, 0,
                        longHedge.getWidth(), longHedge.getHeight(),
                        false, false);
                }
                else if (propGrid[row][col] == 5)
                    batch.draw(log, x, y, tileSize * 2, tileSize * 2);
                else if (propGrid[row][col] == 6)
                    batch.draw(flag, x, y, tileSize * 2, tileSize * 2);
                else if (propGrid[row][col] == 7){
                    batch.draw(log,
                        x, y,
                        tileSize / 2f, tileSize / 2f,
                        tileSize * 2, tileSize * 2,
                        1f, 1f,
                        90,
                        0, 0,
                        log.getWidth(), log.getHeight(),
                        false, false);
                }
                else if (propGrid[row][col] == 8)
                    batch.draw(target, x, y, tileSize * 2, tileSize * 2);
            }
        }
    }

    public void dispose(){
        grassTileA.dispose();
        grassTileB.dispose();
        dirtTileA.dispose();
        dirtTileB.dispose();
        mudTileA.dispose();
        mudTileB.dispose();
        buildingTileA.dispose();
        stoneTileLeft.dispose();
        stoneTileTop.dispose();
        stoneTileTopRight.dispose();
        stoneTileLeft.dispose();
        stoneTileCenter.dispose();
        stoneTileRight.dispose();
        stoneTileBotLeft.dispose();
        stoneTileBot.dispose();
        stoneTileBotRight.dispose();
        longHedge.dispose();
        shortHedge.dispose();
    }
}
