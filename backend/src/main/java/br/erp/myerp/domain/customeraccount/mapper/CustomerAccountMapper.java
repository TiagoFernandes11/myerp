package br.erp.myerp.domain.customeraccount.mapper;

import br.erp.myerp.domain.customeraccount.dto.CustomerAccountDTO;
import br.erp.myerp.domain.customeraccount.entity.CustomerAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAccountMapper {

    CustomerAccountDTO toCustomerAccountDTO(CustomerAccount customerAccount);
}
