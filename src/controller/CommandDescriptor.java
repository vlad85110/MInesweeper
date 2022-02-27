package controller;

import model.Field;
import model.Point;

public record CommandDescriptor(String name, Point arg, Field field){}

