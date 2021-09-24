package io.aturanj.cryptoinc.repository;

import io.aturanj.cryptoinc.dto.LiveOrderBoardDto;
import io.aturanj.cryptoinc.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true)
    List<LiveOrderBoardDto> getLiveOrderBoardConditional();
}
