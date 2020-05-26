package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Priority;
import spittr.repo.PriorityRepo;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityRepo priorityRepo;

    @Autowired
    public PriorityController(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @GetMapping("/test")
    public List<Priority> test(){
        return priorityRepo.findAll();
    }

    @PostMapping("/add")
    public Priority add(@RequestBody Priority priority) {
        System.out.println(priority);
        return priorityRepo.save(priority);
    }

}
