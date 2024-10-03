package org.kivio.stt.app.adapter.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.meecrowave.jpa.api.PersistenceUnitInfoBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class JpaConfig {
    @Produces
    public PersistenceUnitInfoBuilder unit(final DataSource ds) {
        return new PersistenceUnitInfoBuilder()
                .setUnitName("stt")
                .setDataSource(ds)
                //.setExcludeUnlistedClasses(true)
                //.addManagedClazz(User.class)
                .addProperty("openjpa.RuntimeUnenhancedClasses", "warn")
                .addProperty("openjpa.Log", "DefaultLevel=INFO, Runtime=INFO, Tool=INFO, Enhance=INFO");
    }

    @Produces // dbcp2 datasource for instance
    @ApplicationScoped
    public DataSource dataSource() {
        final BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("org.h2.Driver");
        //source.setUrl("jdbc:h2:mem:stt");
        source.setUrl("jdbc:h2:~/stt");
        return source;
    }
}
