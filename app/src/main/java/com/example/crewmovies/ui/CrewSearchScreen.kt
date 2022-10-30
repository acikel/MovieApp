package com.example.crewmovies.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.crewmovies.core.domain.models.PeopleResultModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CrewSearchScreen(
    modifier: Modifier = Modifier,
    crewSearchViewModel: CrewSearchViewModel = hiltViewModel()
){
    Column(
        modifier = modifier
    ) {
        CrewSearchTextField(searchName = crewSearchViewModel.peopleSearchName, onUserSearchChanged = crewSearchViewModel::fillListWithSearchResult)
        CrewSearchList(urlToPicture = crewSearchViewModel::getPainterFromUrl, searchResultList = crewSearchViewModel.uiPeopleSearchListState.value)
    }
}

@Composable
fun CrewSearchTextField(
    searchName: String,
    onUserSearchChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchName,
        //singleLine = true,
        modifier = modifier.fillMaxWidth(),
        onValueChange =  { onUserSearchChanged() },
        /*
        label = {
            if (isGuessWrong) {
                Text(stringResource(R.string.wrong_guess))
            } else {
                Text(stringResource(R.string.enter_your_word))
            }
        },
        isError = isGuessWrong,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardDone() }
        ),
         */
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrewSearchList(
    urlToPicture : ( String ) -> String,
    searchResultList : ArrayList<PeopleResultModel>,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(searchResultList.size) { index ->
            searchResultList[index].profilePicturePath?.let { urlToPicture(it) }
                ?.let { searchResultList[index].name?.let { it1 -> CrewSearchCard(it, it1) } }
        }
    }
}

@Composable
fun CrewSearchCard(
    url: String,
    crewName : String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(model = url,
            contentDescription = null,
            //Crops image to fit into the parent box.
            contentScale = ContentScale.Crop,
            //modifier = Modifier.clip(CircleShape)
        )
        //Put crew name into another box to be able to align it inside the parent box.
        Box(
            modifier = modifier.fillMaxSize().padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            //TODO text style in theme rein tun.
            Text(crewName, style = TextStyle(color = Color.White, fontSize = 16.sp))
        }
    }
}

@Preview
@Composable
fun CrewSearchTextFieldPreview(){
    CrewSearchTextField(searchName = "Steven Spielberg", onUserSearchChanged = {})
}