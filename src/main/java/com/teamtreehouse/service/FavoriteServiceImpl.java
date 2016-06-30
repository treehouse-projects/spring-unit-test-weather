package com.teamtreehouse.service;

import com.teamtreehouse.dao.FavoriteDao;
import com.teamtreehouse.domain.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public List<Favorite> findAll() {
        return favoriteDao.findAll();
    }

    @Override
    public Favorite findById(Long id) {
        Favorite fave = favoriteDao.findOne(id);
        if(fave == null) {
            throw new FavoriteNotFoundException();
        }
        return fave;
    }

    @Override
    public void save(Favorite favorite) {
        if(favoriteDao.saveForCurrentUser(favorite) == 1) {
            Favorite newFave = favoriteDao.findByPlaceId(favorite.getPlaceId());
            favorite.setId(newFave.getId());
        } else {
            throw new FavoriteSaveException();
        }
    }

    @Override
    public void delete(Long id) {
        if(favoriteDao.deleteForCurrentUser(id) < 1) {
            throw new FavoriteDeleteException();
        }
    }
}