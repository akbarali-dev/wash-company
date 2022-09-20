package com.example.washer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import com.example.washer.model.Order;
import com.example.washer.projection.AnalyticsProjection;
import com.example.washer.projection.OrderProjection;
import com.example.washer.repository.base.BaseRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface OrderRepository extends BaseRepository<Order> {




    @Query(nativeQuery = true,
            value = "select cast(o.id as varchar),\n" +
                    "       o.car_model as carModel ,\n" +
                    "       o.car_number as carNumber ,\n" +
                    "       o.client_name as clientName ,\n" +
                    "       o.client_number as clientNumber ,\n" +
                    "       o.date as date,\n" +
                    "       o.is_active as isActive ,\n" +
                    "       o.price\n" +
                    "\n" +
                    "from orders o\n" +
                    "         join wash_company wc on wc.id = o.wash_company_id\n" +
                    "where wc.id = :washCompanyId\n" +
                    "  and o.date between :dateFrom and :dateTo\n" +
                    "and o.is_cancelled = false and o.is_active = :isActive")
    Page<OrderProjection> getAllOrderByCompanyId(UUID washCompanyId, Pageable pageable, LocalDate dateFrom, LocalDate dateTo, boolean isActive);

    boolean existsById(UUID id);
    boolean existsByIdAndWashCompanyId(UUID orderId, UUID companyId);

    @Query(nativeQuery = true, value = "select count(distinct o.id)         as totalOrders,\n" +
            "       count(distinct w.id)         as toalWashers,\n" +
            "       sum(distinct o.price)        as ordersSum,\n" +
            "       sum(distinct o.price) * 0.01* count(distinct w.id) as washersSum\n" +
            "from orders o\n" +
            "--      washer w\n" +
            "left  join orders_washers ow on o.id = ow.orders_id\n" +
            "left join washer w on w.id = ow.washers_id\n" +
            "where o.is_cancelled = false\n" +
            "  and o.wash_company_id = :washCompanyId\n" +
            "  and w.wash_company_id = :washCompanyId\n" +
            "and o.date between :dateFrom and :dateTo")
    AnalyticsProjection analytic(UUID washCompanyId, LocalDate dateFrom, LocalDate dateTo);


    
}
