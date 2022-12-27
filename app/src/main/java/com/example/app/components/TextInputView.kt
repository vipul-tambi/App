package com.example.app.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun LabelView(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.caption,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.primaryVariant
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextInputField(label:String,value:String,onValueChanged:(String)->Unit){
    val keyboardController=LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            ,
        value = value,
        onValueChange = {
            onValueChanged(it)
        },

        label = { LabelView(title = label) },
        textStyle = MaterialTheme.typography.body1,
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(imeAction =ImeAction.Search),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}



@Composable
fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = MaterialTheme.colors.primaryVariant,
    focusedLabelColor = MaterialTheme.colors.primary,
    focusedIndicatorColor = MaterialTheme.colors.primary,
    unfocusedIndicatorColor = Color.LightGray,
    cursorColor = MaterialTheme.colors.primary,
    placeholderColor = MaterialTheme.colors.onSurface,
    disabledPlaceholderColor = MaterialTheme.colors.onSurface,
    backgroundColor =MaterialTheme.colors.primary.copy(0.1f)
)
