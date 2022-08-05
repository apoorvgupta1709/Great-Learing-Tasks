package com.cspl.service.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.cspl.commons.util.DateUtil.DATE_FORMAT_MM;
import static com.cspl.commons.util.DateUtil.DATE_FORMAT_YY;

@Slf4j
@Service
public class UserServiceHelper {

    public synchronized String generateCustomUserId(@NotNull Long sequenceId) {
        log.info("Entering into [UserServiceHelper → generateUserId] for sequenceId: {}", sequenceId);
        Date date = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat(DATE_FORMAT_YY);
        SimpleDateFormat monthFormat = new SimpleDateFormat(DATE_FORMAT_MM);
        return StringUtils.join(yearFormat.format(date), monthFormat.format(date),
                appendZeros(String.valueOf(sequenceId).length()), sequenceId);
    }

    private String appendZeros(int length) {
        StringBuilder zero = new StringBuilder();
        for (int i = 0; i < 6 - length; i++) {
            zero.append("0");
        }
        return zero.toString();
    }

}
