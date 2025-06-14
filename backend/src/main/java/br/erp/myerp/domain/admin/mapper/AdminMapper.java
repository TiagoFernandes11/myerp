package br.erp.myerp.domain.admin.mapper;

import br.erp.myerp.domain.admin.dto.AdminDTO;
import br.erp.myerp.domain.admin.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminDTO adminDTO);

    AdminDTO toAdminDTO(Admin admin);
}
