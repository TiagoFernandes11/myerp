package br.erp.myerp.backend.domain.admin.controller;

import br.erp.myerp.backend.domain.admin.dto.AdminDTO;
import br.erp.myerp.backend.domain.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{username}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.get(username));
    }
}
