package br.erp.myerp.backend.cruds.admin.repository;

import br.erp.myerp.backend.cruds.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
