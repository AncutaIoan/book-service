package learn.bookservice.common

@Target(AnnotationTarget.FUNCTION)
annotation class RunSql (val scripts: Array<String>)