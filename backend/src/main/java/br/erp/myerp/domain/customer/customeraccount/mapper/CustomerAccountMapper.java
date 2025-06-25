package br.erp.myerp.domain.customer.customeraccount.mapper;

import br.erp.myerp.domain.customer.customeraccount.dto.CustomerAccountDTO;
import br.erp.myerp.domain.customer.customeraccount.entity.CustomerAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAccountMapper {

    CustomerAccountDTO toCustomerAccountDTO(CustomerAccount customerAccount);
}
