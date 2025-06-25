package br.erp.myerp.domain.customer.client.repository;

import br.erp.myerp.domain.customer.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByEmail(String email);
}
