package com.sdobrovolschi.hibernate.batchprocessing.infrastructure.persistence.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.sdobrovolschi.hibernate.DBTest;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.Client;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.ClientRepository;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.Project;
import com.sdobrovolschi.hibernate.batchprocessing.domain.model.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.sdobrovolschi.hibernate.batchprocessing.domain.model.Currency.GBP;
import static com.sdobrovolschi.hibernate.batchprocessing.domain.model.ProjectCode.of;
import static java.math.BigDecimal.valueOf;
import static java.time.Month.*;
import static java.time.YearMonth.of;

/**
 * @author Stanislav Dobrovolschi
 */
public class JpaProjectRepositoryTest extends DBTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    // can be fixed by
    // 1. hibernate.jdbc.batch_size
    // 2. Session-level JDBC batch size
    @Test
    public void batchInsert() {
        Project project = new Project(of("AV452245"), "Project1");
        project.forecastRevenue(of(2017, JANUARY.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, FEBRUARY.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, MARCH.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, APRIL.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, MAY.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, JUNE.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, JULY.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, AUGUST.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, SEPTEMBER.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, OCTOBER.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, NOVEMBER.getValue()), GBP.amountOf(valueOf(15000)));
        project.forecastRevenue(of(2017, DECEMBER.getValue()), GBP.amountOf(valueOf(15000)));

        projectRepository.add(project);

        entityManager.flush();

        assertThatDataSource().hasInsertCount(2);
    }

    // can be fixed by
    // 1. hibernate.jdbc.batch_size
    // 2. Session-level JDBC batch size
    @DataSet("datasets/batchprocessing/project-revenue.xml")
    @Test
    public void batchUpdate() {
        Project project = projectRepository.get(of("AB325687"));

        project.forecastRevenue(of(2017, JANUARY.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, FEBRUARY.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, MARCH.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, APRIL.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, MAY.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, JUNE.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, JULY.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, AUGUST.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, SEPTEMBER.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, OCTOBER.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, NOVEMBER.getValue()), GBP.amountOf(valueOf(7500)));
        project.forecastRevenue(of(2017, DECEMBER.getValue()), GBP.amountOf(valueOf(7500)));

        entityManager.flush();

        assertThatDataSource().hasUpdateCount(1);
    }

    // can be fixed by
    // 1. hibernate.jdbc.batch_size
    // 2. Session-level JDBC batch size
    @DataSet("datasets/batchprocessing/project-revenue.xml")
    @Test
    public void batchDelete() {
        Project project = projectRepository.get(of("AB325687"));

        project.clearRevenueForecast(of(2017, JANUARY.getValue()));
        project.clearRevenueForecast(of(2017, FEBRUARY.getValue()));
        project.clearRevenueForecast(of(2017, MARCH.getValue()));
        project.clearRevenueForecast(of(2017, APRIL.getValue()));
        project.clearRevenueForecast(of(2017, MAY.getValue()));
        project.clearRevenueForecast(of(2017, JUNE.getValue()));
        project.clearRevenueForecast(of(2017, JULY.getValue()));
        project.clearRevenueForecast(of(2017, AUGUST.getValue()));

        entityManager.flush();

        assertThatDataSource().hasDeleteCount(1);
    }

    // can be fixed by
    // hibernate.order_inserts=true
    @Test
    public void unorderedBatchInsert() {
        clientRepository.add(new Client("Client1"));

        projectRepository.add(new Project(of("IU452245"), "Project1"));

        clientRepository.add(new Client("Client2"));

        projectRepository.add(new Project(of("FG452245"), "Project2"));

        clientRepository.add(new Client("Client3"));

        projectRepository.add(new Project(of("CF452245"), "Project3"));

        entityManager.flush();

        assertThatDataSource().hasInsertCount(2);
    }

    // can be fixed by
    // hibernate.order_updates=true
    @DataSet("datasets/batchprocessing/clients-projects.xml")
    @Test
    public void unorderedBatchUpdate() {
        clientRepository.get(1L).changeName("Client1changed");
        projectRepository.get(of("AB325687")).changeName("Project1changed");
        clientRepository.get(2L).changeName("Client2changed");
        projectRepository.get(of("BC325687")).changeName("Project2changed");
        clientRepository.get(3L).changeName("Client3changed");
        projectRepository.get(of("CD325687")).changeName("Project3changed");

        entityManager.flush();

        assertThatDataSource().hasUpdateCount(2);
    }

    // can not be fixed
    @DataSet("datasets/batchprocessing/clients-projects.xml")
    @Test
    public void unorderedBatchDelete() {
        clientRepository.delete(1L);
        projectRepository.delete(of("AB325687"));
        clientRepository.delete(2L);
        projectRepository.delete(of("BC325687"));
        clientRepository.delete(3L);
        projectRepository.delete(of("CD325687"));

        entityManager.flush();

        assertThatDataSource().hasDeleteCount(2);
    }

    // java.lang.OutOfMemoryError: GC overhead limit exceeded
    // can be fixed by manual batching
    @Test
    public void batchInsertMultipleAggregates() {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            clients.add(new Client("Client" + i));
        }

        clientRepository.addAll(clients);

        entityManager.flush();

        assertThatDataSource().hasInsertCount(200);
    }
}