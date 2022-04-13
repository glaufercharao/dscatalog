package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.mapper.Mappable;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
                () -> new ResourceNotFoundException("Entity not found")),CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        return map(categoryRepository.save(map(categoryDTO, Category.class)), CategoryDTO.class);
    }

    @Transactional
    public CategoryDTO update(CategoryDTO categoryDTO, Long id) {
        try {
            Category category = categoryRepository.getById(id);
            category.setName(categoryDTO.getName());
            return map(categoryRepository.save(category), CategoryDTO.class);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found "+ id);
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found "+id);
        }catch (DataIntegrityViolationException d){
            throw new DatabaseException("Integrity violation");
        }

    }
}
