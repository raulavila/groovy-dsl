package com.raulavila.dsl

import groovy.transform.TypeChecked

@TypeChecked
abstract class LogConfigScript extends Script{
    
    final Map<String, AppenderConfig> appenders = [:]
    final Map<String, LogConfig> loggers = [:]

    AppenderConfig appender(String name, @DelegatesTo(AppenderConfig) Closure config) {
        def appender = new AppenderConfig(name)
        appenders[name] = appender
        
        def cl = config.clone() as Closure
        cl.delegate = appender
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        cl.call()
        
        println appender.path
        appender
    }

    LogConfig log(String pkg) {
        loggers[pkg] = new LogConfig(pkg)
    }

    def propertyMissing(String name) {
        def appender = appenders[name]
        
        if(!appender)
            throw new MissingPropertyException(name, getClass())
        
        return appender
    }
    
}
