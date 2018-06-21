package com.scb.simplebank.controller;

import com.scb.simplebank.entity.Account;
import com.scb.simplebank.entity.Client;
import com.scb.simplebank.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientRestController.class, secure = false)
public class ClientRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testFindAll() throws Exception {
        List<Client> mockClients = Arrays.asList(
                new Client("Client A", "Last A"),
                new Client("Client B", "Last B"),
                new Client("Client C", "Last C")
        );
        mockClients.forEach(c -> c.setId(Long.valueOf(123)));
        Mockito.when(clientService.findAll()).thenReturn(mockClients);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/clients").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":123,\"firstName\":\"Client A\",\"lastName\":\"Last A\",\"status\":null},{\"id\":123,\"firstName\":\"Client B\",\"lastName\":\"Last B\",\"status\":null},{\"id\":123,\"firstName\":\"Client C\",\"lastName\":\"Last C\",\"status\":null}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void testFindById() throws Exception {
        Client mockClient = new Client("First", "Last");
        mockClient.setId(Long.valueOf(123));
        mockClient.setStatus("active");
        Mockito.when(clientService.findById(Mockito.anyLong())).thenReturn(mockClient);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/clients/123").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":123,\"firstName\":\"First\",\"lastName\":\"Last\",\"status\":\"active\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testFindAccounts() throws Exception {
        Account accA = new Account();
        accA.setBalance(123.123);
        accA.setName("Acc A");
        accA.setId(Long.valueOf(1));
        Account accB = new Account();
        accB.setBalance(-123.123);
        accB.setName("Acc B");
        accB.setId(Long.valueOf(-1));
        List<Account> accs = Arrays.asList(
          accA, accB
        );
        Mockito.when(clientService.findAccounts(Mockito.anyLong())).thenReturn(accs);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/clients/123/accounts").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":1,\"balance\":123.123,\"name\":\"Acc A\"},{\"id\":-1,\"balance\":-123.123,\"name\":\"Acc B\"}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void testFindAccountById() throws Exception {
        Account accA = new Account();
        accA.setBalance(123.123);
        accA.setName("Acc A");
        accA.setId(Long.valueOf(1));
        Mockito.when(clientService.findAccountById(Mockito.anyLong(), Mockito.anyLong())).thenReturn(accA);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/clients/123/accounts/123").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":1,\"balance\":123.123,\"name\":\"Acc A\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
