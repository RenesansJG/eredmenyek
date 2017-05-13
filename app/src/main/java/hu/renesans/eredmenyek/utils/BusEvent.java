package hu.renesans.eredmenyek.utils;

public abstract class BusEvent<T> {
    private int code;
    private T result;
    private Throwable throwable;

    public BusEvent() {
    }

    public BusEvent(int code, T result, Throwable throwable) {
        this.code = code;
        this.result = result;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
