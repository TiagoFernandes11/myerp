package br.erp.myerp.backend.cruds.admin.service;

import br.erp.myerp.backend.cruds.admin.dto.AdminDTO;
import br.erp.myerp.backend.cruds.admin.entity.Admin;
import br.erp.myerp.backend.cruds.admin.mapper.AdminMapper;
import br.erp.myerp.backend.cruds.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    public void create(AdminDTO adminDTO){
        Admin admin = adminMapper.toAdmin(adminDTO);
        adminRepository.save(admin);
    }
}
