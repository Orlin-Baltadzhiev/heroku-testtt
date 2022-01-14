package bg.DeveloperGroup.musicdb.repository;

import bg.DeveloperGroup.musicdb.models.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository <ArticleEntity, Long> {
}
