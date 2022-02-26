package model;

import java.util.Random;

public record GameStarter(int size, int mines, Field field) {

    public void initField() {
        final Random random = new Random();
        int x, y;

        for (int i = 0; i < mines; i++) {
            Point point;
            do {
                x = random.nextInt(size - 1);
                y = random.nextInt(size - 1);
                point = new Point(x, y);
            } while (field.isMine(point));

            field.setMine(point, 'b');
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
