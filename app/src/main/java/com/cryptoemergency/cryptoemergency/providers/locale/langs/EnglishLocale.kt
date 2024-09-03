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

val englishLang = Lang(
    newsFeed = "News feed",
    news = "News",
    exchanges = "Exchanges",
    exchangers = "Exchangers",
    chat = "Chat",
    users = "Users",
    ICORating = "ICO rating",
    startups = "Startups",
    workWeb3 = "Work in Web3",
    career = "Career",
    academy = "Academy",
    wallet = "CEM wallet",
    home = "Main",
    more = "More",

    share = "Share",
    copyUrl = "Copy URL",
    save = "Save",
    unsubscribe = "Unsubscribe",
    report = "Report",
    cancel = "Cancel",
    description = "Description",
    url = "Url",
    moreDetails = "More details",
    socialNetworks = "Social networks",

    profile = Profile(
        subscriptions = "Subscriptions",
        subscribers = "Subscribers",
    ),

    tabs = Tabs(
        generalFeed = "General feed",
        subscriptionFeed = "Subscription feed",
        allCems = "All Cems",
        cemsSubscriptions = "Cems subscriptions",
        tagFeed = "Tag feed",
        socialNetworks = "Social networks",
        bookmarks = "Bookmarks",
    ),

    labels = Labels(
        yourName = "",
        username = "",
        aboutMe = "",
        specialization = "",
        birthday = "",
        language = "",
        password = "",
        dateOfRegistration = "",
    ),

    languages = Languages(
        russian = "",
        english = ""
    ),

    inputsErrorMessage = InputsErrorMessage(
        isEmail = "",
        hasUppercase = "",
        hasLowercase = "",
        hasDigit = "",
        hasSpecialChar = "",
        onlyLetter = "",
        onlyNumber = "",
        isPhoneNumber = "",
        isFullName = "",
        isDomainName = "",
        isURL = "",
        withoutSpaces = "",
        notSpecialSymbol = "",
        isIPv4 = "",
        isIPv6 = "",
        isUUID = "",
        notEmpty = "",
        isEquals = "!",
        inRange = "",
    ),

    httpExceptionMessage = HttpExceptionMessage(
        IOException = "",
        serializationException = "",
        unknownHostException = "",
        forbidden = "",
        methodNotAllowed = "",
        tooManyRequests = "",
        requestTimeout = "",
        internalServerError = "",
        internalClientError = "",
    ),

    titles = Titles(
        myProfile = "",
        changeProfile = "",
        addPhoto = "",
        addSocialNetworks = "",
        comments = "",
        postInNewsFeed = "",
        filter = "Filter"
    ),

    filter = Filter(
        all = "All",
        photo = "Photo",
        video = "Video",
        text = "Text",
    ),

    newsFeedSection = NewsFeedSection(
        myFeed = "My feed",
        emptyTitle = "",
        emptyDescription = "",
        addNews = "",
        showMore = "",
    ),

    socialNetworksSection = SocialNetworksSection(
        emptyTitle = "",
        emptyDescription = "",
        addMore = "",
        addSocialNetwork = "",
    ),
)
