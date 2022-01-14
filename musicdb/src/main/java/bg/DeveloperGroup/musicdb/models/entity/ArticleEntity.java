package bg.DeveloperGroup.musicdb.models.entity;

import bg.DeveloperGroup.musicdb.models.entity.enums.Genre;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    private String title;
    private String imageUrl;
    private Genre genre;
    private String content;
    @ManyToOne
    private UserEntity userEntity;

    public ArticleEntity() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }
    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    @Column(nullable = false)
    public ArticleEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    @Enumerated(EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }

    public ArticleEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }
    @Column(nullable = false)
    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ArticleEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
