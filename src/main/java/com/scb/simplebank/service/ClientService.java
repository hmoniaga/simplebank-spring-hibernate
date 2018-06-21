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

    /**
     * Find all client entries
     */
    public List<Client> findAll() {
        LOGGER.debug("ClientService: findAll");
        return clientRepository.findAll();
    }

    /**
     * Find client by a specific id
     */
    public Client findById(Long id) {
        LOGGER.debug("ClientService: findById {}", id);
        Client found = clientRepository.findOne(id);
        if (found == null) {
            throw new ClientNotFoundException("No client found with id: " + id);
        }
        return found;
    }

    /**
     * Find all accounts belonging to a client
     */
    public List<Account> findAccounts(Long id) {
        LOGGER.debug("ClientService: findAccounts {}", id);
        Client found = clientRepository.findOne(id);
        if (found == null) {
            throw new ClientNotFoundException("No client found with id: " + id);
        }
        return found.getAccounts();
    }

    /**
     * Find details of a specific account
     */
    public Account findAccountById(Long clientId, Long accountId) {
        LOGGER.debug("ClientService: findAccountById clientId: {}, accountId: {}", clientId, accountId);
        Optional<Account> opt = findById(clientId).getAccounts().stream().filter(a -> a.getId() == accountId).findFirst();
        if (!opt.isPresent()) {
            throw new AccountNotFoundException("No account found with id: " + accountId);
        }
        return opt.get();
    }

}
