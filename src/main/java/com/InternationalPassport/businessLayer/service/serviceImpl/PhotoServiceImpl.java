package com.InternationalPassport.businessLayer.service.serviceImpl;

import com.InternationalPassport.businessLayer.DAO.PhotoDAO;
import com.InternationalPassport.businessLayer.model.Photo;
import com.InternationalPassport.businessLayer.service.PhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
    private static final Logger logger = LogManager.getLogger(PhotoServiceImpl.class);

    @Autowired
    PhotoDAO photoDAO;

    @Override
    public Photo findById(Integer id) {
        Photo photo = null;
        try {
            photo = photoDAO.findById(id);
            logger.debug("Service Photo findById -- " + photo);
        } catch (DataAccessException dae) {
            logger.error("Was error from findById " + dae.getMessage());
        }

        return photo;
    }

    @Override
    public Photo findByQuery(String query) {
        return null;
    }

    @Override
    public List<Photo> findAll() {
        List<Photo> photos = null;
        try {
            photos = photoDAO.findAll();
            logger.debug("Service Photo findAll -- " + photos.size());
        } catch (DataAccessException dae) {
            logger.error("Was error from findAll " + dae.getMessage());
        }
        return photos;
    }

    @Override
    public void persist(Photo entity) {
        try {
            photoDAO.persist(entity);
            logger.debug("Service Photo persist -- " + entity);
        } catch (DataAccessException dae) {
            logger.error("Was error from persist " + dae.getMessage());
        }
    }

    @Override
    public void update(Photo entity) {
        try {
            photoDAO.update(entity);
            logger.debug("Service Photo update -- " + entity);
        } catch (DataAccessException dae) {
            logger.error("Was error from update " + dae.getMessage());
        }
    }

    @Override
    public void delete(Photo entity) {
        try {
            photoDAO.delete(entity);
            logger.debug("Service Photo delete -- " + entity);
        } catch (DataAccessException dae) {
            logger.error("Was error from delete " + dae.getMessage());
        }
    }
}
