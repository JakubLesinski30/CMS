package Twoj.Blog;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RepozytoriumArtykulu extends CrudRepository<Artykul, Long> {

}
