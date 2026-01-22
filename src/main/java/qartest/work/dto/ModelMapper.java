package qartest.work.dto;


import org.mapstruct.*;
import qartest.work.dto.seller.SellerDto;
import qartest.work.dto.seller.SellerResponse;
import qartest.work.dto.transaction.TransactionDto;
import qartest.work.dto.transaction.TransactionResponse;
import qartest.work.model.Seller;
import qartest.work.model.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModelMapper {
    SellerResponse toSellerResponse(Seller seller);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    Seller toSeller(SellerDto sellerDto);

    @Mapping(target = "sellerId", source = "seller.id")
    TransactionResponse toTransactionResponse(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "transactionDate", ignore = true)
    Transaction toTransaction(TransactionDto transactionDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    void map(SellerDto sellerDto, @MappingTarget Seller seller);
}
