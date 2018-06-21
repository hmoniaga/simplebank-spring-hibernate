package com.scb.simplebank.controller;

import com.scb.simplebank.entity.Account;
import com.scb.simplebank.entity.Client;
import com.scb.simplebank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestController {

    @Autowired
    private ClientService service;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<Client> findAll() {
        return this.service.findAll();
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public Client findById(@PathVariable("clientId") Long id) {
        return this.service.findById(id);
    }

    @RequestMapping(value = "/clients/{clientId}/accounts", method = RequestMethod.GET)
    public List<Account> findAccounts(@PathVariable("clientId") Long id) {
        return this.service.findAccounts(id);
    }

    @RequestMapping(value = "/clients/{clientId}/accounts/{accountId}", method = RequestMethod.GET)
    public Account findAccountById(@PathVariable("clientId") Long clientId, @PathVariable("accountId") Long accountId) {
        return this.service.findAccountById(clientId, accountId);
    }
}
