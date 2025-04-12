package com.zywczas.commoncompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.commoncompose.theme.Spacing
import com.zywczas.commoncompose.theme.Theme

@Composable
fun GuestListItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        val horizontalPadding = Spacing.screenBorder
        val verticalPadding = Spacing.listItemVerticalOuter

        Text(
            text = title,
            modifier = Modifier
                .padding(
                    top = verticalPadding,
                    start = horizontalPadding,
                    end = horizontalPadding,
                ),
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = subtitle,
            modifier = Modifier
                .padding(
                    bottom = verticalPadding,
                    start = horizontalPadding,
                    end = horizontalPadding,
                ),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGuestListItem() {
    Theme.Preview {
        GuestListItem(
            title = "Vin Diesiel",
            subtitle = "Zones: cosplay, movie",
            onClick = {}
        )
    }
}
