package qartest.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qartest.work.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySeller_Id(Long sellerId);
}
