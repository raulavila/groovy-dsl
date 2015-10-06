package com.raulavila.dsl

import groovy.transform.Canonical
import groovy.transform.ToString
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
@ToString(includePackage =  false)
class LogConfig {
    final String name
    
    AppenderConfig appender
    String level

    LogConfig(String name) {
        this.name = name
    }

    LogConfig to(AppenderConfig appender) {
        this.appender = appender    
        this
    }

    LogConfig at(LogLevel level) {
        this.level = level
        this
    }
}
