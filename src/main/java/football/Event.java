package football;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class Event implements Serializable {
    private final int code;
    private final String from;
    private final String to;
    private final String eventTime;
    private final String stadion;
    private final String startTime;
}