package org.example.util;

import org.example.entity.ReportLogin;

import java.util.Comparator;

/**
 * @author a.mehdizadeh on 5/8/2024
 */
public class ReportLoginDateComparator implements Comparator<ReportLogin> {
    @Override
    public int compare(ReportLogin o1, ReportLogin o2) {
        return Long.compare(o1.getLoginDate().getTime(), o2.getLoginDate().getTime());
    }
}
