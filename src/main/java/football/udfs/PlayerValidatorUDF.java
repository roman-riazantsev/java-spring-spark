package football.udfs;

import football.configs.TeamConfig;
import football.utils.AutowiredBroadcast;
import football.utils.UDF;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

@UDF
public class PlayerValidatorUDF implements UDF1<String, Boolean> {
    @AutowiredBroadcast
    private Broadcast<TeamConfig> teams;

    @Override
    public Boolean call(String player) throws Exception {
        return teams.getValue().getTeams().get(player) != null;
    }
}