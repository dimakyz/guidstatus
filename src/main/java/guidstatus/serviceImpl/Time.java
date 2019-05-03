package guidstatus.serviceImpl;

import guidstatus.service.TimeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service("TimeService")
public class Time implements TimeService {
    private TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @PostConstruct
    private void setTimeZone () {
        dateFormat.setTimeZone(timeZone);
    }

    @Override
    public String getDatetime () {
        return dateFormat.format(new Date());
    }

}
