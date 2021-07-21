# BungeeCord Client Brand Fixewr
Fixes a bug within Bungeecord where the MC|Brand payload doesn't get send to the other server 

# How to use
Install the Bungeecord-Fixer.jar on your Bungeecord server
Install the Spigot-Fixer.jar on all your Spigot servers

(You will need to setup a redis server & configure them in the config.yml on all of the servers and bungeecord)

# API

###### Usage
```java
// will return the client brand from the bungeecord
BrandFixAPI.getClientBrand(player.getUniqueId());
```

###### Maven Installation
```xml
    <repositories>
        <repository>
            <id>Sparky</id>
            <url>https://nexus.sparky.ac/repository/Sparky/</url>
      </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>me.rhys.brandfix</groupId>
            <artifactId>Spigot</artifactId>
            <version>1.0</version>
        </dependency>
    </dependencies>
```
