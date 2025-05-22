package br.erp.myerp.client;

import br.erp.myerp.client.controller.ClientController;
import br.erp.myerp.client.dto.ClientDTO;
import br.erp.myerp.client.entity.Client;
import br.erp.myerp.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClientTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ClientService clientService;

    @Test
    public void createClient(){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("Test");
        clientDTO.setLastName("Test");
        clientDTO.setEmail("teste@teste.com");
        clientDTO.setDdd("99");
        clientDTO.setCellphone("999999999");
        clientDTO.setCpf("99999999999");

        try{
            clientController.createClient(clientDTO);
        } catch (IllegalArgumentException e){
            System.out.println("This cpf is already registered");
        }

        assertNotNull(clientService.find("99999999999"));
    }
}
