package learn.bookservice.common

import learn.bookservice.configs.R2dbcConfiguration
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import

@DataR2dbcTest
@Target(AnnotationTarget.CLASS)
@ExtendWith(RunSqlExtension::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(R2dbcConfiguration::class)
@Tag("integration")
annotation class TestcontainersIntegrationTest ()