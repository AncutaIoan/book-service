package learn.bookservice.repository

import learn.bookservice.common.AbstractTestcontainersIntegrationTest
import learn.bookservice.common.RunSql
import learn.bookservice.common.TestcontainersIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.test.StepVerifier

@TestcontainersIntegrationTest
internal class BookRepositoryTest: AbstractTestcontainersIntegrationTest() {

    @Autowired
    lateinit var bookRepository: BookRepository


    @Test
    @RunSql(["/book/insertBook.sql"])
    fun whenFindById_thenReturnBankAccount() {
        StepVerifier.create(bookRepository.getBookById(1))
            .expectNextMatches { it.id == 1 && it.selfLink == "link" && it.saleInfo == "just 3 pesos" && it.volumeInfo == "nvm"}
            .verifyComplete()
    }
}