package exeptions;

public class MakeCommandException extends Exception {

    public MakeCommandException(ReflectiveOperationException e) {
        this.addSuppressed(e);
    }

    public MakeCommandException(RuntimeException e) {
        this.addSuppressed(e);
    }
}
