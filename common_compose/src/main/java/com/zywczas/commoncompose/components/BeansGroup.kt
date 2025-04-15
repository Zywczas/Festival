package com.zywczas.commoncompose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zywczas.commoncompose.theme.Spacing
import com.zywczas.commoncompose.theme.Theme

@Composable
fun BeansGroup(
    beans: List<BeansViewEntity>,
    onClick: (index: Int) -> Unit
) {
    LazyRow {
        item {
            Spacer(Modifier.width(Spacing.screenBorder))
        }

        itemsIndexed(beans) { index, item ->
            Bean(
                viewEntity = item,
                onClick = { onClick(index) }
            )

            if (index < beans.lastIndex) {
                Spacer(Modifier.width(12.dp))
            }
        }

        item {
            Spacer(Modifier.width(Spacing.screenBorder))
        }
    }
}

data class BeansViewEntity(
    val text: String,
    val isChecked: Boolean
)

@Composable
private fun Bean(
    viewEntity: BeansViewEntity,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(4.dp)
    val modifier = if (viewEntity.isChecked) {
        Modifier
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surface, shape = shape)
    } else {
        Modifier
            .clickable(onClick = onClick)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), shape = shape)
    }

    Column(modifier) {
        Text(
            text = viewEntity.text,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            maxLines = 1,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun PreviewBeansGroup() {
    Theme.Preview {
        BeansGroup(
            beans = listOf(
                BeansViewEntity("Bean", isChecked = false),
                BeansViewEntity("Bean", isChecked = true),
                BeansViewEntity("Bean", isChecked = false),
                BeansViewEntity("Bean", isChecked = true),
            ),
            onClick = {}
        )
    }
}
