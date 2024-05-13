package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.entity.ReportLogin;
import org.example.repository.ReportLoginRepository;
import org.example.util.ReportLoginDateComparator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author a.mehdizadeh on 3/4/2024
 */
@Service
public class ReportLoginService {


    private final ReportLoginRepository reportloginRepository;

    private final HttpServletRequest httpServletRequest;

    public ReportLoginService(ReportLoginRepository loginRepository, HttpServletRequest httpServletRequest) {
        this.reportloginRepository = loginRepository;
        this.httpServletRequest = httpServletRequest;
    }

    public ReportLogin save(ReportLogin reportLogin) {
        return reportloginRepository.save(useDetail(reportLogin));
    }


    private void addIp(ReportLogin reportLogin) {
        String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            reportLogin.setIp(httpServletRequest.getRemoteAddr());
        } else {
            reportLogin.setIp(ip);
        }
    }


    private void getCurrentDate(ReportLogin reportLogin) {
        Date date = new Date();
        reportLogin.setLoginDate(date);
    }

    private void addOs(ReportLogin reportLogin) {
        String userAgentString = httpServletRequest.getHeader("User-Agent");
        reportLogin.setOS(userAgentString);
    }

    private ReportLogin useDetail(ReportLogin reportLogin) {
        addIp(reportLogin);
        getCurrentDate(reportLogin);
        addOs(reportLogin);
        return reportLogin;
    }

    public List<ReportLogin> findByNationalCode(String nationalCode) {
        return reportloginRepository.findByNationalCode(nationalCode);
    }

    public String findLastLoginByNationalCode(String nationalCode) {
        List<ReportLogin>  result = findByNationalCode(nationalCode);
        if(!result.isEmpty()) {
            result.sort(new ReportLoginDateComparator());
            return result.get(result.size() - 2).getStringLoginDate();
        }
        return null;
    }

}


