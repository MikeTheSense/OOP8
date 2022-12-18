package Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id_album")
    private int id;

    @Column(name = "album_genre")
    private String genre;

    @Column(name = "album_name",unique = true)
    private String albumName;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Singer singer;
    
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "album")
    private List<Composition> compositions;

    public Album(String genre, String albumName, Singer singer) {
        this.genre = genre;
        this.albumName = albumName;
        this.singer = singer;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public Album() {
    }

    public Album(String genre, String albumName) {
        this.genre = genre;
        this.albumName = albumName;
    }

    public int getsingerId()
    {
        return singer.getId();
    }
    public String getsingerName()
    {
        return singer.getName();
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Singer getsinger() {
        return singer;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setsinger(Singer singer) {
        this.singer = singer;
    }
}