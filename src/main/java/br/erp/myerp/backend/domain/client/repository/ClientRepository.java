package br.erp.myerp.backend.domain.client.repository;

import br.erp.myerp.backend.domain.client.dto.ClientResponseDTO;
import br.erp.myerp.backend.domain.client.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT new br.erp.myerp.backend.domain.client.dto.ClientResponseDTO(c.id, c.firstName, c.lastName, c.email, c.ddd, c.cellphone, c.birthday, c.cpf) FROM Client c")
    Page<ClientResponseDTO> findAllResponseDTO(Pageable pageable);

    Optional<Client> findByCpf(String cpf);
}
