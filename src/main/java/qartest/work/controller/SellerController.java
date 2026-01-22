package qartest.work.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qartest.work.dto.PeriodRequest;
import qartest.work.dto.seller.SellerDto;
import qartest.work.dto.seller.SellerResponse;
import qartest.work.dto.seller.SellerResponseWithSum;
import qartest.work.service.SellerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final SellerService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<SellerResponse> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<SellerResponse>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<SellerResponse> create(@Valid @RequestBody SellerDto sellerDto){
        return new ResponseEntity<>(service.create(sellerDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<SellerResponse> update(@Valid @RequestBody SellerDto sellerDto, @PathVariable("id") Long id){
        return new ResponseEntity<>(service.update(id, sellerDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SellerResponse> delete(@PathVariable Long id){
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }

    @GetMapping("/findTopSellers")
    public ResponseEntity<List<SellerResponseWithSum>> findTopSellers(@Valid @RequestBody PeriodRequest periodRequest){
        return new ResponseEntity<>(service.findTopSellers(periodRequest), HttpStatus.OK);
    }

    @GetMapping("/findSellersWithSumLess/{sum}")
    public ResponseEntity<List<SellerResponseWithSum>> findSellersWithSumLess(@Valid @RequestBody PeriodRequest periodRequest, @PathVariable("sum") Long sum){
        return new ResponseEntity<>(service.findSellerWithBigSum(sum, periodRequest), HttpStatus.OK);
    }

}
