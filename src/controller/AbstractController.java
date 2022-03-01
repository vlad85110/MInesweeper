package controller;

import factory.CommandFactory;
import model.data.Field;

public abstract class AbstractController implements Controller {
    protected CommandFactory factory;
    protected Field field;

    @Override
    public void setField(Field field) {
        this.field = field;
    }
}
