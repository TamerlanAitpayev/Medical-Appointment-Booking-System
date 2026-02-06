package Utils;

public class Result<T> {
    private final T data;
    private final String message;
    private final boolean success;

    private Result(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, "Success", true);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(null, message, false);
    }

    public T getData() { return data; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }
}