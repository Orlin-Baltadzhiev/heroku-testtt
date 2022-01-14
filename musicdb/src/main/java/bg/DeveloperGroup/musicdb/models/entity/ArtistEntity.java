package bg.DeveloperGroup.musicdb.models.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Expose
    @Column(nullable = false)
    private String name;
    @Expose
    @Column(nullable = false,columnDefinition = "text")
    private String careerInformation;

    public ArtistEntity(){
    }

    public String getName(){
        return name;
    }

    public ArtistEntity setName(String name){
        this.name = name;
        return this;
    }

    public String getCareerInformation(){
        return careerInformation;
    }

    public ArtistEntity setCareerInformation(String careerInformation){
        this.careerInformation = careerInformation;
        return this;
    }

    @Override
    public String toString(){
        return "ArtistEntity{" +
                "name='" + name + '\'' +
                ", careerInformation='" + careerInformation + '\'' +
                '}';
    }
}
