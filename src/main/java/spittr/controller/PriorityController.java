package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public void test(){
        System.out.println("!!!!!!!!!!!!");
        List<Priority> list = priorityRepo.findAll();
        list.forEach(System.out::println);

    }

}
