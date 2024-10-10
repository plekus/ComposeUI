package ru.example.composeui.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import composeui.composeapp.generated.resources.Res
import composeui.composeapp.generated.resources.button_subtitle
import composeui.composeapp.generated.resources.button_title
import composeui.composeapp.generated.resources.coin_box
import composeui.composeapp.generated.resources.plus
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.example.composeui.theme.AppTheme
import ru.example.composeui.utils.toImageBitmap

@Composable
fun MainView(
    state: MainUiState,
    onButtonClick: () -> Unit
) = Box(
    modifier = Modifier.fillMaxSize()
        .background(
            color = AppTheme.colors.white
        ),
    contentAlignment = Alignment.Center
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = state.image?.toImageBitmap()
        if (bitmap != null) {
            Image(
                modifier = Modifier.size(200.dp),
                bitmap = bitmap,
                contentDescription = null
            )
        } else {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(Res.drawable.coin_box),
                contentDescription = null
            )
        }

        Text(
            text = stringResource(Res.string.button_subtitle),
            style = AppTheme.typography.paragraph14,
            color = AppTheme.colors.primary
        )

        Box(
            Modifier.fillMaxWidth()
                .height(50.dp)
                .background(
                    color = AppTheme.colors.secondary,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { onButtonClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 12.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(Res.drawable.plus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = AppTheme.colors.white)
                )

                Text(
                    text = stringResource(Res.string.button_title),
                    style = AppTheme.typography.button14,
                    color = AppTheme.colors.white
                )
            }
        }
    }
}