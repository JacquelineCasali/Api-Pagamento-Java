package com.api.cardapio.controller;

import com.api.cardapio.dto.FoodCadastroDTO;
import com.api.cardapio.dto.FoodListaDTO;
import com.api.cardapio.model.food.Food;
import com.api.cardapio.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping
    public List<FoodListaDTO> listar(){
        List<FoodListaDTO> foodList= foodRepository.findAll().stream().map(FoodListaDTO::new).toList();
        return foodList;

    }

// detalhar
//    ResponseEntity messnagem do codigo
@CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        // recupera o banco de dados
        var cardapio =foodRepository.getReferenceById(id);

        return ResponseEntity.ok(new FoodListaDTO(cardapio));
    }

    //criar
    // mensagem ResponseEntity
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping
    public ResponseEntity save(@RequestBody FoodCadastroDTO data){

        var foodData=new Food(data);
        foodRepository.save(foodData);
        //return ;
        return ResponseEntity.ok(new FoodListaDTO(foodData));

    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    @Transactional
    public Optional<ResponseEntity<Food>> editar(@PathVariable("id") long id, @RequestBody Food food){

        return  foodRepository.findById(id).map(record->{
            record.setTitle(food.getTitle());
            record.setImage(food.getImage());
            record.setPrice(food.getPrice());
            Food update=foodRepository.save(record);
            return ResponseEntity.ok(update);
        });

    }
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        // recupera o banco de dados
      foodRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }



}
