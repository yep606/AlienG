package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Category;
import spittr.entity.Priority;
import spittr.repo.CategoryRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/all")
    public List<Category> test() {
        return categoryRepo.findAllByOrderByIdAsc();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent())
            return ResponseEntity.ok(category.get());
        else
            return new ResponseEntity(String.format("Id: %d not found", id), HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0)
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepo.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {

        if (category.getId() == null || category.getId() == 0)
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);

        if (category.getTitle() == null || category.getTitle().trim().length() == 0)
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);

        return ResponseEntity.ok(categoryRepo.save(category));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        try {
            categoryRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity(String.format("Id: %d not found", id), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);

    }
}

