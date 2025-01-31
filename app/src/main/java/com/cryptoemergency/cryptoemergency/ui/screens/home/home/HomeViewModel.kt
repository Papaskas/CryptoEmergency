package com.cryptoemergency.cryptoemergency.ui.screens.home.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.provides.entity.ColorsEntity
import com.papaska.data.qualifiers.StorageQualifiers
import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.useCases.remote.token.InitTokenUseCase
import com.papaska.domain.useCases.storage.LocalStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val initTokenUseCase: InitTokenUseCase,
    @StorageQualifiers.TokenStorage private val tokenStorage: LocalStorageUseCase<TokenEntity>,
) : ViewModel() {
    private val ethereum = "ethereum"
    private val cemCoin = "cemCoin"
    private val stonks = "stonks"
    private val notStonks = "notStonks"

    init {
        viewModelScope.launch {
            if (tokenStorage.get().isNotEmpty()) return@launch

            val res = initTokenUseCase()

            if (res is ApiResponse.Success) {
                val token = res.headers[DomainHttpHeaders.AUTHORIZATION]!!.toString()

                tokenStorage.put(token)
            }
        }
    }

    fun getString(
        colors: ColorsEntity
    ): AnnotatedString {
        return buildAnnotatedString {
            appendInlineContent(ethereum, ethereum)
            append(" ETH / USDT   ")
            appendInlineContent(stonks, stonks)
            withStyle(style = SpanStyle(color = colors.success)) {
                append("$ 3541.82 ")
            }
            append(" -1.56% ")
            append("      ")
            appendInlineContent(cemCoin, cemCoin)
            append(" CEM / USDT   ")
            appendInlineContent(notStonks, notStonks)
            withStyle(style = SpanStyle(color = colors.error)) {
                append("$ 0.3670 ")
            }
            append(" -1.56% ")
        }
    }

    val inlineContent = mapOf(
        ethereum to InlineTextContent(
            placeholder = Placeholder(
                width = 16.sp,
                height = 16.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            ),
            children = {
                Image(
                    painter = painterResource(R.drawable.ethereum),
                    contentDescription = null,
                )
            }
        ),
        cemCoin to InlineTextContent(
            placeholder = Placeholder(
                width = 16.sp,
                height = 16.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            ),
            children = {
                Image(
                    painter = painterResource(R.drawable.cem_coin),
                    contentDescription = null,
                )
            }
        ),
        stonks to InlineTextContent(
            placeholder = Placeholder(
                width = 16.sp,
                height = 16.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            ),
            children = {
                Image(
                    painter = painterResource(R.drawable.stonks),
                    contentDescription = null,
                )
            }
        ),
        notStonks to InlineTextContent(
            placeholder = Placeholder(
                width = 16.sp,
                height = 16.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            ),
            children = {
                Image(
                    painter = painterResource(R.drawable.not_stonks),
                    contentDescription = null,
                )
            }
        ),
    )
}
