package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorDataDTO;
import ro.tuc.ds2020.entities.SensorData;

public class SensorDataBuilder {

    public SensorDataBuilder() {
    }

    public static SensorDataDTO toSensorDataDTO(SensorData sensorData) {
        return new SensorDataDTO(sensorData.getId(),
                sensorData.getPatient_id(),
                sensorData.getStart(),
                sensorData.getEnd(),
                sensorData.getActivity(),
                sensorData.isAnomaly()
        );
    }

    public static SensorData toEntity(SensorDataDTO sensorDataDTO) {
        return new SensorData(
                sensorDataDTO.getPatient_id(),
                sensorDataDTO.getStart(),
                sensorDataDTO.getEnd(),
                sensorDataDTO.getActivity(),
                sensorDataDTO.isAnomaly());
    }

    public static SensorData toEntityUpdate(SensorDataDTO sensorDataDTO) {
        return new SensorData(sensorDataDTO.getId(),
                sensorDataDTO.getPatient_id(),
                sensorDataDTO.getStart(),
                sensorDataDTO.getEnd(),
                sensorDataDTO.getActivity(),
                sensorDataDTO.isAnomaly());
    }
}
