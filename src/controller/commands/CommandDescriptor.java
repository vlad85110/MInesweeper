package controller.commands;

import model.data.Field;
import model.data.Point;

public record CommandDescriptor(String name, Point arg, Field field){}

