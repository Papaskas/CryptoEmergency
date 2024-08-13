package com.cryptoemergency.cryptoemergency.lib

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.cryptoemergency.cryptoemergency.navigation.Routes
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.LocalSnackbar
import kotlinx.coroutines.launch

/**
 * Настраиваемая функция, которая отслеживает изменения в предоставленных параметрах и выполняет
 * соответствующие действия
 *
 * @param message - Изменяемое состояние типа String?, представляющее отображаемое сообщение.
 * При изменении значения этого состояния будет отображаться сообщение.
 * После отображения сообщение будет удалено.
 * Если этот параметр не указан, его значение по умолчанию равно null.
 *
 * @param redirect экземпляр класса Redirect, который представляет действие навигации.
 * При изменении значения этого параметра навигация будет перенаправлена по указанному маршруту.
 * Если свойство `popBackStack` экземпляра Redirect имеет значение true, стек навигации будет возвращен обратно.
 * Если этот параметр не указан, его значение по умолчанию равно null.
 * */
@Composable
fun Listener(
    message: MutableState<String?> = mutableStateOf(null),
    redirect: Redirect? = null,
) {
    ListenerMessage(message)
    ListenerRedirect(redirect)
}

/**
 * Настраиваемая функция, которая отслеживает изменения в предоставленных параметрах и выполняет
 * соответствующие действия
 *
 * @param redirect экземпляр класса Redirect, который представляет действие навигации.
 * При изменении значения этого параметра навигация будет перенаправлена по указанному маршруту.
 * Если свойство `popBackStack` экземпляра Redirect имеет значение true, стек навигации будет возвращен обратно.
 * Если этот параметр не указан, его значение по умолчанию равно null.
 * */
@Composable
fun Listener(
    redirect: Redirect? = null,
) {
    ListenerRedirect(redirect)
}


/**
 * Настраиваемая функция, которая отслеживает изменения в предоставленных параметрах и выполняет
 * соответствующие действия
 *
 * @param message - Изменяемое состояние типа String?, представляющее отображаемое сообщение.
 * При изменении значения этого состояния будет отображаться сообщение.
 * После отображения сообщение будет удалено.
 * Если этот параметр не указан, его значение по умолчанию равно null.
 *
 * */
@Composable
fun Listener(
    message: MutableState<String?> = mutableStateOf(null),
) {
    ListenerMessage(message)
}

@Composable
private fun ListenerMessage(
    message: MutableState<String?>,
) {
    val scope = rememberCoroutineScope()
    val snackbar = LocalSnackbar.current

    LaunchedEffect(message.value) {
        scope.launch {
            message.value?.let { msg ->
                snackbar.showSnackbar(
                    message = msg,
                    withDismissAction = true,
                )

                message.value = null
            }
        }
    }
}

@Composable
private fun ListenerRedirect(
    redirect: Redirect?,
) {
    val navController = LocalNavController.current
    LaunchedEffect(redirect?.route) {
        redirect?.route?.let { url ->
            if (redirect.popBackStack) {
                navController.popBackStack()
            }

            navController.navigate(url)
        }
    }
}

data class Redirect(
    var route: Routes,
    var popBackStack: Boolean = false,
)