package ru.example.composeui.ui.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composeui.composeapp.generated.resources.Res
import composeui.composeapp.generated.resources.item_camera
import composeui.composeapp.generated.resources.item_gallery
import org.jetbrains.compose.resources.stringResource
import ru.example.composeui.theme.AppTheme
import ru.example.composeui.utils.PermissionType

@Composable
fun PhotosPickerBottomSheet(
    onCameraSelect: () -> Unit,
    onGallerySelect: () -> Unit
) = Column(
    modifier = Modifier.fillMaxWidth()
        .padding(
            horizontal = 16.dp,
            vertical = 36.dp
        ),
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp)
            .clickable { onCameraSelect() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.item_camera),
            style = AppTheme.typography.paragraph14,
            color = AppTheme.colors.primary
        )
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = AppTheme.colors.primary50
    )
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp)
            .clickable { onGallerySelect() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.item_gallery),
            style = AppTheme.typography.paragraph14,
            color = AppTheme.colors.primary
        )
    }
}