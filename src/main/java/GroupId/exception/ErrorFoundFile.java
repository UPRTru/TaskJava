package GroupId.exception;

import GroupId.activity.StartActivity;

public class ErrorFoundFile extends RuntimeException {
    public ErrorFoundFile(String message) {
        super(message);
        System.out.println(message);
        StartActivity.start();
    }
}
