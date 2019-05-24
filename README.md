# pravega-auth-handler-impl-example

An example service provider implementation for Pravega AuthHandler

## Setting it up in Pravega Standalone

Modify startStandalone task: 

```gradle
task startStandalone(type: JavaExec) {
        main = "io.pravega.local.LocalPravegaEmulator"
        classpath = sourceSets.main.runtimeClasspath
        
        // Adds the jar to the classpath
        classpath += files('/path/to/authhandler-impl-example-1.0-SNAPSHOT.jar')

        systemProperties System.getProperties()
        systemProperties 'logback.configurationFile' : ...
        ...
        
        // Add these to configure ClientConfig for internal use inside the cluster
        systemProperties 'pravega.client.auth.method' : "StaticTokenAuthHandler"
        systemProperties 'pravega.client.auth.token' : "static-token"

        if (systemProperties.get("extDirs") != null) {
            classpath += files(systemProperties.get("extDirs"))
        }
        ....

    }

```


