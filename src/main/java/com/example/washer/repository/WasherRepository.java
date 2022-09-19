package com.example.washer.repository;

import com.example.washer.model.Washer;
import com.example.washer.projection.OrderProjection;
import com.example.washer.projection.WasherProjection;
import com.example.washer.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface WasherRepository extends BaseRepository<Washer> {

    @Query(nativeQuery = true, value = "select\n" +
            " existWasherAllId(:ids)")
    boolean existWasherAllId(List<UUID> ids);

    @Query(nativeQuery = true, value = "select w.id,\n" +
            "       w.image as image,\n" +
            "       w.is_active as isActive,\n" +
            "       w.name as name,\n" +
            "       w.stake as stake,\n" +
            "       w.telephone_number as telephoneNumber,\n" +
            "       w.wash_company_id as washerCompanyId,\n" +
            "       wc.name as washerCompanyName\n" +
            "from washer w\n" +
            "         join wash_company wc on w.wash_company_id = wc.id\n" +
            "where lower(w.name) like concat('%', :searchName, '%') and wc.id = :washCompanyId")
    Page<WasherProjection> getAllWasherByNameAndWashCompanyId(Pageable pageable, UUID washCompanyId, String searchName);

    @Query(nativeQuery = true, value = "select cast(o.id as varchar),\n" +
            "       o.car_model     as carModel,\n" +
            "       o.car_number    as carNumber,\n" +
            "       o.client_name   as clientName,\n" +
            "       o.client_number as clientNumber,\n" +
            "       o.date          as date,\n" +
            "       o.is_active     as isActive,\n" +
            "       o.price\n" +
            "\n" +
            "from orders o\n" +
            "         join wash_company wc on wc.id = o.wash_company_id\n" +
            "         join washer w on wc.id = w.wash_company_id\n" +
            "where w.id = :washerId\n" +
            "  and o.date between :dateFrom and :dateTo\n" +
            "  and o.is_cancelled = false\n" +
            "  and o.is_active = :isActive")
    Page<OrderProjection> getAllOrderByWasherId(Pageable pageable, LocalDate dateFrom, LocalDate dateTo, UUID washerId);
}
