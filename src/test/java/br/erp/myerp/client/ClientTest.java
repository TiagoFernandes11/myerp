package br.erp.myerp.client;

import br.erp.myerp.backend.client.controller.ClientController;
import br.erp.myerp.backend.client.dto.ClientCreateDTO;
import br.erp.myerp.backend.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClientTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ClientService clientService;

    @Test
    public void createClient(){
        ClientCreateDTO clientCreateDTO = new ClientCreateDTO();
        clientCreateDTO.setFirstName("Test");
        clientCreateDTO.setLastName("Test");
        clientCreateDTO.setEmail("teste@teste.com");
        clientCreateDTO.setDdd("99");
        clientCreateDTO.setCellphone("999999999");
        clientCreateDTO.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        clientCreateDTO.setCpf("99999999999");

        clientController.createClient(clientCreateDTO);

        assertNotNull(clientService.find("99999999999"));
    }
}
