package entities;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPhoto")
    private Integer idPhoto;

    @Column(name = "path")
    private String path;

    @Column(name = "idPlace")
    private Integer idPlace;


}
