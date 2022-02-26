package model;

import static java.lang.Math.abs;

public class Field {
    private final int size;
    private final Character [][] mineMap;
    private final Character [][] flagsMap;
    private final Character [][] userView;

    public Field(int size) {
        this.size = size;
        mineMap = new Character[size][size];
        flagsMap = new Character[size][size];
        userView = new Character[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                userView[i][j] = 'x';
                mineMap[i][j] = '0';
                flagsMap[i][j] = '0';
            }
        }
    }

    public void setMine(Point point, Character value) {
        mineMap[point.x][point.y] = value;
    }

    public void setNum(Point point) {
        try {
            if (mineMap[point.x][point.y] != 'b')
                mineMap[point.x][point.y]++;
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    public void setFlag(int x, int y, Character value) {
        flagsMap[x][y] = value;
    }

    public int getSize() {
        return size;
    }

    public Character[][] getMineMap() {
        return mineMap;
    }

    public Character[][] getUserView() {
        return userView;
    }

    public boolean isMine(Point point) {
        return mineMap[point.x][point.y] == 'b';
    }
}
