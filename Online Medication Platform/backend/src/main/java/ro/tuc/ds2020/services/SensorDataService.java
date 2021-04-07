package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.SensorDataDTO;
import ro.tuc.ds2020.dtos.builders.SensorDataBuilder;
import ro.tuc.ds2020.entities.SensorData;
import ro.tuc.ds2020.repositories.SensorDataRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorDataService {
    private final SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorDataDTO> findSensorData() {
        List<SensorData> sensorDataList = sensorDataRepository.findAll();
        return sensorDataList.stream()
                .map(SensorDataBuilder::toSensorDataDTO)
                .collect(Collectors.toList());
    }

    public UUID insert(SensorDataDTO sensorDataDTO){
        SensorData sensorData=SensorDataBuilder.toEntity(sensorDataDTO);

        sensorData=sensorDataRepository.save(sensorData);
        return sensorData.getPatient_id();
    }

}
