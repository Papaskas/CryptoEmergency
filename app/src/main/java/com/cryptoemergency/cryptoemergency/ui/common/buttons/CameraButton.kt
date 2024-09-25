package com.cryptoemergency.cryptoemergency.ui.common.buttons

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.theme.Theme
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CameraButton(
    modifier: Modifier = Modifier,
    onPhotoCaptured: (Uri?) -> Unit,
) {
    val context = LocalContext.current

    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            onPhotoCaptured(photoUri.value)
        } else {
            onPhotoCaptured(null)
        }
    }

    IconButton(
        modifier = modifier
            .clip(CircleShape)
            .background(Theme.colors.surface1),
        onClick = { openCamera(context, launcher, photoUri) },
    ) {
        Icon(
            painter = painterResource(R.drawable.camera),
            contentDescription = "Сделать фото", // TODO: перевод
            tint = Theme.colors.text6,
            modifier = Modifier
                .padding(8.dp)
                .size(16.dp)
        )
    }
}

private fun openCamera(
    context: Context,
    launcher: ManagedActivityResultLauncher<Uri, Boolean>,
    photoUri: MutableState<Uri?>
) {
    val photoFile = createImageFile(context)
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        photoFile
    )
    photoUri.value = uri
    launcher.launch(uri)
}

@Throws(IOException::class)
private fun createImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir,
    )
}
