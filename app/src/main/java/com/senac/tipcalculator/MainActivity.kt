package com.senac.tipcalculator

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senac.tipcalculator.components.LabelComponent
import com.senac.tipcalculator.components.MyTopBar
import com.senac.tipcalculator.ui.theme.TipCalculatorTheme
import com.senac.tipcalculator.viewmodels.TipViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    Scaffold (
        topBar = {
            MyTopBar()
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(16.dp)) {
            Content()

        }
    }
}

@Composable
private fun Content(tipViewModel: TipViewModel = viewModel()) {

    val tipState = tipViewModel.uiState.collectAsState()

    Row(verticalAlignment = Alignment.CenterVertically) {
        LabelComponent(labelResource = R.string.amount)
        TextField(
            value = tipState.value.amount.toString(),
            onValueChange = { tipViewModel.updateAmount(it.toFloat()) },
            modifier = Modifier.weight(3f)
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        LabelComponent(labelResource = R.string.custom)
        Slider(
            modifier = Modifier.weight(3f),
            value = tipState.value.custom.toFloat(),
            valueRange = 0f..30f,
            onValueChange = { tipViewModel.updateCustom(it.toInt()) })
    }

    Row {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "15%",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = "${tipState.value.custom}%",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1.5f)
        )
    }

    Row {
        LabelComponent(labelResource = R.string.tip)
        Text(
            text = "R$ %.2f".format(tipState.value.getTip15()),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = "R$ %.2f".format(tipState.value.getTipCustom()),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1.5f)
        )
    }
    Row {
        LabelComponent(labelResource = R.string.total)
        Text(
            text = "R$ %.2f".format(tipState.value.getTotal15()),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = "R$ %.2f".format(tipState.value.getTotalCustom()),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    TipCalculatorTheme {
        MyApp()
    }
}