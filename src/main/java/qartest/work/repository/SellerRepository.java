package qartest.work.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import qartest.work.dto.seller.SellerResponseWithSum;
import qartest.work.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    @Query("""
        SELECT new qartest.work.dto.seller.SellerResponseWithSum(
            s.id,
            s.name,
            s.contactInfo,
            s.registrationDate,
            SUM(t.amount)
        )
        FROM Transaction t
        JOIN t.seller s
        WHERE t.transactionDate BETWEEN :from AND :to
        GROUP BY s.id, s.name, s.contactInfo, s.registrationDate
        HAVING SUM(t.amount) = :maxSum
    """)
    List<SellerResponseWithSum> findTopSeller(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("maxSum") Long maxSum
    );

    @Query("""
        SELECT SUM(t.amount)
        FROM Transaction t
        WHERE t.transactionDate BETWEEN :from AND :to
        GROUP BY t.seller
        ORDER BY SUM(t.amount) DESC
        LIMIT 1
    """)
    Long findMaxSum(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    @Query("""
        SELECT new qartest.work.dto.seller.SellerResponseWithSum(
            s.id,
            s.name,
            s.contactInfo,
            s.registrationDate,
            SUM(t.amount)
        )
        FROM Transaction t
        JOIN t.seller s
        WHERE t.transactionDate BETWEEN :from AND :to
        GROUP BY s.id, s.name, s.contactInfo, s.registrationDate
        HAVING SUM(t.amount) < :limit
        ORDER BY SUM(t.amount) ASC
    """)
    List<SellerResponseWithSum> findSellersWithSumLess(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("limit") Long limit
    );
}
