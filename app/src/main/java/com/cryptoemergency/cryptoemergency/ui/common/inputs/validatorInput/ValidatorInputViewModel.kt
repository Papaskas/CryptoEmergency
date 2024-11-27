package com.cryptoemergency.cryptoemergency.ui.common.inputs.validatorInput

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cryptoemergency.cryptoemergency.lib.validation.Validator
import com.cryptoemergency.cryptoemergency.lib.validation.validation
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ValidatorInputViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    val errorMessage = mutableStateOf<String?>(null)
    val successMessage = mutableStateOf<String?>(null)

    /**
     * Внутренний метод валидации параметров текстового поля
     * */
    fun validate(
        text: String,
        validators: List<Validator>,
        hasError: MutableState<Boolean>,
        @StringRes successMessage: Int?,
    ) {
        val res = context.resources

        validation(text, validators) { hasErr, errMsg ->
            if (hasErr) {
                this.errorMessage.value = errMsg
                this.successMessage.value = null
                hasError.value = true
            } else {
                this.errorMessage.value = null
                this.successMessage.value = if(successMessage != null) res.getString(successMessage) else null
                hasError.value = false
            }
        }
    }
}
