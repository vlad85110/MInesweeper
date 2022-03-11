package model.data;

import java.util.Collections;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point plus(int argX, int argY) {
        return new Point(x + argX, y + argY);
    }

    public Point plusPoint(Point point) {
        return new Point(x + point.x, y + point.y);
    }

    public Point div(int num) {
        return new Point(x / num, y / num);
    }

    public boolean opposite(Point other) {
        return signum(x * other.x + y * other.y) < 0;
    }

    public boolean inSquare(Point other, int length) {
        return abs(x - other.x) <= length && abs(y - other.y) <= length;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
