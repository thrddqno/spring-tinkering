package com.thrddqno.expense_tracker_api.common.config;

import com.thrddqno.expense_tracker_api.category.Category;
import com.thrddqno.expense_tracker_api.category.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class CategorySeeder {

    @Bean
    CommandLineRunner seedCategories(CategoryRepository categoryRepository){ return args -> {
        List<String> categories = List.of(
                "Food",
                "Transport",
                "Bills",
                "Entertainment",
                "Health",
                "Shopping",
                "Education",
                "Other"
        );

        for (String name : categories){
            if(!categoryRepository.existsByName(name)){
                categoryRepository.save(Category.builder()
                        .name(name)
                        .build());
            }
        }
        log.debug("Categories seeded.");
    };

    }
}
