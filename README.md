
[![Build Status](https://buildhive.cloudbees.com/job/slemesle/job/selma/badge/icon)](https://buildhive.cloudbees.com/job/slemesle/job/selma/)


h1. Selma you know for mapping

Just add Selma to your build, define a Mapper interface:

```java
@Mapper
public interface SelmaMApper {

    OutBean asOutBean(InBean in);

}
```

Then ? Well just use the generated Mapper:

```java

    SelmaMapper mapper = XMapper.mapper(SelmaMapper.class).build();

    OutBean res = mapper.asOutBean(in);

```

And voilà !

h2. Features




Nothing to tell for now