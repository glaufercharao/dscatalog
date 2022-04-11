package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.mapper.Mappable;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements Mappable {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> findAllCategory(){
        return map(categoryRepository.findAll(),CategoryDTO.class);
    }
}
