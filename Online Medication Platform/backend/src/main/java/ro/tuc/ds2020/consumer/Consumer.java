package ro.tuc.ds2020.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.SensorDataDTO;
import ro.tuc.ds2020.services.SensorDataService;

import java.io.IOException;

@Component
public class Consumer {
    private final SensorDataService sensorDataService;
    private SimpMessagingTemplate simpMessagingTemplate;

    public Consumer(SensorDataService sensorDataService, SimpMessagingTemplate simpMessagingTemplate) {
        this.sensorDataService = sensorDataService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = "sensor-queue")
    public void receivedData(String sensorData) {
//        System.out.println(sensorData);

        ObjectMapper mapper = new ObjectMapper();
        try {
            SensorDataDTO sensorDataDTO = mapper.readValue(sensorData, SensorDataDTO.class);
            sensorDataDTO.setAnomaly(false);

            if (sensorDataDTO.getActivity().equals("Sleeping") && (sensorDataDTO.getEnd() - sensorDataDTO.getStart() > 25200000)) {
                System.out.println("Sleep period longer than 7 hours");
                simpMessagingTemplate.convertAndSend("/message/notify", "Patient has slept longer than 7 hours ");

                sensorDataDTO.setAnomaly(true);
            }

            if (sensorDataDTO.getActivity().equals("Leaving") && (sensorDataDTO.getEnd() - sensorDataDTO.getStart() > 18000000)) {
                System.out.println("The leaving activity (outdoor) is longer than 5 hours");
                simpMessagingTemplate.convertAndSend("/message/notify", "Patient has spent time outside longer than 5 hours ");

                sensorDataDTO.setAnomaly(true);
            }

            if ((sensorDataDTO.getActivity().equals("Toileting") ||
                    sensorDataDTO.getActivity().equals("Showering") ||
                    sensorDataDTO.getActivity().equals("Grooming")) &&
                    (sensorDataDTO.getEnd() - sensorDataDTO.getStart() > 1800000)) {
                System.out.println("Period spent in bathroom is longer than 30 minutes");
                simpMessagingTemplate.convertAndSend("/message/notify", "Patient has spent time in bathroom longer than 30 minutes ");
                sensorDataDTO.setAnomaly(true);

            }

            sensorDataService.insert(sensorDataDTO);

            System.out.println(sensorData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
