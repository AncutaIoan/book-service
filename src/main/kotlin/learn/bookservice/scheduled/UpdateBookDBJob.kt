package learn.bookservice.scheduled

import learn.bookservice.dto.books.BooksResponse
import learn.bookservice.repodto.BookEntity
import learn.bookservice.service.BookService
import learn.bookservice.service.GoogleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import reactor.core.Disposable
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toFlux

@Configuration
@EnableScheduling
class SchedulerConfig(val googleService: GoogleService,val bookService: BookService) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(SchedulerConfig::class.java)
        private const val MAIN_TYPE = "programming"
    }

    @Scheduled(cron = "*/30 * * * * ?")
    fun refreshBookDatabase() {
        googleService.getBooks(MAIN_TYPE).map {
            log.info("Starting to save books")
            preserveBooks(it)
        }.subscribe()
    }

    private fun preserveBooks(it: BooksResponse) {
        val books = it.items.map { item ->
            BookEntity(
                selfLink = item.selfLink,
                saleInfo = item.saleInfo.toString(),
                volumeInfo = item.volumeInfo.toString()
            )
        }
        bookService.saveBooks(books).subscribe()
    }
}