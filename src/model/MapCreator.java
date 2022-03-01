package model;

import model.data.Field;
import model.data.GameDescriptor;
import model.data.Point;

import java.util.Random;

public class MapCreator {
    protected final Field field;
    protected final int size;
    protected final int bombs;
    protected final int safetyRad;

    public MapCreator(GameDescriptor descriptor, Field field) {
        this.field = field;
        size = descriptor.size();
        bombs = descriptor.bombs();
        safetyRad = descriptor.safetyRad();
    }

    public void initField(Point start) {
        final Random random = new Random();
        int x, y;

        for (int i = 0; i < bombs; i++) {
            Point point;
            do {
                x = random.nextInt(size - 1);
                y = random.nextInt(size - 1);
                point = new Point(x, y);
            } while (field.isMine(point) || point.inSquare(start, safetyRad));

            field.setBomb(point);
            fillNeighbours(point);
        }
    }

    public void fillNeighbours(Point point) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(j == 0 && i == 0))
                    field.setNum(point.plus(i, j));
            }
        }
    }
}
