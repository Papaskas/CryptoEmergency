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
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Colors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val ethereum = "ethereum"
    private val cemCoin = "cemCoin"
    private val stonks = "stonks"
    private val notStonks = "notStonks"

    fun getString(
        colors: Colors
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
