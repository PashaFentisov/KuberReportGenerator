package core.kuberreportgenerator;

public interface ReportSender {
    void sendReport(UserInfoDto userInfoDto, String report);
}
