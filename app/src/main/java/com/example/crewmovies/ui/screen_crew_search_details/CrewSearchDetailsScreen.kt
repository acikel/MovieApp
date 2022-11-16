package com.example.crewmovies.ui.screen_crew_search_details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.crewmovies.R
import com.example.crewmovies.core.domain.models.PeopleResultModel

@Composable
fun CrewSearchDetailsScreen(
    person : PeopleResultModel,
    modifier: Modifier = Modifier,
    crewSearchDetailsViewModel: CrewSearchDetailsViewModel = hiltViewModel()
){
    val scrollState = rememberScrollState()

    ImageParallaxScroll(scrollState, person, urlToPicture = person.profilePicturePath?.let {
        crewSearchDetailsViewModel.getPainterFromUrl(
            it
        )
    })

    /*
    ImageParallaxScroll(scrollState, crewSearchDetailsViewModel.person.value, urlToPicture = crewSearchDetailsViewModel.person.value.profilePicturePath?.let {
        crewSearchDetailsViewModel.getPainterFromUrl(
            it
        )
    })
     */
}

@Composable
fun ImageParallaxScroll(
    scrollState: ScrollState,
    person: PeopleResultModel,
    urlToPicture : String?,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        //rememberAsyncImagePainter("https://www.example.com/image.jpg")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(Color.White)

                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                    translationY = 0.5f * scrollState.value
                },

            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = urlToPicture,
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
        }
        Text(
            text = person.knownForDepartment?:"text",
            modifier = Modifier.background(
                Color.White
            ),
            style = TextStyle(
                fontSize = 24.sp
            )
        )
    }
}