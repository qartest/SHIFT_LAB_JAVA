package qartest.work.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qartest.work.dto.transaction.TransactionDto;
import qartest.work.dto.transaction.TransactionResponse;
import qartest.work.service.TransactionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService service;

    @GetMapping("/allTransaction")
    public ResponseEntity<List<TransactionResponse>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransactionResponse> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getBySellerId/{id}")
    public ResponseEntity<List<TransactionResponse>> getBySellerId(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getBySellerId(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(service.create(transactionDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TransactionResponse> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}
