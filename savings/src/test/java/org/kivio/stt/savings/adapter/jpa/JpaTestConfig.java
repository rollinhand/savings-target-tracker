package org.kivio.stt.savings.adapter.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.meecrowave.jpa.api.PersistenceUnitInfoBuilder;
import org.kivio.stt.savings.domain.SavingsTarget;
import org.kivio.stt.savings.domain.Transaction;

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
                .addManagedClazz(SavingsTarget.class)
                .addManagedClazz(Transaction.class)
                .addProperty("openjpa.RuntimeUnenhancedClasses", "warn")
                .addProperty("openjpa.Log", "DefaultLevel=INFO, SQL=INFO, RUNTIME=INFO")
                // Dieses Property sorgt dafür, dass OpenJPA beim Start automatisch die Tabellen basierend auf den Entitäten erzeugt.
                // Der Wert buildSchema(ForeignKeys=true) bedeutet, dass auch Fremdschlüssel berücksichtigt werden.
                .addProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)")
                .addProperty("openjpa.jdbc.SchemaFactory", "native(ForeignKeys=true)")
                .addProperty("openjpa.jdbc.MappingDefaults", "ForeignKeyDeleteAction=restrict,JoinForeignKeyDeleteAction=restrict")
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
        source.setUrl("jdbc:h2:mem:stt;DB_CLOSE_DELAY=-1;INIT=SET REFERENTIAL_INTEGRITY TRUE");
        return source;
    }
}
