import com.raulavila.dsl.LogConfigScript
import groovy.transform.BaseScript

@BaseScript LogConfigScript script

appender "main", {
    path = "log.out"
    ttl = 10 * 24 * 60 * 60
}

appender "other", {
    path = "other-log.out"
}

log "org.example" to main at DEBUG

//log {
//
//}.to main at INFO