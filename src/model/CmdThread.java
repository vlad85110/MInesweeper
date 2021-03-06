package model;

import controller.Controller;
import controller.commands.Command;
import exeptions.MakeCommandException;
import view.Viewer;

public class CmdThread extends Thread {
    private final Controller controller;
    private final Viewer viewer;
    private boolean flag;
    public Command cmd;
    Executor executor;

    public CmdThread (Controller controller, Viewer viewer, Executor executor) {
        this.controller = controller;
        this.viewer = viewer;
        this.executor = executor;
    }

    @Override
    public void run() {
        super.run();
        waitCmd();
    }

    private void waitCmd() {
        cmd = null;
        while (cmd == null && !flag) {
            try {
                cmd = controller.waitCommand();
            } catch (MakeCommandException e) {
                viewer.showList("wrong input, return");
            }
        }
        executor.setCmd(cmd);
    }

    public void kill() {
        flag = true;
        controller.interrupt();
    }
}
