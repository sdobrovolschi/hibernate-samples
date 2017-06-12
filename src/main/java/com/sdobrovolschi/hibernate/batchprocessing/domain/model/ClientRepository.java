package com.sdobrovolschi.hibernate.batchprocessing.domain.model;

import java.util.Collection;

/**
 * @author Stanislav Dobrovolschi
 */
public interface ClientRepository {

    void add(Client client);

    void addAll(Collection<? extends Client> clients);

    Client get(Long id);

    void delete(Long id);
}
