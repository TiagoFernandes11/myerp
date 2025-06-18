package br.erp.myerp.domain.order.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Order {
    private long id;
    private long clientId;
    private List<Long> productsId;
    private BigDecimal total;
}
