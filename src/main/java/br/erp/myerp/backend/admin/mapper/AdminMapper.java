package br.erp.myerp.backend.admin.mapper;

import br.erp.myerp.backend.admin.dto.AdminDTO;
import br.erp.myerp.backend.admin.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toAdmin(AdminDTO adminDTO);

    AdminDTO toAdminDTO(Admin admin);
}
