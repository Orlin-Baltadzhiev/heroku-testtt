package bg.DeveloperGroup.musicdb.models.binding;

import bg.DeveloperGroup.musicdb.models.entity.enums.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AlbumBindingModel {
    private String name;
    private String imageUrl;
    private String videoUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Genre genre;
    private String artist;

    public AlbumBindingModel(){
    }

    @Size(min = 5, max = 20,message = "Username length must be between 5 and 20 characters and passwords should match")
    public String getName(){
        return name;
    }

    public AlbumBindingModel setName(String name){
        this.name = name;
        return this;
    }
    @Size(min = 5)
    public String getImageUrl(){
        return imageUrl;
    }

    public AlbumBindingModel setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
        return this;
    }
    @Size(min = 5)
    public String getVideoUrl(){
        return videoUrl;
    }

    public AlbumBindingModel setVideoUrl(String videoUrl){
        this.videoUrl = videoUrl;
        return this;
    }

    @Size(min = 5)
    public String getDescription(){
        return description;
    }

    public AlbumBindingModel setDescription(String description){
        this.description = description;
        return this;
    }

    @Min (0)
    public Integer getCopies(){
        return copies;
    }

    public AlbumBindingModel setCopies(Integer copies){
        this.copies = copies;
        return this;
    }

    @DecimalMin ("0")
    public BigDecimal getPrice(){
        return price;
    }

    public AlbumBindingModel setPrice(BigDecimal price){
        this.price = price;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public AlbumBindingModel setReleaseDate(LocalDate releaseDate){
        this.releaseDate = releaseDate;
        return this;
    }



    @NotNull
    public Genre getGenre(){
        return genre;
    }

    public AlbumBindingModel setGenre(Genre genre){
        this.genre = genre;
        return this;
    }

    public String getArtist(){
        return artist;
    }

    public AlbumBindingModel setArtist(String artist){
        this.artist = artist;
        return this;
    }

    @Override
    public String toString(){
        return "AlbumBindingModel{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", description='" + description + '\'' +
                ", copies=" + copies +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                '}';
    }
}
