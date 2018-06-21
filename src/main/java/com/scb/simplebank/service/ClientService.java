package com.scb.simplebank.service;

import com.scb.simplebank.entity.Account;
import com.scb.simplebank.entity.Client;
import com.scb.simplebank.exception.AccountNotFoundException;
import com.scb.simplebank.exception.ClientNotFoundException;
import com.scb.simplebank.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    // find all clients
    public List<Client> findAll() {
        LOGGER.debug("Finding all client entries");
        return clientRepository.findAll();
    }

    // find client with an id
    public Client findById(Long id) {
        LOGGER.debug("Finding client with id: {}", id);
        Client found = clientRepository.findOne(id);
        if (found == null) {
            throw new ClientNotFoundException("No client found with id: " + id);
        }
        return found;
    }

    // find client accounts with an id
    public List<Account> findAccounts(Long id) {
        return findById(id).getAccounts();
    }

    // find specific account id
    public Account findAccountById(Long clientId, Long accountId) {
        LOGGER.debug("Finding account with clientId: {}, accountId: {}", clientId, accountId);
        Optional<Account> opt = findById(clientId).getAccounts().stream().filter(a -> a.getId() == accountId).findFirst();
        if (!opt.isPresent()) {
            throw new AccountNotFoundException("No account found with id: " + accountId);
        }
        return opt.get();
    }

}
