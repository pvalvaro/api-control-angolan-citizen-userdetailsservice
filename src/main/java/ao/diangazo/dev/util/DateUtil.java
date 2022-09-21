package ao.diangazo.dev.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public Date formatInDate(String data) throws Exception {
        if (data == null || data.equals(""))
            return null;

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date)formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }
}