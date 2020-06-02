package spittr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spittr.entity.Task;
import spittr.repo.TaskRepo;

import java.util.Optional;

@Controller
@RequestMapping
public class TaskController {

    private final TaskRepo taskRepo;

    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> getOne(@PathVariable Long id){
        Optional<Task> task = taskRepo.findById(id);
        if(task.isPresent())
            return ResponseEntity.ok(task.get());
        else
            return new ResponseEntity(String.format("Id: %d not found", id), HttpStatus.NOT_ACCEPTABLE);
    }


}
