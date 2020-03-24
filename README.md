<h1>Movies's World</h1>
<h2>Test project</h2>

Clone and run project login with account:

> username: teo <br>
> password: teo123 <br>

After login ==> Whitelabel Error Page ==> done <br>
(because of not yet implement login page)<br>
When login with wrong account ==> Bad credentials <br>

<h2> Running from the Command-Line</h2>

> Option 1: use `java -jar` <br>
> Option 2: Use Spring Boot Maven plugin `mvn spring-boot: run`<br>
  (if you using maven wrapper change `mvn` -> `./mvnw` )

<h2> Spring boot properties</h2>

> link: [spring boot properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties)

<h2> Create user in mysql</h2>

> create: `CREATE USER 'moviesworld'@'localhost' IDENTIFIED BY 'moviesworld';` <br>
> permission: `GRANT ALL PRIVILEGES ON * . * TO 'moviesworld'@'localhost';`

