package br.erp.myerp.backend.domain.admin.service;

import br.erp.myerp.backend.domain.admin.dto.AdminDTO;
import br.erp.myerp.backend.domain.admin.entity.Admin;
import br.erp.myerp.backend.domain.admin.mapper.AdminMapper;
import br.erp.myerp.backend.domain.admin.repository.AdminRepository;
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
