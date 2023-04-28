package learn.bookservice.common

import io.r2dbc.spi.ConnectionFactory
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ScriptUtils
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import reactor.core.publisher.Mono
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@Tag("integration-test")
abstract class AbstractTestcontainersIntegrationTest {

    @Autowired
    lateinit var connectionFactory: ConnectionFactory

    fun executeScriptBlocking(sqlScript: String) {
        Mono.from(connectionFactory.create())
            .flatMap <Any> { connection -> ScriptUtils.executeSqlScript(connection, ClassPathResource(sqlScript))}.block()
    }

    companion object {
        private val postgres: PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres:13.3"))
            .apply {
                this.withDatabaseName("testDb")
                    .withUsername("root")
                    .withPassword("123456")
                    .withInitScript("schema.sql")
                    .withReuse(true)
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url", AbstractTestcontainersIntegrationTest::r2dbcUrl)
            registry.add("spring.r2dbc.username", postgres::getUsername)
            registry.add("spring.r2dbc.password", postgres::getPassword)
            registry.add("spring.r2dbc.pool.enabled") {true}
            registry.add("spring.r2dbc.pool.max-size") {50}
        }

        fun r2dbcUrl(): String {
            return "r2dbc:postgresql://${postgres.host}:${postgres.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT)}/${postgres.databaseName}"
        }

        @JvmStatic
        @BeforeAll
        internal fun setUp(): Unit {
            postgres.start()
        }
    }
}