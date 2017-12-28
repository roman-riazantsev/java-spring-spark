package football.configs;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Builder
public class TeamConfig implements Serializable {
    @Getter
    private final HashMap<String,String> teams;
}
