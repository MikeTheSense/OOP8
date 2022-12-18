package Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "Composition")
public class Composition implements Serializable {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id_composition")
    private int id;

    @Column(name = "composition_duration")
    private int duration;

    @Column(name = "composition_name")
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;

    public Composition(int duration, String compositionName, Album album) {
        this.duration = duration;
        this.name = compositionName;
        this.album = album;
    }

    public String getAlbumName()
    {
        return this.album.getAlbumName();
    }
    public int getAlbumId()
    {
        return this.album.getId();
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String compositionName) {
        this.name = compositionName;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Composition() {
    }

    public Composition(int duration, String compositionName) {
        this.duration = duration;
        this.name = compositionName;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public Album getAlbum() {
        return album;
    }
}