package spittr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spittr.entity.Stat;
import spittr.repo.StatRepo;

@RestController
@RequestMapping("/stat")
public class StatController {

    private final StatRepo statRepo;
    private final Long defaultId = 1L;

    public StatController(StatRepo statRepo) {
        this.statRepo = statRepo;
    }

    @GetMapping("/get")
    public Stat getStat(){
        return statRepo.findById(defaultId).get();
    }

}
