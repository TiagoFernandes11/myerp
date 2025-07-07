package br.myerp.store_backend.customeraccount.mapper;


import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerAccountCreateDTO;
import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerAccountResponseDTO;
import br.myerp.store_backend.customeraccount.entity.CustomerAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAccountMapper {
    CustomerAccount toCustomerAccount(CustomerAccountCreateDTO customerAccountCreateDTO);
    CustomerAccountResponseDTO toCustomerAccountDTO(CustomerAccount customerAccount);
}
