package com.example.CS205.map;

import java.util.Random;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    public static final int NUMBER_OF_ROW_TILES = 100;
    public static final int NUMBER_OF_COLUMN_TILES = 100;

    private int[][] layout;

    public MapLayout() {
        initializeLayout();
    }

    public int[][] getLayout() {
        return layout;
    }

    /**
    * Initialise 2d array for tile configuration on map
    */
    private void initializeLayout() {
        Random rand = new Random();
        layout = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int random = rand.nextInt(1000);
                if (random < 100){
                    layout[i][j] = 4;
                }
                else if(random < 120){
                    layout[i][j] = 1;
                }
                else layout[i][j] = 3;
            }
        }
    }
}
