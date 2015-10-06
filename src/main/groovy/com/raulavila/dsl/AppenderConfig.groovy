package com.raulavila.dsl

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@Canonical
@TypeChecked
class AppenderConfig {
    final String name
    
    String path
    long ttl = -1
    

    AppenderConfig(String name) {
        this.name = name
    }
}
