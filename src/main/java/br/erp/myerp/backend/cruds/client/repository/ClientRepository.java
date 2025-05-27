package br.erp.myerp.backend.cruds.client.repository;

import br.erp.myerp.backend.cruds.client.dto.ClientResponseDTO;
import br.erp.myerp.backend.cruds.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT new br.erp.myerp.backend.cruds.client.dto.ClientResponseDTO(c.id, c.firstName, c.lastName, c.email, c.ddd, c.cellphone, c.birthday, c.cpf) FROM Client c")
    List<ClientResponseDTO> findAllResponseDTO();

    Optional<Client> findByCpf(String cpf);
}
