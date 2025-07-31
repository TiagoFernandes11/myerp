package br.myerp.store_backend.mapper;


import br.myerp.store_backend.dto.customeraccount.CustomerAccountCreateDTO;
import br.myerp.store_backend.dto.customeraccount.CustomerAccountResponseDTO;
import br.myerp.store_backend.entity.CustomerAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAccountMapper {
    CustomerAccount toCustomerAccount(CustomerAccountCreateDTO customerAccountCreateDTO);
    CustomerAccountResponseDTO toCustomerAccountDTO(CustomerAccount customerAccount);
}
