package begin.flywayspringmaven.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class APIResponseError {
    public long timestamp;
    public int code;
    public String error;
    public Object message;

    public APIResponseError() {
    }

    public APIResponseError(int code, Object message) {
        this.timestamp = new Date().getTime();
        this.code = code;
        this.message = message;
    }

    public APIResponseError(int code, Object message, String error) {
        this.timestamp = new Date().getTime();
        this.code = code;
        this.message = message;
    }

    public APIResponseError(long timestamp, int code, String error, Object message) {
        this.timestamp =  new Date().getTime();
        this.code = code;
        this.error = error;
        this.message = message;
    }
}
