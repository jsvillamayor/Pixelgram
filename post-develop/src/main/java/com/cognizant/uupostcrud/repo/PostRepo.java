package com.cognizant.uupostcrud.repo;

import com.cognizant.uupostcrud.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepo extends PagingAndSortingRepository<Post,Integer> {
    boolean existsById(Integer id);
    Optional<Post> findById(Integer id);

    Page<Post> findAll(Pageable pageable);


}
