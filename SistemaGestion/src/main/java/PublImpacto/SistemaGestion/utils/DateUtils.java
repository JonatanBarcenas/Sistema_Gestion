package PublImpacto.SistemaGestion.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
	
	public static boolean isNearDeadline(LocalDateTime fechaEntrega) {
        LocalDateTime ahora = LocalDateTime.now();
        return fechaEntrega.minusHours(48).isBefore(ahora);
    }

    public static boolean isOverdue(LocalDateTime fechaEntrega) {
        return fechaEntrega.isBefore(LocalDateTime.now());
        
    }
    
    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDateTime();
    }
}
