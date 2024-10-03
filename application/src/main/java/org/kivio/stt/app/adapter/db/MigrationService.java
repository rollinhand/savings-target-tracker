package org.kivio.stt.app.adapter.db;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.sql.DataSource;

@Slf4j
@ApplicationScoped
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MigrationService {
    private DataSource dataSource;

    @Inject
    public MigrationService(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void onStartup(@Observes @Initialized(ApplicationScoped.class) Object event) {
        this.migrate();
    }

    protected void migrate() {
        log.info("Configuring Flyway...");
        final Flyway flyway = Flyway.configure()
                .dataSource(this.dataSource)
                .load();

        log.info("Starting migration...");
        flyway.migrate();
    }
}
