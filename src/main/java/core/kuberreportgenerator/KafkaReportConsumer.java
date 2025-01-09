package core.kuberreportgenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaReportConsumer {
    private final ReportGenerator reportGenerator;
    private final ReportSender reportSender;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "report", groupId = "reportConsumerGroup")
    public void consume(String userInfoJson) {
        log.info("Consumed report for: {}", userInfoJson);
        UserInfoDto userInfoDto;
        try {
            userInfoDto = objectMapper.readValue(userInfoJson, UserInfoDto.class);
        } catch (JsonProcessingException e) {
            log.error("Error while converting JSON to UserInfoDto", e);
            //позначати що повідомлення не було успішно оброблено
            return;
        }
        String report = reportGenerator.generateReport(userInfoDto);
        log.info("Generated report: {}", report);
        reportSender.sendReport(userInfoDto, report);
    }
}
