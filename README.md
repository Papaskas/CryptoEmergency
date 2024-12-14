# <a id="urls">Полезные ссылки</a>

> [Dagger hilt - внедрение зависимостей](https://dagger.dev/hilt)<br/>
> [Detekt - линтер](https://plugins.jetbrains.com/plugin/10761-detekt)<br/>
> [Безопасная маршрутизация](https://developer.android.com/guide/navigation/design/type-safety)<br/>
> [Store, ProtoStore - локальное хранилище](https://developer.android.com/topic/libraries/architecture/datastore)<br/>
> [SvgToCompose](https://www.composables.com/svgtocompose)<br/>
> [Room - БД](https://developer.android.com/jetpack/androidx/releases/room)
> [Локаль по умолчанию](https://habr.com/ru/companies/alconost/articles/581926)

# Lint

> В проекте установлен `detekt` - `app/config/detekt/detekt.yml`<br/>
> Для его инициализации в `Android studio` нужно скачать плагин [detekt](#urls)<br/>
> Его активация описана в описании плагина

# Логирование

> В `release` сборке логирования - нет.<br/>
> `Log.*`, `print` и `println` - в release сборке убираются в конфиге `proguard-rules-without-logs.pro`<br/>
> Логирование сетевых запросов убирается проверкой `BuildConfig.DEBUG` в файле `api/network/HttpClient`

# Locale

> Все локали определены в стнадратном для андроид `xml`</br>
> Для добавления новых локалей необходимо добавить не только новый `xml`, но и в `gradle.android.defaultConfig.resourceConfigurations`</br>
> Язык по умолчанию обязательно [английский](#urls)

# Тема, цвета, значения, иконки к темам, типография

> Все файлы находятся в папке `providers/theme/Theme`<br/>
> Использование цветовой схемы:
>```kotlin
>  Text(color = Theme.colors.primary)
>```

# Routing

> Используются типобезопасные маршруты, смотри [официальную документацию](#urls)<br/>
> Все файлы по роутингу находятся в `navigation`<br/>
> Для контроля роутинга необходимо пользоваться методом `LocalNavController.current` из
  `providers/LocalNavController`<br/>
> Для перехода по страницам нужно использовать не строки, а импортировать обьект `Destionation` из
  `navigation/Destionation`<br/>

---

# Разное

> В хранилище Bitrix24 находятся файлы `signingConfigs`<br/>
> Основная документация находится в `GitWiki`
