package byog.lab5;
import javafx.geometry.Pos;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 35;
    private static final int HEIGHT = 40;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        private int x;
        private int y;
    }

    /**
     * Adds a hexagon of side length s to a given position in the world.
     * (x + 2s - 1, y) - (x + 2s - 1, y + s - 1)
     * ...
     * (x + s + 1, y - s) - (x + s + 1, y + 2s - 1)
     * (x + s, y - s) - (x + s, y + 2s - 1)
     * ...
     * (x + 1, y - 1) - (x + 1, y + s)
     * p(x, y) - (x, y + s - 1)
     * @param world
     * @param p
     * @param s
     * @param t
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        Position pp = new Position();
        int px = p.x;
        int py = p.y;
        pp.x = px;
        pp.y = py;
        for(int i = 0; i < s; i++) {
            pp.x = px - i;
            pp.y = py + i;
            addLine(world, pp, s + 2 * i, t);
//            world[p.x - i][p.y + i] = t;
        }
        px -= s - 1;
        py += s;
        for(int i = 0; i < s; i++) {
            pp.x = px + i;
            pp.y = py + i;
            addLine(world, pp, 3 * s - 2 * i - 2, t);
//            world[p.x + i][p.y + i] = t;
        }
    }

    /**
     * Adds a horizontal line of length s to a given position in the world.
     * @param world
     * @param p
     * @param s
     * @param t
     */
    public static void addLine(TETile[][] world, Position p, int s, TETile t) {
        for(int i = 0; i < s; i++){
            world[p.x + i][p.y] = t;
        }
    }

    /**
     * Draws a vertical in the number of num Hexes of length s to a given position in the world.
     * @param world
     * @param s
     * @param num
     */
    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int num) {
        Position pp = new Position();
        pp.x = p.x;
        pp.y = p.y;
        TETile t = randomTile();
        for(int i = 0; i < num; i++) {
            pp.y += 2 * s;
            addHexagon(world, pp, s, t);
            t = randomTile();
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            case 3: return Tileset.SAND;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    public static Position bottomRightNeighbor(Position p, int s) {
        Position res = new Position();
        res.x = p.x + 2 * s - 1;
        res.y = p.y - s;
        return res;
    }

    public static Position topRightNeighbor(Position p, int s) {
        Position res = new Position();
        res.x = p.x + 2 * s - 1;
        res.y = p.y + s;
        return res;
    }

    public static void fillWithEmpty(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        fillWithEmpty(tiles);

        Position p = new Position();
        int size = 3;

        p.x = 5;
        p.y = 2 * size;

        drawRandomVerticalHexes(tiles, p, size, 3);
        p = bottomRightNeighbor(p, size);
        drawRandomVerticalHexes(tiles, p, size, 4);
        p = bottomRightNeighbor(p, size);
        drawRandomVerticalHexes(tiles, p, size, 5);
        p = topRightNeighbor(p, size);
        drawRandomVerticalHexes(tiles, p, size, 4);
        p = topRightNeighbor(p, size);
        drawRandomVerticalHexes(tiles, p, size, 3);

        ter.renderFrame(tiles);
    }

}
