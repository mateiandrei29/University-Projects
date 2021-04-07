package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bug")
public class Bug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBug")
    private Integer idBug;

    @Column(name = "description")
    private String description;

    @Column(name = "idPlace")
    private Integer idPlace;

    @Column(name = "resolved")
    private boolean resolved;

    public Bug(String description, Integer idPlace, boolean resolved) {
        this.description = description;
        this.idPlace = idPlace;
        this.resolved = resolved;
    }

    public Bug() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }


    @Override
    public String toString() {
        return "Bug{" +
                "description='" + description + '\'' +
                '}';
    }
}
