package football.services;

import football.Event;

import java.util.HashMap;

public interface EventBuilder {
    Event buildEvent(HashMap<String, String> map);
}
