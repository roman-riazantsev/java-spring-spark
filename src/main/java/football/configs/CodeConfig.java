package football.configs;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Properties;

@Builder
public class CodeConfig implements Serializable {
    @Getter
    private final Properties codes;
}
