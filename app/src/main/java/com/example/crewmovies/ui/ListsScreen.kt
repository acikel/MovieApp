package com.example.crewmovies.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crewmovies.R
import com.example.crewmovies.core.domain.models.PeopleListModel
import com.example.crewmovies.ui.theme.boldTitle

@Composable
fun ListsScreen(
    modifier: Modifier = Modifier,
    listScreenViewModel: ListScreenViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = modifier
        ) {

            Text(
                text = stringResource(id = R.string.lists),
                style = MaterialTheme.typography.boldTitle,
                modifier = Modifier
                    .padding(start = 10.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
            )
            TextButton(
                onClick = { listScreenViewModel.openAddListDialogClearName() },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    //tint = MaterialTheme.colors.onSurface,
                    contentDescription = null
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    // specifying text as android
                    stringResource(id = R.string.new_list),

                    // on below line adding style
                    style = MaterialTheme.typography.button.copy(fontSize = 16.sp),
                )
            }

            if (listScreenViewModel.isDialogOpen) {
                DialogBoxAddList(
                    listScreenViewModel.listName,
                    { listScreenViewModel.updateListName(it) },
                    { listScreenViewModel.closeAddListDialog() },
                    { listScreenViewModel.createNewListAndCloseDialog() },
                    listScreenViewModel.getApplicationContext()
                )
            }
            val peopleLists: ArrayList<PeopleListModel> by listScreenViewModel.uiPeopleListsState.collectAsState()
            CrewSearchList(peopleLists)
            //Other button types
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
                Row( modifier = Modifier){
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        // specifying text as android
                        stringResource(id = R.string.new_list),

                        // on below line adding style
                        //style = TextStyle(fontWeight = FontWeight.Bold),

                        // adding text align on below line.
                        //textAlign = TextAlign.Center,

                        // adding font size on below line.
                        //fontSize = 20.sp
                    )
                }

            }
            
            Button(
                onClick = { /*TODO*/ },
            ) {
                Row(
                    // on below line we are specifying modifier
                    // and setting max height and max width
                    // for our column
                    modifier = Modifier
                        // on below line we are
                        // adding padding for our column
                        .padding(5.dp),
                    // on the below line we are specifying horizontal
                    // and vertical alignment for our column
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    /*
                    Image(
                        // on below line we are specifying the drawable
                        // image for our image.
                        painter = painterResource(id = R.drawable.ic_android),

                        // on below line we are specifying
                        // content description for our image
                        contentDescription = "Image",

                        // on below line we are setting height
                        // and width for our image.
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)

                    )
                     */
                    //Use above Image or below Icon:
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null
                    )

                    // adding spacer on below line.
                    Spacer(Modifier.height(10.dp))

                    // adding text on below line.
                    Text(
                        // specifying text as android
                        stringResource(id = R.string.new_list),

                        // on below line adding style
                        //style = TextStyle(fontWeight = FontWeight.Bold),

                        // adding text align on below line.
                        //textAlign = TextAlign.Center,

                        // adding font size on below line.
                        //fontSize = 20.sp
                    )

                }
            }
        */
        }
    }
}

/*
@Composable
fun ButtonClickTamplate(
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        onClick = {
            onButtonClick()
        }) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CrewSearchList(
    resultPeopleLists: ArrayList<PeopleListModel>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(start = 12.dp, end = 12.dp)
    ) {

        /*
        items(resultPeopleLists.size) { index ->
            resultPeopleLists[index].name?.let {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_picture),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                )}
        }

        items(resultPeopleLists.size) { index ->
            resultPeopleLists[index].name?.let { Text(it) }
        }
         */
        items(resultPeopleLists.size){ index ->
            CrewSearchListItem(resultPeopleLists[index])
        }

    }
}

@Composable
fun CrewSearchListItem(peopleListModel: PeopleListModel){
    Row (verticalAlignment = Alignment.CenterVertically) {
        if(peopleListModel.name == null)
            return

        //TODO image of first person in list
        Image(
            painter = painterResource(id = R.drawable.placeholder_picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                //.clip(CircleShape)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))
        peopleListModel.name?.let { Text(it) }
    }
}

@Preview
@Composable
fun ListsScreenPreview() {
    ListsScreen()
}