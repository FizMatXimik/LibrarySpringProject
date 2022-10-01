# Library Spring Project

## Описание

Простое веб-приложение - электронная библиотека. \
Проект пишется на Spring-MVC. \
Взаимодействие с бд (PostgreSQL) осуществляется через JdbcTemplate. \
В качестве фронта используются представления с шаблонизатором Thymeleaf

## Автор

Гапликов Александр

## История версий

*  1.2.0 - Добавлен функционал создание нового клиента, редактирования информации о клиенте и удаления клиента. Параметры подключения 
к базе данных вынесены в отдельный файл. Добавлена валидация форм, в тм числе и на уникальность клиентов в системе.
*  1.1.0 - Реализован функционал просмотра всех клиентов и информации о конкретном клиенте
*  1.0.0 - Создано базовое Spring MVC приложение
*  Начальный коммит 
