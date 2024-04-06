# Security - Задание №3

### Spring Security (SecurityContextHolder, SecurityFilterChain, Expressions)<br/><br/>

Самым фундаментальным объектом в Spring Security является `SecurityContextHolder`. 
В нем хранится информация о текущем контексте безопасности приложения, 
который включает в себя подробную информацию о пользователе (принципале), 
работающим с приложением. Spring Security использует объект `Authentication`, 
пользователя авторизованной сессии.

Пользователь – это просто `Object`. В большинстве случаев он может быть
приведен к классу `UserDetails`. `UserDetails` можно представить, 
как адаптер между БД пользователей и тем что требуется Spring Security 
внутри `SecurityContextHolder`.

Для создания `UserDetails` используется интерфейс `UserDetailsService` с единственным методом:

`UserDetails loadUserByUsername(String username) throws UsernameNotFoundException`

Java конфигурация имеет следующий вид:

```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("ru.tkachev.security")
public class SecurityJavaConfig {
    
}
```

Security Expressions:
* hasRole, hasAnyRole
* hasAuthority, hasAnyAuthority
* permitAll, denyAll
* isAnonymous, isRememberMe, isAuthenticated, isFullyAuthenticated 
* principal, authentication 
* hasPermission

Выражения `hasRole`, `hasAnyRole` отвечают за определение контроля доступа или 
авторизации к определенным URL-адресам и методам в приложении:

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    .antMatchers("/auth/admin/*").hasRole("ADMIN")
    .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
}
```

В данном примере доступ ко всем ссылкам, начинающимся с `/auth/`, имеют только пользователи, 
которые входят в систему с правами пользователя или администратора. 
Более того, для доступа к ссылкам, начинающимся с `/auth/admin/`, необходимо 
иметь роль администратора.

Роли и полномочия `hasAuthority`, `hasAnyAuthority` в Spring аналогичны. Основное 
отличие состоит в том, что роли имеют особую семантику — начиная с Spring Security 4 
префикс `ROLE_` добавляется автоматически (если его еще нет) любым методом, связанным с ролью.

Преимущество использования полномочий заключается в том, что вообще не нужно 
использовать префикс `ROLE_`.

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("user1").password(encoder().encode("user1Pass"))
      .authorities("USER")
      .and().withUser("admin").password(encoder().encode("adminPass"))
      .authorities("ADMIN");
}
```

`permitAll`, `denyAll` позволяют либо разрешить доступ к какому-либо URL-адресу 
в сервисе, либо отказать в доступе.

С помощью конфигурации `.antMatchers("/*").permitAll()` можно разрешить всем 
пользователям (как анонимным, так и вошедшим в систему) доступ к странице, 
начинающейся с «/» (например, к домашней странице).

Чтобы запретить доступ ко всему пространству URL `.antMatchers("/*").denyAll()`.

Указав `.antMatchers("/*").anonymous()` в конфигурации Java, определяется доступ 
всем неавторизованным пользователям доступ к главной странице.

Если необходимо защитить веб-сайт, чтобы каждый, кто его использует, 
должен был войти в систему, необходимо использовать метод `isAuthenticated()`.

Также есть два дополнительных выражения `isRememberMe()` и `isFullyAuthenticated()`. 
Благодаря использованию файлов cookie Spring позволяет запоминать пользователя, 
поэтому ему нет необходимости каждый раз входить в систему.

Выражения `principal`, `authentication` позволяют получить доступ к основному объекту, 
представляющему текущего авторизованного (или анонимного) пользователя, 
и текущему объекту аутентификации из `SecurityContext` соответственно.

Можно использовать `principal` для загрузки электронной почты пользователя, 
аватара или любых других данных, которые доступны вошедшему в систему пользователю.

`authentication` предоставляет информацию о полном объекте аутентификации вместе 
с предоставленными ему полномочиями.

`SecurityFilterChain` в Spring Security представляет собой цепочку фильтров, 
которые обрабатывают запросы для обеспечения безопасности приложения. 
Каждый фильтр в цепочке выполняет определенную задачу, такую как аутентификация 
пользователя, авторизация доступа к ресурсам и т. д. Цепочка фильтров обрабатывает 
запросы по порядку, пока не будет найден подходящий фильтр 
для выполнения необходимых действий.
