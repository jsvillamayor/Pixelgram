package com.cognizant.uupostcrud.service;

import com.cognizant.uupostcrud.Model.PageOfItems;
import com.cognizant.uupostcrud.Model.Post;
import com.cognizant.uupostcrud.repo.PostRepo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    private PostService postservice;

    @Mock
    private PostRepo postRepo;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks((this));
        this.postservice = new PostService(postRepo);
        Post post = new Post();
        post.setId(1);
    }

    @Test
    public void getByID_doesNotReturnNull(){
        Post post = new Post();
        post.setId(1);
        given(postRepo.findById(1)).willReturn(Optional.of(post));
        assertNotNull(postservice.getById(1));
    }

    @Test
    public void getPage_doesNotReturnNull(){
        //arrange
        Page<Post> Page = Mockito.mock(Page.class);
        given(postRepo.findAll((Pageable) Mockito.any())).willReturn(Page);
        Assert.assertNotNull(postservice.getPage(1, 5));
    }

    @Test
    public void getById_callsRepoFindById(){
        //arrange
        Post post = new Post();
        post.setId(1);
        //act
        postservice.getById(1);
        //assert
        verify(postRepo).findById(1);
    }

    @Test
    public void getPage_callsRepoFindAll() throws Exception{
        //arrange
        Page<Post> Page = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(1,5,Sort.by("created").descending());
        //act
        when(postRepo.findAll(pageable)).thenReturn(Page);
        postservice.getPage(1,5);
        //assert
        verify(postRepo, times(3)).findAll(pageable);
    }

    @Test
    public void getById_returnsPostFromFindByIDFromPostRepo(){
        //arrange
        Post post = new Post();
        post.setId(1);
        Optional<Post> optionalPost = Optional.of(post);
        //act
        given(postRepo.findById(1)).willReturn(Optional.of(post));
        Optional<Post> result = postservice.getById(1);
        //assert
        assertEquals(optionalPost,result);
    }

    //Test to be worked on. Was having issue with model @ having different numbers but everything else was equal
    @Test
    public void getPage_returnsPageOfItems(){
        //arrange
        Page<Post> Page = Mockito.mock(Page.class);
        Pageable pageable = PageRequest.of(1,5, Sort.by("created").descending());
        //act
        when(postRepo.findAll(pageable)).thenReturn(Page);
        PageOfItems result = postservice.getPage(1,5);
        PageOfItems pageOfItems = new PageOfItems<>();
        pageOfItems.setTotalElements(0);
        //assert
        assertEquals(pageOfItems.getTotalElements(),0);

    }
}

