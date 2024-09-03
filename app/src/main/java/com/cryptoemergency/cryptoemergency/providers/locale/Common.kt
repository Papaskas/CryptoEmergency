package com.cryptoemergency.cryptoemergency.providers.locale

data class Lang(
    val newsFeed: String,
    val news: String,
    val exchanges: String,
    val exchangers: String,
    val chat: String,
    val users: String,
    val ICORating: String,
    val startups: String,
    val workWeb3: String,
    val career: String,
    val academy: String,
    val wallet: String,
    val home: String,
    val more: String,

    val share: String,
    val copyUrl: String,
    val save: String,
    val unsubscribe: String,
    val report: String,
    val cancel: String,
    val url: String,
    val description: String,
    val moreDetails: String,
    val socialNetworks: String,

    val tabs: Tabs,
    val profile: Profile,
    val newsFeedSection: NewsFeedSection,
    val socialNetworksSection: SocialNetworksSection,
    val labels: Labels,
    val languages: Languages,
    val inputsErrorMessage: InputsErrorMessage,
    val httpExceptionMessage: HttpExceptionMessage,
    val titles: Titles,
    val filter: Filter,
)

data class Profile(
    val subscriptions: String,
    val subscribers: String,
)

data class NewsFeedSection(
    val myFeed: String,
    val emptyTitle: String,
    val emptyDescription: String,
    val addNews: String,
    val showMore: String,
)

data class SocialNetworksSection(
    val emptyTitle: String,
    val emptyDescription: String,
    val addMore: String,
    val addSocialNetwork: String,
)

data class Tabs(
    val generalFeed: String,
    val subscriptionFeed: String,
    val allCems: String,
    val cemsSubscriptions: String,
    val tagFeed: String,
    val socialNetworks: String,
    val bookmarks: String,
)

data class Labels(
    val yourName: String,
    val username: String,
    val aboutMe: String,
    val specialization: String,
    val birthday: String,
    val dateOfRegistration: String,
    val language: String,
    val password: String,
)

data class InputsErrorMessage(
    val isEmail: String,
    val hasUppercase: String,
    val hasLowercase: String,
    val hasDigit: String,
    val hasSpecialChar: String,
    val onlyLetter: String,
    val onlyNumber: String,
    val isPhoneNumber: String,
    val isFullName: String,
    val isDomainName: String,
    val isURL: String,
    val withoutSpaces: String,
    val notSpecialSymbol: String,
    val isIPv4: String,
    val isIPv6: String,
    val isUUID: String,
    val notEmpty: String,
    val isEquals: String,
    val inRange: String,
)

data class HttpExceptionMessage(
    val IOException: String,
    val serializationException: String,
    val unknownHostException: String,
    val forbidden: String,
    val methodNotAllowed: String,
    val tooManyRequests: String,
    val requestTimeout: String,
    val internalServerError: String,
    val internalClientError: String,
)

data class Languages(
    val russian: String,
    val english: String,
)

data class Titles(
    val myProfile: String,
    val changeProfile: String,
    val addPhoto: String,
    val addSocialNetworks: String,
    val comments: String,
    val postInNewsFeed: String,
    val filter: String,
)

data class Filter(
    val all: String,
    val photo: String,
    val video: String,
    val text: String,
)
