package com.example.RservationsService.mapper;

import com.example.RservationsService.domain.TrainingCategory;
import com.example.RservationsService.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto categoryToCategoryDto(TrainingCategory trainingCategory){
        return new CategoryDto(trainingCategory.getId(),trainingCategory.getCategory(),trainingCategory.getPrice(),trainingCategory.getType());
    }

}
