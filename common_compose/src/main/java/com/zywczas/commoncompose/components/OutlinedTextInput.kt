package com.zywczas.commoncompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.zywczas.commoncompose.theme.Theme

@Composable
fun OutlinedTextInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hint: String? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = hint?.let { { Text(hint) } },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
private fun PreviewOutlinedTextInput() {
    Theme.Preview {
        OutlinedTextInput(
            value = TextFieldValue(),
            onValueChange = {},
            hint = "Hint",
        )
    }
}
