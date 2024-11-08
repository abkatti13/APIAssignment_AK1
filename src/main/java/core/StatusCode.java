package core;

public enum StatusCode {

    SUCCESS(200,"Request was processed successfully"),
    CREATED(201, "Resource was created successfully");

    public final String message;
    public final int code;

    StatusCode(int code,String message)  {
        this.message = message;
        this.code = code;
    }
}
