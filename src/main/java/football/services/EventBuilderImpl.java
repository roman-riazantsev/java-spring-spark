package football.services;

import football.Event;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;

import static football.configs.Const.*;

@Service
public class EventBuilderImpl implements EventBuilder, Serializable {
    @Override
    public Event buildEvent(HashMap<String, String> map) {
        return Event.builder().
                code(Integer.parseInt(map.get(CODE))).
                eventTime(map.get(EVENT_TIME)).
                from(map.get(FROM)).
                to(map.get(TO)).
                stadion(map.get(STADION)).
                startTime(map.get(START_TIME)).
                build();
    }
}
