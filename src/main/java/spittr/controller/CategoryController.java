package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Category;
import spittr.repo.CategoryRepo;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/test")
    public List<Category> test(){
        return categoryRepo.findAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody Category category) {
        System.out.println(category);
        categoryRepo.save(category);
    }





}
