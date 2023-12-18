package GroupId.exception;

import GroupId.activity.StartActivity;

public class ErrorInPut extends RuntimeException{
    public ErrorInPut(String message) {
        super(message);
        System.out.println(message);
        StartActivity.start();
    }
}
