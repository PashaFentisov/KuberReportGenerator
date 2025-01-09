package core.kuberreportgenerator;

import org.springframework.stereotype.Service;

@Service
public class PlainReportGenerator implements ReportGenerator {
    @Override
    public String generateReport(UserInfoDto userInfoDto) {
        return String.format("Plain report for user %s ", userInfoDto.getName());
    }
}
