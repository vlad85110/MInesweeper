package model;

import model.data.Field;
import model.data.GameDescriptor;
import model.data.Point;

public class LabyrinthCreator extends MapCreator {
    public LabyrinthCreator(GameDescriptor descriptor, Field field) {
        super(descriptor, field);
    }

    @Override
    public void initField(Point start) {
        boolean [][] protectedPoints = new boolean[size][size];


    }

    private void createLabirynth(boolean [][] points, Point vector)

    @Override
    public void fillNeighbours(Point point) {
        super.fillNeighbours(point);
    }
}
