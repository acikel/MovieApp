package com.example.crewmovies.ui.ScreenLists

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.crewmovies.R

@Composable
fun DialogBoxAddList(
    listName: String,
    onValueChange: ( String ) -> Unit,
    onDismiss: () -> Unit,
    onCreate: () -> Unit,
    context: Context
) {
    //val contextForToast = LocalContext.current.applicationContext

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    text = stringResource(id = R.string.new_list),
                    textAlign = TextAlign.Start,
                    //style = TextStyle(
                    //    fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)),
                    //    fontSize = 20.sp
                    //)
                )

                TextFieldAddList(listName, onValueChange)

                Row (
                    horizontalArrangement = Arrangement.End
                        ) {
                    TextButtonTemplate(stringResource(id = R.string.cancel), onDismiss)
                    TextButtonCheckValueTemplate(stringResource(id = R.string.create), listName, onCreate, context)
                }
            }
        }
    }
}

@Composable
fun TextFieldAddList(
    listName: String,
    onValueChange: ( String ) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.padding(start = 12.dp, end = 12.dp),
        value = listName,
        singleLine = true,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.new_list_hint)) },
    )
}

@Composable
fun TextButtonTemplate(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            /*
            color = Color(0xFF35898f),
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_regular,
                        FontWeight.Normal
                    )
                ),
                fontSize = 14.sp
            )
            */
        )
    }
}

@Composable
fun TextButtonCheckValueTemplate(
    buttonText: String,
    listName: String,
    onClick: () -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = {
            if(listName.isNullOrEmpty()){
                Toast.makeText(
                    context,
                    R.string.list_name_empty_error,
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                onClick()
            }
        },
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            /*
            color = Color(0xFF35898f),
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_regular,
                        FontWeight.Normal
                    )
                ),
                fontSize = 14.sp
            )
            */
        )
    }
}