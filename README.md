[![Build Status](https://buildhive.cloudbees.com/job/slemesle/job/selma/badge/icon)](https://buildhive.cloudbees.com/job/slemesle/job/selma/)


# Selma you know for mapping

Just add Selma to your build, define a Mapper interface:

```java
@Mapper
public interface SelmaMapper {

    OutBean asOutBean(InBean in);

}
```

Then ? Well just use the generated Mapper:

```java

    SelmaMapper mapper = Selma.mapper(SelmaMapper.class).build();

    OutBean res = mapper.asOutBean(in);

```

And voilà !

## Features

* Generate code for mapping bean to bean matching fields to fields
** Support for nested bean
** Bean should respect Java property convention
* Maps Enum using identical values
* Maps Collection any to any
* Maps Map any to any
* Use strict memory duplication for all fields
* Support for Factory to instantiate beans is out of the box
* Support Type to Type custom mapping using custom mapping static methods
* Gives full feedback at compilation time
* Break build when mapping does not work Say good bye to mapping errors in production

## Usage

First add selma and selma-processor to your pom dependencies:
```xml
        <!-- scope provided because the processor is only needed for the compiler -->
        <dependency>
            <groupId>fr.xebia.extras</groupId>
            <artifactId>selma-processor</artifactId>
            <version>0.1-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>

        <!-- This is the only real dependency you will have in youre binaries -->
        <dependency>
            <groupId>fr.xebia.extras</groupId>
            <artifactId>selma</artifactId>
            <version>0.1-SNAPSHOT</version>
        </dependency>
```

Then, as I said earlier, build youre interface with @Mapper annotation and enjoy.

Checkout the example module to have a deeper look.

**Help needed, please report issues and ask for features :)**

