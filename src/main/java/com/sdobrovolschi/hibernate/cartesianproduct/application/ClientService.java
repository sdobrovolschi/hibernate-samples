package com.sdobrovolschi.hibernate.cartesianproduct.application;

import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.Client;
import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional//(readOnly = true)
    public List<Client> getAll() {
        return clientRepository.getAll();
    }
}
