package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import model.data.Point;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Open extends AbstractGameCommand {
    public Open(CommandDescriptor descriptor) throws MakeCommandException {
        super(descriptor);
    }

    private static final Map<String, Point> vectors = new HashMap<>() {
        {
         put("up", new Point(0, -1));
         put("down", new Point(0, 1));
         put("right", new Point(1, 0));
         put("left", new Point(-1, 0));

         put("downRight", new Point(1, 1));
         put("upLeft", new Point(-1, -1));
         put("upRight", new Point(1, -1));
         put("downLeft", new Point(-1, 1));
        }
    };


    @Override
    public Tags run() throws IOException {
        if (field.isMine(point)) {
            field.setLose();
            return Tags.False;
        }

        openMain(point);
        return Tags.True;
    }

    public void openMain(Point point) throws IOException {
        if (field.outOf(point)) {
            throw new IOException();
        }

        if (field.isNearMine(point)) {
            field.openSell(point);
            return;
        }

        if (field.isMine(point)) {
            return;
        }

        if (field.isEmpty(point)) {
            field.openSell(point);
            openSide(point.plus(0, -1), "up");
            openSide(point.plus(-1, 0), "left");
            openSide(point.plus(1, 0), "right");
            openSide(point.plus(0, 1), "down");
        }
    }

    private void openSide(Point point, String vector) {
        if (field.isOpen(point) || field.isMine(point) || field.outOf(point)) {
            return;
        }

        if (field.isNearMine(point)) {
            field.openSell(point);
            return;
        }

        field.openSell(point);
        for (var i : vectors.entrySet()) {
            if (!i.getValue().opposite(vectors.get(vector))) {
                openSide(point.plusPoint(i.getValue()), i.getKey());
            }
        }

    }
}
