package com.InternationalPassport.businessLayer.service;


import com.InternationalPassport.businessLayer.model.Photo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PhotoService {
    Photo findById(Integer id);
    Photo findByQuery(String query);
    List<Photo> findAll();
    void persist(Photo entity);
    void update(Photo entity);
    void delete(Photo entity);
}
