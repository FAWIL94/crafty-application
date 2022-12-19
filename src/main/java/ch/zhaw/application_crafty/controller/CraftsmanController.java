package ch.zhaw.application_crafty.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.application_crafty.model.Craftsman;
import ch.zhaw.application_crafty.model.CraftsmanCreateDTO;
import ch.zhaw.application_crafty.repository.CraftsmanRepository;


@RestController
@RequestMapping("/api/craftsman")
public class CraftsmanController {
    @Autowired
    CraftsmanRepository craftsmanRepository;
   

@PostMapping("")
public ResponseEntity<Craftsman> createCraftsman(@RequestBody CraftsmanCreateDTO cDTO) {
    Craftsman cDAO = new Craftsman(cDTO.getCompanyName(), cDTO.getStreet(), cDTO.getPlz(), cDTO.getCity());
    Craftsman c = craftsmanRepository.save(cDAO);
    return new ResponseEntity<>(c, HttpStatus.CREATED);
}
@GetMapping("")
    public ResponseEntity<Page<Craftsman>> getAllFreelancer(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 2;
        }
        Page<Craftsman> allCraftsmans = craftsmanRepository.findAll(PageRequest.of(page - 1, pageSize));

        return new ResponseEntity<>(allCraftsmans, HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<String> deleteAllCraftsmans(){
        craftsmanRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("DELETED");
    }
}

