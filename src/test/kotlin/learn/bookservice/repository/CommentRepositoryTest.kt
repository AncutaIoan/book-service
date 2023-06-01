package learn.bookservice.repository

import learn.bookservice.common.AbstractTestcontainersIntegrationTest
import learn.bookservice.common.RunSql
import learn.bookservice.common.TestcontainersIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.test.StepVerifier

@TestcontainersIntegrationTest
internal class CommentRepositoryTest: AbstractTestcontainersIntegrationTest(){
    @Autowired
    lateinit var repository: CommentRepository

    @Test
    @RunSql(["/comment/insertComment.sql"])
    fun findAllByBookId() {
        StepVerifier.create(repository.findAllByBookId("123"))
                .expectNextCount(2)
                .verifyComplete()
    }
}