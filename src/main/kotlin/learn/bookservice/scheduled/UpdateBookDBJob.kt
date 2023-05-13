package learn.bookservice.scheduled

import learn.bookservice.dto.books.Item
import learn.bookservice.repodto.BookEntity
import learn.bookservice.service.BookService
import learn.bookservice.service.GoogleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import reactor.core.scheduler.Schedulers

@Configuration
@EnableScheduling
class SchedulerConfig(val googleService: GoogleService, val bookService: BookService) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(SchedulerConfig::class.java)
        private const val MAIN_TYPE = "cooking"
    }

    @Scheduled(cron = "*/30 * * * * ?")
    fun refreshBookDatabase() {
        log.info("Starting to save books")
        googleService.getBooks(MAIN_TYPE)
            .map { createBookEntitiesFrom(it.items) }
            .flatMap { bookService.saveBooks(it) }
            .doOnError { log.error("There was an error during update", it) }
            .doOnSuccess { log.info("Manage to finish update.") }
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe()
    }


    private fun createBookEntitiesFrom(items: List<Item>) =
        items.map(BookEntity::from)
}