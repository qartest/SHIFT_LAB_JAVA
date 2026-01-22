package qartest.work.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qartest.work.dto.ModelMapper;
import qartest.work.dto.transaction.TransactionDto;
import qartest.work.dto.transaction.TransactionResponse;
import qartest.work.error.exception.NotFound;
import qartest.work.error.exception.SaveProblem;
import qartest.work.model.Seller;
import qartest.work.model.Transaction;
import qartest.work.repository.SellerRepository;
import qartest.work.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<TransactionResponse> getAll(){
        return transactionRepository.findAll().stream().map(modelMapper::toTransactionResponse).collect(Collectors.toList());
    }

    @Transactional
    public TransactionResponse getById(Long id){
        return modelMapper.toTransactionResponse(transactionRepository.findById(id).orElseThrow(() -> new NotFound("Transaction not found with id: " + id)));

    }

    @Transactional
    public TransactionResponse create(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.toTransaction(transactionDto);
        Seller seller = sellerRepository.findById(transactionDto.getSellerId()).orElseThrow(() -> new NotFound("Can not found seller with id: " + transactionDto.getSellerId()));

        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setSeller(seller);

        try {
            transaction = transactionRepository.save(transaction);
            return modelMapper.toTransactionResponse(transaction);
        } catch (DataIntegrityViolationException e) {
            throw new SaveProblem("Can not save the Transaction");
        }
    }

    @Transactional
    public TransactionResponse delete(Long id){
        TransactionResponse transactionResponse = modelMapper.toTransactionResponse(transactionRepository.findById(id).orElseThrow((() -> new NotFound("Transaction not found with id: " + id))));
        transactionRepository.deleteById(id);
        return transactionResponse;
    }


    @Transactional
    public List<TransactionResponse> getBySellerId(Long id){
        return transactionRepository.findBySeller_Id(id).stream().map(modelMapper::toTransactionResponse).collect(Collectors.toList());
    }




}
