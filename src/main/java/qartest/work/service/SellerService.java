package qartest.work.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qartest.work.dto.ModelMapper;
import qartest.work.dto.PeriodRequest;
import qartest.work.dto.seller.SellerDto;
import qartest.work.dto.seller.SellerResponse;
import qartest.work.dto.seller.SellerResponseWithSum;
import qartest.work.error.exception.NotFound;
import qartest.work.error.exception.SaveProblem;
import qartest.work.model.Seller;
import qartest.work.repository.SellerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SellerService {
    private final ModelMapper mapper;
    private final SellerRepository sellerRepository;

    @Transactional
    public SellerResponse getById(Long id){
        return mapper.toSellerResponse(sellerRepository.findById(id).orElseThrow(() -> new NotFound("Can not found seller with id: " + id.toString())));
    }

    @Transactional
    public List<SellerResponse> getAll(){
        return sellerRepository.findAll().stream().map(mapper::toSellerResponse).collect(Collectors.toList());
    }

    @Transactional
    public SellerResponse create(SellerDto sellerDto){
        Seller seller = mapper.toSeller(sellerDto);
        seller.setRegistrationDate(LocalDateTime.now());
        try{
            seller = sellerRepository.save(seller);
            return mapper.toSellerResponse(seller);
        }catch (DataIntegrityViolationException e){
            throw new SaveProblem("Can not save the seller");
        }
    }


    @Transactional
    public SellerResponse update(Long id, SellerDto sellerDto){
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new NotFound("Can not found seller with id: " + id.toString()));

        mapper.map(sellerDto, seller);

        try{
            seller = sellerRepository.save(seller);
            return mapper.toSellerResponse(seller);
        }catch (DataIntegrityViolationException e){
            throw new SaveProblem("Can not save the seller");
        }
    }

    @Transactional
    public SellerResponse delete(Long id){
        SellerResponse sellerResponse = mapper.toSellerResponse(sellerRepository.findById(id).orElseThrow(() -> new NotFound("Can not found seller with id: " + id.toString())));
        sellerRepository.deleteById(id);
        return sellerResponse;
    }

    @Transactional
    public List<SellerResponseWithSum> findTopSellers(PeriodRequest periodRequest){
        return sellerRepository.findTopSeller(periodRequest.getStartPeriod(), periodRequest.getEndPeriod(), sellerRepository.findMaxSum(periodRequest.getStartPeriod(), periodRequest.getEndPeriod()));
    }

    @Transactional
    public List<SellerResponseWithSum> findSellerWithBigSum(Long sum, PeriodRequest periodRequest){
        return sellerRepository.findSellersWithSumLess(periodRequest.getStartPeriod(), periodRequest.getEndPeriod(), sum);
    }


}
