package br.erp.myerp.domain.customer.customeraccount.mapper;

import br.erp.myerp.domain.customer.customeraccount.dto.CustomerAccountCreateDTO;
import br.erp.myerp.domain.customer.customeraccount.dto.CustomerAccountDTO;
import br.erp.myerp.domain.customer.customeraccount.entity.CustomerAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAccountMapper {

    CustomerAccount toCustomerAccount(CustomerAccountCreateDTO customerAccountCreateDTO);

    CustomerAccountDTO toCustomerAccountDTO(CustomerAccount customerAccount);
}
