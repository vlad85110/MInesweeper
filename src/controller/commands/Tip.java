package controller.commands;

import controller.commands.descriptors.CommandDescriptor;
import exeptions.MakeCommandException;
import exeptions.RunCommandException;
import model.data.Field;
import model.data.Point;

public class Tip implements Command {
    private final Field field;

    public Tip(CommandDescriptor descriptor) {
        this.field = descriptor.field;
    }

    @Override
    public Tags run() throws RunCommandException {
       var view = field.getView().view();
       var size = view.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isNum(view[i][j])) {
                    var point = new Point(i, j);
                    findBomb(view, point);
                    findFree(view, point);
                }
            }
        }
        return Tags.True;
    }

    boolean isNum(Character sym) {
        if (sym == 'b')
            System.out.println(1);
        return sym != 'x' && sym != 'f' && sym != '0';
    }

    private void findFree(Character[][] field, Point sell) throws RunCommandException {
        var x = sell.x;
        var y = sell.y;
        int cnt = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i != x || j != y) {
                    try {
                        if (field[i][j] == 'f') {
                            cnt++;
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {/**/}
                }
            }
        }
        if (cnt == Integer.parseInt(field[x][y].toString())) {
            openNear(sell);
            //System.out.println("all free near " + sell);
        }
    }

    private void findBomb(Character[][] field, Point sell) throws RunCommandException {
        var x = sell.x;
        var y = sell.y;
        int cnt = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i != x || j != y) {
                    try {
                        if (field[i][j] == 'x'|| field[i][j] == 'f') {
                            cnt++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {/**/}
                }
            }
        }
        if (cnt == Integer.parseInt(field[x][y].toString())) {
            setNear(sell);
            //System.out.println("all bmb near " + sell);
        }
    }

    private void setNear(Point sell) throws RunCommandException {
        var x = sell.x;
        var y = sell.y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i != x || j != y) {
                    try {
                        var point = new Point(i,j);
                        if (!field.outOf(point) && !field.isFlag(point)) {
                            new SetFlag(new CommandDescriptor(("set " + new Point(i, j)).split(" "), field))
                                    .run();
                        }
                    } catch (MakeCommandException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void openNear(Point sell) throws RunCommandException{
        var x = sell.x;
        var y = sell.y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i != x || j != y) {
                    try {
                        var point = new Point(i,j);
                        if (!field.outOf(point) && !field.isFlag(point)) {
                            new Open(new CommandDescriptor(("open " + new Point(i, j)).split(" "), field))
                                    .run();
                        }
                    } catch (MakeCommandException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public Object getArg() {
        return null;
    }
}
