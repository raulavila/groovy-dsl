package com.raulavila.dsl

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer


class App {
    public static void main(String[] args) {
        def model = new Binding()
        def compilerConfig = new CompilerConfiguration()
        compilerConfig.scriptBaseClass = LogConfigScript.name

        def customizer = new ImportCustomizer()
        customizer.addStaticStars("com.raulavila.dsl.LogLevel")   // auto-import
        compilerConfig.compilationCustomizers << customizer
        
        def shell = new GroovyShell(model, compilerConfig)
        def script = shell.parse(
                new File("log-config.groovy").newReader("UTF-8"),
                "logConfig") as LogConfigScript

        try {
            script.run()
        }
        catch (MissingPropertyException e) {
            println "ERROR: no appender defined with the name ${e.property}"
        }
        

        script.appenders
        
        println ">> Appenders: ${script.appenders}"
        println ">> Loggers: ${script.loggers}"
        println model.variables
    }
}
