package controller.commands;

import controller.commands.descriptors.CommandDescriptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class About extends AbstractWriteCommand {
    public About(CommandDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected String getFileName() {
        return "about.txt";
    }
}
