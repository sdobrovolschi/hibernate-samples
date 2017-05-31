package com.sdobrovolschi.hibernate;

import com.github.database.rider.core.DBUnitRule;
import net.ttddyy.dsproxy.asserts.ProxyTestDataSource;
import net.ttddyy.dsproxy.asserts.assertj.DataSourceProxyAssertions;
import net.ttddyy.dsproxy.asserts.assertj.ProxyTestDataSourceAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class DBTest {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(() -> jdbcTemplate.getDataSource().getConnection());

    @Before
    public void setUp() {
        ((ProxyTestDataSource) jdbcTemplate.getDataSource()).reset();
    }

    protected ProxyTestDataSourceAssert assertThatDataSource() {
        return DataSourceProxyAssertions.assertThat((ProxyTestDataSource) jdbcTemplate.getDataSource());
    }
}
