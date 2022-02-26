package model;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point plus(int argx, int argy) {
        return new Point(x + argx, y + argy);
    }
}
