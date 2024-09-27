package org.kivio.stt.user.adapter.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.meecrowave.jpa.api.PersistenceUnitInfoBuilder;
import org.kivio.stt.user.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class JpaTestConfig {
    @Produces
    public PersistenceUnitInfoBuilder unit(final DataSource ds) {
        return new PersistenceUnitInfoBuilder()
                .setUnitName("stt")
                .setDataSource(ds)
                .setExcludeUnlistedClasses(true)
                .addManagedClazz(User.class)
                .addProperty("openjpa.RuntimeUnenhancedClasses", "supported")
                .addProperty("openjpa.Log", "DefaultLevel=INFO, SQL=TRACE")
                // Dieses Property sorgt dafür, dass OpenJPA beim Start automatisch die Tabellen basierend auf den Entitäten erzeugt.
                // Der Wert buildSchema(ForeignKeys=true) bedeutet, dass auch Fremdschlüssel berücksichtigt werden.
                .addProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=false)")
                .addProperty("javax.persistence.schema-generation.database.action", "drop-and-create")
                .addProperty("javax.persistence.schema-generation.create-source", "metadata");
    }

    @Produces // dbcp2 datasource for instance
    @ApplicationScoped
    public DataSource dataSource() {
        final BasicDataSource source = new BasicDataSource();
        source.setDriverClassName("org.h2.Driver");
        // Dieser Parameter sorgt dafür, dass die In-Memory-Datenbank nicht geschlossen wird,
        // sobald die Verbindung geschlossen wird (wichtig während des Testens)
        source.setUrl("jdbc:h2:mem:stt;DB_CLOSE_DELAY=-1;");
        return source;
    }
}
