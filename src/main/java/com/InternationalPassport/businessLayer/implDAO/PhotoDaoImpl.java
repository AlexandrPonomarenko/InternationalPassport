package com.InternationalPassport.businessLayer.implDAO;

import com.InternationalPassport.businessLayer.DAO.PhotoDAO;
import com.InternationalPassport.businessLayer.model.Customer;
import com.InternationalPassport.businessLayer.model.Photo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoImpl extends AbstractPersistenceProducer implements PhotoDAO {
    private static final Logger logger = LogManager.getLogger(PhotoDaoImpl.class);

    @Override
    public Photo findById(Integer id) {
        Photo photo = (Photo) getEntityManager().createQuery("SELECT p FROM Photo AS p WHERE p.id =:id")
                .setParameter("id", id)
                .getSingleResult();
        logger.debug("photo.getId() -- " + photo.getId() + " findById: " + photo);
        return photo;
    }

    @Override
    public Photo findByQuery(String query) {
        return null;
    }

    @Override
    public List<Photo> findAll() {
        List<Photo> allPhotos = getEntityManager().createQuery("FROM Photo", Photo.class)
                .getResultList();
        logger.debug(allPhotos + " findAll: -" + allPhotos.size());
        return allPhotos;
    }

    @Override
    public void persist(Photo entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void update(Photo entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void delete(Photo entity) {
        getEntityManager().remove(entity);
        logger.debug(" Photo - delete: - " + entity);
    }
}
