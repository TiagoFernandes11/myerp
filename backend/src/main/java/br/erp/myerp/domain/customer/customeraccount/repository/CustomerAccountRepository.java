package br.erp.myerp.domain.customer.customeraccount.repository;

import br.erp.myerp.domain.customer.customeraccount.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long>, JpaSpecificationExecutor<CustomerAccount> {

    Optional<CustomerAccount> findByUsername(String username);
}
