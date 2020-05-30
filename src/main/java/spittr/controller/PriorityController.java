package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Category;
import spittr.entity.Priority;
import spittr.repo.PriorityRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityRepo priorityRepo;

    @Autowired
    public PriorityController(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @GetMapping("/all")
    public List<Priority> test(){
        return priorityRepo.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {
        if(priority.getId() != null && priority.getId() != 0)
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        if(priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepo.save(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        if (priority.getId() == null || priority.getId() == 0)
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0)
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);

        return ResponseEntity.ok(priorityRepo.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> getOne(@PathVariable Long id){
        Optional<Priority> priority = priorityRepo.findById(id);
        if(priority.isPresent())
            return ResponseEntity.ok(priority.get());
        else
            return new ResponseEntity(String.format("Id: %d not found", id), HttpStatus.NOT_ACCEPTABLE);
    }


}
