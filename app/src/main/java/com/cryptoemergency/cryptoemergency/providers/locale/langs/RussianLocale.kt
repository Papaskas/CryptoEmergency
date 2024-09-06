package com.cryptoemergency.cryptoemergency.providers.locale.langs

import com.cryptoemergency.cryptoemergency.providers.locale.Filter
import com.cryptoemergency.cryptoemergency.providers.locale.HttpExceptionMessage
import com.cryptoemergency.cryptoemergency.providers.locale.InputsErrorMessage
import com.cryptoemergency.cryptoemergency.providers.locale.Labels
import com.cryptoemergency.cryptoemergency.providers.locale.Lang
import com.cryptoemergency.cryptoemergency.providers.locale.Languages
import com.cryptoemergency.cryptoemergency.providers.locale.NewsFeedSection
import com.cryptoemergency.cryptoemergency.providers.locale.Profile
import com.cryptoemergency.cryptoemergency.providers.locale.SocialNetworksSection
import com.cryptoemergency.cryptoemergency.providers.locale.Tabs
import com.cryptoemergency.cryptoemergency.providers.locale.Titles

val russianLang = Lang(
    newsFeed = "Лента",
    news = "Новости",
    exchanges = "Биржи",
    exchangers = "Обменники",
    chat = "Чат",
    users = "Пользователи",
    ICORating = "ICO рейтинг",
    startups = "Стартапы",
    workWeb3 = "Работа в web3",
    career = "Карьера",
    academy = "Академия",
    wallet = "CEM кошелек",
    home = "Главная",
    more = "Еще",

    share = "Поделиться",
    copyUrl = "Скопировать ссылку",
    save = "Сохранить",
    unsubscribe = "Отписаться",
    report = "Пожаловаться",
    cancel = "Отмена",
    description = "Описание",
    url = "Ссылка",
    moreDetails = "Подробнее",
    socialNetworks = "Социальные сети",

    profile = Profile(
        subscriptions = "Подписки",
        subscribers = "Подписчики",
    ),

    tabs = Tabs(
        generalFeed = "Общая лента",
        subscriptionFeed = "Лента подписок",
        allCems = "Все Cems",
        cemsSubscriptions = "Cems подписчиков",
        tagFeed = "Лента отметок",
        socialNetworks = "Социальные сети",
        bookmarks = "Закладки",
    ),

    labels = Labels(
        yourName = "Ваше имя",
        username = "Имя пользователя",
        aboutMe = "О себе",
        specialization = "Специализация",
        birthday = "День рождения",
        language = "Язык",
        password = "Пароль",
        dateOfRegistration = "Дата регистрации",
    ),

    languages = Languages(
        russian = "Русский",
        english = "Английский"
    ),

    inputsErrorMessage = InputsErrorMessage(
        isEmail = "Некорректная почта",
        hasUppercase = "Необходима хотя бы одна заглавная буква",
        hasLowercase = "Необходима хотя бы одна строчная буква",
        hasDigit = "Необходима хотя бы одна цифра",
        hasSpecialChar = "Необходим хотя бы один спец символ",
        onlyLetter = "Допустимы только буквы",
        onlyNumber = "Допустимы только цифры",
        isPhoneNumber = "Некорректный номер телефона",
        isFullName = "",
        isDomainName = "",
        isURL = "",
        withoutSpaces = "Пробелы недопустимы",
        notSpecialSymbol = "Спец символы недопустимы",
        isIPv4 = "",
        isIPv6 = "",
        isUUID = "",
        notEmpty = "Не должно быть пустым",
        isEquals = "Строки не совпадают!",
        inRange = "Превышен диапазон возможных символов",
    ),

    httpExceptionMessage = HttpExceptionMessage(
        IOException = "Удаленный сервер недоступен",
        serializationException = "Ошибка сериализации данных",
        unknownHostException = "Нет подключения к интернету",
        forbidden = "Отказано в доступе",
        methodNotAllowed = "Не разрешенный метод",
        tooManyRequests = "Превышен лимит запросов",
        requestTimeout = "Превышено время ожидания",
        internalServerError = "Непредвиденная ошибка сервера",
        internalClientError = "Непредвиденная ошибка клиента",
    ),

    titles = Titles(
        myProfile = "Мой профиль",
        changeProfile = "Редактировать профиль",
        addPhoto = "Добавить фото",
        addSocialNetworks = "Добавить социальные сети",
        comments = "Комментарии",
        postInNewsFeed = "Пост в ленте",
        filter = "Фильтр"
    ),

    filter = Filter(
        all = "Все",
        photo = "Фото",
        video = "Видео",
        text = "Текст",
    ),

    newsFeedSection = NewsFeedSection(
        myFeed = "Моя лента",
        emptyTitle = "Социальных сетей пока нет",
        emptyDescription = "Добавьте социальных сетей\nв свой профиль",
        addNews = "Добавить социальную сеть",
        showMore = "Показать еще",
    ),

    socialNetworksSection = SocialNetworksSection(
        emptyTitle = "Социальных сетей пока нет",
        emptyDescription = "Добавьте социальных сетей\nв свой профиль",
        addMore = "Добавить еще",
        addSocialNetwork = "Добавиь социальную сеть",
    ),
)
