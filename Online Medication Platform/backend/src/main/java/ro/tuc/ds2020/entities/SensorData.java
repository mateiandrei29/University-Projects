package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class SensorData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "start_time", nullable = false)
    private long start;

    @Column(name = "end_time", nullable = false)
    private long end;

    @Column(name = "activity_label", nullable = false)
    private String activity;

    @Column(name = "is_anomaly", nullable = false)
    private boolean isAnomaly;

    @Column(name = "patient_id", nullable = false)
    private UUID patient_id;

    public SensorData() {
    }

    public SensorData(UUID id, UUID patient_id, long start, long end, String activity, boolean isAnomaly) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.activity = activity;
        this.isAnomaly = isAnomaly;
        this.patient_id = patient_id;
    }

    public SensorData(UUID patient_id, long start, long end, String activity, boolean isAnomaly) {
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

}
