package br.erp.myerp.backend.domain.admin.mapper;

import br.erp.myerp.backend.domain.admin.dto.AdminDTO;
import br.erp.myerp.backend.domain.admin.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminDTO adminDTO);

    AdminDTO toAdminDTO(Admin admin);
}
