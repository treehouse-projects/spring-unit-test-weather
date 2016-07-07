package com.teamtreehouse.service;

import com.teamtreehouse.dao.FavoriteDao;
import com.teamtreehouse.domain.Favorite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteDao dao;

    @InjectMocks
    private FavoriteService service = new FavoriteServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo() throws Exception {
        List<Favorite> faves = Arrays.asList(
            new Favorite(),
            new Favorite()
        );

        when(dao.findAll()).thenReturn(faves);

        assertEquals("findAll should return two favorites",2,service.findAll().size());
        verify(dao).findAll();
    }

    @Test
    public void findById_ShouldReturnOne() throws Exception {
        when(dao.findOne(1L)).thenReturn(new Favorite());
        assertThat(service.findById(1L),instanceOf(Favorite.class));
        verify(dao).findOne(1L);
    }

    @Test(expected = FavoriteNotFoundException.class)
    public void findById_ShouldThrowFavoriteNotFoundException() throws Exception {
        when(dao.findOne(1L)).thenReturn(null);
        service.findById(1L);
        verify(dao).findOne(1L);
    }
}
