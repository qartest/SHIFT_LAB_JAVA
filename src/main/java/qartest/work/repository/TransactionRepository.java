package qartest.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import qartest.work.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySeller_Id(Long sellerId);
    @Transactional
    @Modifying
    @Query("DELETE FROM Transaction t WHERE t.seller.id = :sellerId")
    void deleteBySellerId(@Param("sellerId") Long sellerId);
}
