package com.example.crewmovies.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.R

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CrewSearchScreen(
    modifier: Modifier = Modifier,
    crewSearchViewModel: CrewSearchViewModel = hiltViewModel()
){
    Column(
        modifier = modifier
    ) {
        CrewSearchTextField(searchName = crewSearchViewModel.peopleSearchName, onKeyboardActionDone = { crewSearchViewModel.fillListWithSearchResult() }, onValueChange = { crewSearchViewModel.updateTextSearch(it) } )

        //val lifecycleOwner = LocalLifecycleOwner.current
        //val examplePeopleSearchFlowLifecycleAware = remember(crewSearchViewModel.uiPeopleSearchListState, lifecycleOwner) {
        //    crewSearchViewModel.uiPeopleSearchListState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        //}
        //val examplePeopleSearch: ArrayList<PeopleResultModel> by examplePeopleSearchFlowLifecycleAware.collectAsState(initial = arrayListOf())
        val examplePeopleSearch: ArrayList<PeopleResultModel> by crewSearchViewModel.uiPeopleSearchListState.collectAsState(initial = arrayListOf())

        CrewSearchList(urlToPicture = { crewSearchViewModel.getPainterFromUrl(it) }, searchResultList = examplePeopleSearch)
    }
}

@Composable
fun CrewSearchTextField(
    searchName: String,
    onKeyboardActionDone: () -> Unit,
    onValueChange: ( String ) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchName,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.search_people_hint)) },
        //isError = isGuessWrong,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onKeyboardActionDone() }
        ),

    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrewSearchList(
    urlToPicture : ( String ) -> String,
    searchResultList : ArrayList<PeopleResultModel>,
    modifier: Modifier = Modifier
){
    println("size: searchResultList$searchResultList")
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(searchResultList.size) { index ->



            if(searchResultList[index].profilePicturePath.isNullOrEmpty()){
                searchResultList[index].name?.let { it1 -> CrewSearchCard("", it1)
                }
            }else{
                searchResultList[index].name?.let { it1 -> CrewSearchCard(searchResultList[index].profilePicturePath?.let{urlToPicture(it)}, it1)
                    println("size: twelved: $it1")
                }
            }



            /*
            searchResultList[index].profilePicturePath?.let {
                println("size: tenth: $it")
                println("size: tenth is null?: ${it==null}")
                println("size: tenth is empty?: ${it.isNullOrEmpty()}")
                println("size: tenth is \"\"?: ${it==""}")
                urlToPicture(it)
            }
                ?.let { searchResultList[index].name?.let { it1 -> CrewSearchCard(it, it1)
                    println("size: eleventh: $it")
                    //println("size: eleventh is null?: ${it==null}")
                    //println("size: eleventh is empty?: ${it.isNullOrEmpty()}")
                    println("size: twelved: $it1")
                } }
            */

            /*
            searchResultList[index].name?.let { it1 -> CrewSearchCard(searchResultList[index].profilePicturePath, it1)
                    println("size: eleventh: $searchResultList[index].profilePicturePath")
                    println("size: twelved: $it1")
                }
             */
        }
    }
}

@Composable
fun CrewSearchCard(
    url: String?,
    crewName : String,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
        ) {
        Box(
            //modifier = Modifier.fillMaxSize() //with this the bottom text is not aligned correctly
            modifier = Modifier.height(200.dp)
        ) {

            //if(!url.isNullOrEmpty()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                //Crops image to fit into the parent box.
                contentScale = ContentScale.Crop,
                //modifier = Modifier.clip(CircleShape)
                modifier = Modifier
                    //.clip(CircleShape)
                    //.size(160.dp)
                    .fillMaxSize(),
                error = BitmapPainter(ImageBitmap.imageResource(R.drawable.placeholder_picture)),

                )
/*
        }else{
            Image(painter =  BitmapPainter(ImageBitmap.imageResource(R.drawable.placeholder_picture)),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    //.clip(CircleShape)
                    .fillMaxSize()
            )

        }


 */

            //Box to create a black gradient for the white text between the text and the image which goes from black to tranparent on the y axis:
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 350f
                    )
                )
            )

            //Put crew name into another box to be able to align it inside the parent box.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                //TODO text style in theme rein tun.
                Text(crewName, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }

}

@Preview
@Composable
fun CrewSearchTextFieldPreview(){
    CrewSearchTextField(searchName = "Steven Spielberg", onKeyboardActionDone = {}, onValueChange = {})
}