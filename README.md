# Дипломный проект. API тесты для платформы [REQRES](https://reqres.in/)
<img alt="reqres logo" src="images/logo/reqres.png" />

## О платформе REQRES
Reqres.in - платформа для обучения взаимодействию с REST API.

## Содержание
- <a href="#stack">Стек</a>
- <a href="#testcases">Тест-кейсы</a>
- <a href="#running">Запуск автотестов</a>
- <a href="#report">отчет Allure</a>
- <a href="#testops">Интеграция с Allure TestOps</a>
- <a href="#jira">Интеграция с Jira</a>
- <a href="#telegram">Telegram notification via bot</a>

<a id="stack"></a>
## Стек
<p  align="center">

<code><img width="5%" title="IntelliJ IDEA" src="images/logo/Idea.svg"></code>
<code><img width="5%" title="Java" src="images/logo/Java.svg"></code>
<code><img width="5%" title="Junit5" src="images/logo/Junit5.svg"></code>
<code><img width="5%" title="Gradle" src="images/logo/Gradle.svg"></code>
<code><img width="5%" title="REST Assured" src="images/logo/Ra.png"></code>
<code><img width="5%" title="GitHub" src="images/logo/GitHub.svg"></code>
<code><img width="5%" title="Jenkins" src="images/logo/Jenkins_logo.svg"></code>
<code><img width="5%" title="Allure Report" src="images/logo/Allure.svg"></code>
<code><img width="5%" title="Allure TestOps" src="images/logo/Allure_TO.svg"></code>
<code><img width="5%" title="Jira" src="images/logo/Jira.svg"></code>
<code><img width="5%" title="Telegram" src="images/logo/Telegram.svg"></code>
</p>

Тесты в данном проекте написаны на языке ```Java``` с использованием фреймворка для тестирования ```Selenide```  и подключенной библиотекой для тестирования API ```REST Assured```, сборщик - ```Gradle```. ```JUnit 5``` задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для запуска браузеров используется ```Selenoid```.
Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота. Так же реализована интеграция с <code>Allure TestOps</code> и <code>Jira</code>.

<a id="testcases"></a>
## Тест-кейсы
- Успешный логин
- Логин без контента
- Неуспешный логин, пароль не введен
- Получение списка пользователей
- Получение одного пользователя
- Получение 404 кода и ошибки при некорректном запросе

<a id="running"></a>
## Запуск автотестов из консоли
```
gradle clean owner_tests
```

## <img alt="Jenkins" src="images/logo/Jenkins_logo.svg" width="40" height="40"/>[ Запуск автотестов из Jenkins](https://jenkins.autotests.cloud/job/024_fedyaabro_Diploma_reqres/)
Build with Parameters --> Build, при необходимости можно добавить комментарий или сменить окружение
<img title="Allure Overview" src="images/attachment/jenkins_start.png">

<a id="report"></a>
## <img alt="Allure Reports" src="images/logo/Allure.svg" width="40" height="40"/> [Отчеты Allure](https://jenkins.autotests.cloud/job/024_fedyaabro_Diploma_reqres/6/allure/#)
<img title="Allure Behaviors" src="images/attachment/allure_behaviors.png"> 
<img title="Allure Suites" src="images/attachment/allure_overview.png"> 

<a id="testops"></a>
## <img alt="Allure TestOps" src="images/logo/Allure_TO.svg" width="40" height="40"/> [Интеграция с Allure TestOps](https://allure.autotests.cloud/project/4216/dashboards)
<img title="TestOps Results" src="images/attachment/allure_to_launches.png"> 
<img title="TestOps Suites" src="images/attachment/allure_to_cases.png"> 

<a id="jira"></a>
## <img alt="Jira" src="images/logo/Jira.svg" width="40" height="40"/> [Интеграция с Jira](https://jira.autotests.cloud/browse/HOMEWORK-1213)
<img title="Jira integration" src="images/attachment/jira.png"> 

<a id="telegram"></a>
## <img alt="Telegram" src="images/logo/Telegram.svg" width="40" height="40"/>Нотификация в бот 
<img title="Telegram notification via bot" src="images/attachment/tg.png">  
