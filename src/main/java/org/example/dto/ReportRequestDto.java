package org.example.dto;

import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
public class ReportRequestDto {

    private Pageable pageable;

    private Date time;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}


