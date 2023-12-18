package GroupId.exception;

import GroupId.activity.StartActivity;

public class ErrorReadFile extends RuntimeException {
    public ErrorReadFile(String message) {
        super(message);
        System.out.println(message);
        StartActivity.start();
    }
}
