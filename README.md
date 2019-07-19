# pravega-auth-handler-impl-example

An example service provider implementation for Pravega AuthHandler

## Building the Example

```gradle
$ gradlew clean build
```

## Deploying the Example  

### Deploying the Example in Pravega Standalone by Modifying the Gradle Config

Modify startStandalone task in `build.gradle`: 

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
        systemProperties 'pravega.client.auth.loadDynamic' : "true"
        systemProperties 'pravega.client.auth.method' : "StaticTokenAuthHandler"
        systemProperties 'pravega.client.auth.token' : "static-token"

        if (systemProperties.get("extDirs") != null) {
            classpath += files(systemProperties.get("extDirs"))
        }
        ....

    }

```

```
        /*Properties properties = new Properties();
        properties.setProperty("pravega.client.auth.loadDynamic", "true");
        properties.setProperty("pravega.client.auth.method", "CustomMethod");
        properties.setProperty("pravega.client.auth.token", "static-token");
        System.setProperties(properties);*/
```


