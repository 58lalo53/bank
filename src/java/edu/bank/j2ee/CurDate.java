package edu.bank.j2ee;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author 58lalo53
 */
public class CurDate {
    public static Timestamp now(){
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        return new Timestamp(now.getTime());
    }
}
