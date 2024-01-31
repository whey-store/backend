package begin.flywayspringmaven.util;

import begin.flywayspringmaven.common.vo.PageInfo;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FlywaySpringUtils {
    /**
     * Convert milliseconds to LocalDateTime.
     *
     * @param millis milliseconds
     * @return LocalDateTime instance
     */
    public static LocalDateTime convertMilliToLocalDateTime(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Convert LocalDateTime to milliseconds.
     *
     * @param dateTime date time value
     * @return milliseconds
     */
    public static long convertLocalDateTimeToMilli(LocalDateTime dateTime) {
        if (null != dateTime) {
            return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        return 0L;
    }

    /**
     * Create paging with basic information
     *
     * @param <T>
     * @param page {@link Page} of T
     * @return {@link PageInfo}
     */
    public static <T> PageInfo<T> pagingResponse(Page<T> page) {
        // page info
        PageInfo<T> pageInfo = new PageInfo<T>();
        pageInfo.setTotal(page.getTotalElements());
        pageInfo.setLimit(page.getSize());
        pageInfo.setPages(page.getTotalPages());
        pageInfo.setPage(page.getNumber() + 1);
        pageInfo.setResult(page.getContent());

        return pageInfo;
    }


}
