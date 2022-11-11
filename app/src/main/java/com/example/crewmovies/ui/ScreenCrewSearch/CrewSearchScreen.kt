package com.example.crewmovies.ui.ScreenCrewSearch

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
        //Surface(
        //    color = MaterialTheme.colors.background
        //) {
        Scaffold(
            topBar = {
                //SearchAppBar(navigateUp, viewModel, closeKeyboard)
                CrewSearchTopAppBar(searchName = crewSearchViewModel.peopleSearchName, onKeyboardActionDone = { crewSearchViewModel.fillListWithSearchResult() }, onValueChange = { crewSearchViewModel.updateTextSearch(it) } )
            }
                //backgroundColor = Color(R.color.purple_light),
        ) { //paddingValues ->
            //Box(
            //    modifier = Modifier.padding(paddingValues)
            //) {
            /*
                   // Show progress while search is happening
                   val isLoadingData = !uiState.isSearchingResults && uiState.actorList.isEmpty()
                   ShowSearchProgress(isLoadingData)
                    // Main content for this screen
                    SearchScreenContent(uiState.actorList, selectedActor, closeKeyboard)
                     */
                    //val lifecycleOwner = LocalLifecycleOwner.current
                    //val examplePeopleSearchFlowLifecycleAware = remember(crewSearchViewModel.uiPeopleSearchListState, lifecycleOwner) {
                    //    crewSearchViewModel.uiPeopleSearchListState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                    //}
                    //val examplePeopleSearch: ArrayList<PeopleResultModel> by examplePeopleSearchFlowLifecycleAware.collectAsState(initial = arrayListOf())



            val examplePeopleSearch: ArrayList<PeopleResultModel> by crewSearchViewModel.uiPeopleSearchListState.collectAsState(initial = arrayListOf())
            CrewSearchList(urlToPicture = { crewSearchViewModel.getPainterFromUrl(it) }, searchResultList = examplePeopleSearch)


        }
           // }
        //}

    }
}
@Composable
fun CrewSearchTopAppBar(
    //navigateToSearch: () -> Unit,
    searchName: String,
    onKeyboardActionDone: () -> Unit,
    onValueChange: ( String ) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(

        content = { CrewSearchTopAppBarContent(searchName = searchName, onKeyboardActionDone = onKeyboardActionDone, onValueChange = onValueChange) },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        modifier = modifier
            //.statusBarsPadding()
            .padding(top = 4.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
            //.padding(4.dp)
            .height(62.dp)
    )
}

@Composable
fun CrewSearchTopAppBarContent(
    searchName: String,
    onKeyboardActionDone: () -> Unit,
    onValueChange: ( String ) -> Unit,
    modifier: Modifier = Modifier
) {

    /*
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
           .height(48.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            //.clickable(onClick = navigateToSearch)
            .background(color = MaterialTheme.colors.surface)
    ) {
    */

        /*
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.alpha(0.5f)
        )

        Spacer(modifier = Modifier.padding(horizontal = 12.dp))
*/
        /*
        Text(
            text = stringResource(R.string.search_people_hint),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.alpha(0.5f)
        )
         */

        OutlinedTextField(
            value = searchName,
            singleLine = true,
            onValueChange = onValueChange,
            label = { Text(stringResource(R.string.search_people_hint)) },
            //isError = isGuessWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardActionDone() }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null
                )

                /*
                IconButton(
                    modifier = Modifier.padding(start = 4.dp),
                    onClick = {
                        /*
                        closeKeyboardAndNavigateUp(
                            navigateUp = navigateUp,
                            closeKeyboard = closeKeyboard,
                            keyboardState = keyboardState
                        )
                        */
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )
                }

                 */
            },
            /*
            leadingIcon = {
                IconButton(
                    modifier = Modifier.padding(start = 4.dp),
                    onClick = {
                        /*
                        closeKeyboardAndNavigateUp(
                            navigateUp = navigateUp,
                            closeKeyboard = closeKeyboard,
                            keyboardState = keyboardState
                        )
                        */
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )
                }
            },
            */
            //Define Textfield color is theme if should not be defined everytime:
            /*
            ProvideTextStyle(TextStyle(color = Color.White)) {
                TextField(
                    ...
                )
            }
            https://stackoverflow.com/questions/66119935/how-to-override-the-text-color-in-textfield-in-jetpack-compose-using-materialthe
             */
            colors =  TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                textColor =  Color.Black,
                //focusedLabelColor = Color.Black
            ),
            modifier = modifier
                .fillMaxWidth()
                //.background(color = MaterialTheme.colors.surface),
            )

    //}
}

@Composable
fun AppDivider(
    verticalPadding: Dp
) {
    Divider(
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
        thickness = 1.dp,
        startIndent = 0.dp,
        modifier = Modifier.padding(vertical = verticalPadding)
    )
}

/*
@Composable
fun SearchAppBar(
    navigateUp: () -> Unit,
    viewModel: SearchViewModel,
    closeKeyboard: () -> Unit?
) {
    // Immediately update and keep track of query from text field changes.
    var query: String by rememberSaveable { mutableStateOf("") }

    // Handle clear icon state whether to show or hide based on query.
    var showClearQueryIcon: Boolean by rememberSaveable { mutableStateOf(false) }
    // Initially the icon state will be false and hidden since query will be empty.
    if (query.isEmpty()) {
        // If query is not empty show the icon
        showClearQueryIcon = false
    } else if (query.isNotEmpty()) {
        // If query is empty hide the icon
        showClearQueryIcon = true
    }

    // Detects whether a current keyboard is opened or closed
    val keyboardState: KeyboardState by getCurrentKeyboardState()

    Column {

        // This Spacer avoids colliding content with app bar by matching the height of status bar.
        Spacer(Modifier.statusBarsPadding())

        TextField(
            value = query,
            onValueChange = { onQueryChanged ->
                // If user makes changes to text, immediately updated it.
                query = onQueryChanged
                // To avoid crash, only query when string isn't empty.
                if (onQueryChanged.isNotEmpty()) {
                    // Pass latest query to refresh search results.
                    viewModel.performQuery(onQueryChanged)
                }
            },
            leadingIcon = {
                IconButton(
                    modifier = Modifier.padding(start = 4.dp),
                    onClick = {
                        closeKeyboardAndNavigateUp(
                            navigateUp = navigateUp,
                            closeKeyboard = closeKeyboard,
                            keyboardState = keyboardState
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = stringResource(id = R.string.cd_search_icon)
                    )
                }
            },
            trailingIcon = {
                if (showClearQueryIcon) {
                    IconButton(
                        onClick = {
                            query = ""
                            closeKeyboard()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = stringResource(id = R.string.cd_clear_icon)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            // This starts the activity and populates the intent with the speech text.
                            resultLauncher.launch(createLaunchSpeechRecognitionIntent)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = "",
                        )
                    }
                }
            },
            placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background, RectangleShape),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )

        // Divides content and search bar with line.
        AppDivider(verticalPadding = 0.dp)
    }
}
 */

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
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(start = 12.dp, end = 12.dp)
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
       // shape = RoundedCornerShape(15.dp),
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
    CrewSearchTopAppBar(searchName = "Steven Spielberg", onKeyboardActionDone = {}, onValueChange = {})
}