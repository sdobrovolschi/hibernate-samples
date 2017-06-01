package com.sdobrovolschi.hibernate.cartesianproduct.infrastructure.persistence.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.sdobrovolschi.hibernate.DBTest;
import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.Client;
import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.ClientRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stanislav Dobrovolschi
 */
@DataSet("datasets/cartesianproduct/clients-with-contracts-and-projects.xml")
public class CartesianProductProblemTest extends DBTest {

    @Autowired
    private ClientRepository clientRepository;

    // MultipleBagFetchException can be fixed by changing lists to sets
    @Test
    public void cartesianProduct() {
        List<Client> clients = clientRepository.getAllWithContractsAndProjects();

        assertThat(clients).hasSize(3);
    }
}