package com.scb.simplebank.service;


import com.scb.simplebank.entity.Account;
import com.scb.simplebank.entity.Client;
import com.scb.simplebank.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ClientServiceTests {

    @TestConfiguration
    static class ClientServiceTestConfiguration {
        @Bean
        public ClientService clientService() {
            return new ClientService();
        }
    }

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    private Client mockClient = new Client("Client A", "Last A");
    private List<Client> mockClients = Arrays.asList(
            mockClient,
            new Client("Client B", "Last B"),
            new Client("Client C", "Last C")
    );
    private Account accA = new Account();
    private Account accB = new Account();
    private List<Account> accounts = Arrays.asList(accA, accB);

    @Before
    public void setUp() {
        mockClients.forEach(c -> c.setId(Long.valueOf(123)));
        Mockito.when(clientRepository.findAll()).thenReturn(mockClients);

        accA.setBalance(123.123);
        accA.setName("Acc A");
        accA.setId(Long.valueOf(1));
        accB.setBalance(-1.123);
        accB.setName("Acc B");
        accB.setId(Long.valueOf(100));
        mockClient.setAccounts(accounts);
        Mockito.when(clientRepository.findOne(Mockito.anyLong())).thenReturn(mockClient);
    }

    @Test
    public void testFindAll()  {
        assertThat(clientService.findAll()).isEqualTo(mockClients);
    }

    @Test
    public void testFindById()  {
        assertThat(clientService.findById(Mockito.anyLong())).isEqualTo(mockClient);
    }

    @Test
    public void testFindAccounts()  {
        assertThat(clientService.findAccounts(Mockito.anyLong())).isEqualTo(accounts);
    }

    @Test
    public void testFindAccountById()  {
        assertThat(clientService.findAccountById(Mockito.anyLong(),Long.valueOf(100))).isEqualTo(accB);
    }

}
