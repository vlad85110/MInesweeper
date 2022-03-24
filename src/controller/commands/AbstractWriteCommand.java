package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractWriteCommand implements Command {
    private final ArrayList<String> output;
    protected String arg;
    public AbstractWriteCommand (CommandDescriptor descriptor) {
        output = new ArrayList<>();
    }

    @Override
    public Tags run() throws IOException {
        File file = new File(getFileName());
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;

        line = reader.readLine();
        while (line != null) {
            output.add(line);
            line = reader.readLine();
        }

        fileReader.close();
        reader.close();
        return Tags.Write;
    }

    @Override
    public Object getArg() {
        return arg;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    protected abstract String getFileName();

    public void setArg(String arg) {
        this.arg = arg;
    }
}
