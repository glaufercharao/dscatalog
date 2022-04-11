package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.mapper.Mappable;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements Mappable {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAllCategory(){
        return map(categoryRepository.findAll(),CategoryDTO.class);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findByIdCategory(Long id){
        return map(categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found")),CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        return map(categoryRepository.save(map(categoryDTO, Category.class)), CategoryDTO.class);
    }
}
