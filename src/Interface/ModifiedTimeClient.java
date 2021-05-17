package Interface;

import java.time.ZonedDateTime;

public interface ModifiedTimeClient extends TimeClient{

    @Override
    public ZonedDateTime getZonedDateTime(String zoneString);
}
