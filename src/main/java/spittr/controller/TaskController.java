package spittr.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Priority;
import spittr.entity.Task;
import spittr.repo.TaskRepo;
import spittr.search.PrioritySearchValues;
import spittr.search.TaskSearchValues;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepo taskRepo;

    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping("/all")
    public List<Task> test(){
        return taskRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> getOne(@PathVariable Long id){
        Optional<Task> task = taskRepo.findById(id);
        if(task.isPresent())
            return ResponseEntity.ok(task.get());
        else
            return new ResponseEntity(String.format("Id: %d not found", id), HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if(task.getId() != null && task.getId() != 0)
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        if(task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepo.save(task));
    }
    
    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0)
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);

        if (task.getTitle() == null || task.getTitle().trim().length() == 0)
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);

        return ResponseEntity.ok(taskRepo.save(task));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        try {
            taskRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity(String.format("Id: %d not found", id), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Task>> search(@RequestBody TaskSearchValues values){
//        return ResponseEntity.ok(taskRepo.findByTitle(values.getText()));
        return null;
    }




}
