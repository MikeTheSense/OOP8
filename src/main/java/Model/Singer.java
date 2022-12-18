package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "singer")
public class Singer implements Serializable {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id_singer")
    private int id;

    @Column(name = "singer_name", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "singer")
    private List<Album> albums;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Singer() {
    }

    public Singer(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}