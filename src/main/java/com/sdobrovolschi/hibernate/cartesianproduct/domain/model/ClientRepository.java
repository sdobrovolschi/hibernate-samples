package com.sdobrovolschi.hibernate.cartesianproduct.domain.model;

import java.util.List;

/**
 * @author Stanislav Dobrovolschi
 */
public interface ClientRepository {

    List<Client> getAllWithContractsAndProjects();
}
