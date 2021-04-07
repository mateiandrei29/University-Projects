package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.SensorData;

import java.util.Objects;
import java.util.UUID;

public class SensorDataDTO extends RepresentationModel<SensorDataDTO> {
    private UUID id;
    private long start;
    private long end;
    private String activity;
    private boolean isAnomaly;
    private UUID patient_id;


    public SensorDataDTO() {
    }

    public SensorDataDTO(UUID id, UUID patient_id, long start, long end, String activity, boolean isAnomaly) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.activity = activity;
        this.isAnomaly = isAnomaly;
        this.patient_id = patient_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public boolean isAnomaly() {
        return isAnomaly;
    }

    public void setAnomaly(boolean anomaly) {
        isAnomaly = anomaly;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDataDTO sensorDataDTO = (SensorDataDTO) o;
        return activity == sensorDataDTO.activity &&
                Objects.equals(start, sensorDataDTO.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, activity);
    }

}
