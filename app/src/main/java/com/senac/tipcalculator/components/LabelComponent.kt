package com.senac.tipcalculator.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.senac.tipcalculator.R

@Composable
fun RowScope.LabelComponent(@StringRes labelResource: Int) {
    Text(
        text = stringResource(labelResource),
        textAlign = TextAlign.Right,
        modifier = Modifier.weight(1f)
            .padding(end = 8.dp)
    )
}