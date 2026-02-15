package com.example.artspace.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.artspace.R
import com.example.artspace.data.ArtworkData
import com.example.artspace.model.Artwork

@Composable
fun ArtSpaceScreen() {

    var currentIndex by rememberSaveable { mutableStateOf(0) }
    val artworks = ArtworkData.artworks
    val artwork = artworks[currentIndex]

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeLayout(artwork, currentIndex, artworks.size) {
            currentIndex = it
        }
    } else {
        PortraitLayout(artwork, currentIndex, artworks.size) {
            currentIndex = it
        }
    }
}

@Composable
fun PortraitLayout(
    artwork: Artwork,
    currentIndex: Int,
    total: Int,
    onIndexChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shadowElevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = stringResource(id = artwork.titleRes),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(id = artwork.titleRes),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "${stringResource(id = artwork.authorRes)} (${stringResource(id = artwork.yearRes)})",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onIndexChange(currentIndex - 1) },
                enabled = currentIndex > 0,
                modifier = Modifier.widthIn(min = 120.dp)
            ) {
                Text(stringResource(R.string.previous))
            }

            Button(
                onClick = { onIndexChange(currentIndex + 1) },
                enabled = currentIndex < total - 1,
                modifier = Modifier.widthIn(min = 120.dp)
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}



@Composable
fun LandscapeLayout(
    artwork: Artwork,
    currentIndex: Int,
    total: Int,
    onIndexChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight(),
            shadowElevation = 4.dp
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = stringResource(id = artwork.titleRes),
                modifier = Modifier.padding(8.dp),
                contentScale = androidx.compose.ui.layout.ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = artwork.titleRes),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Text(
                text = "${stringResource(id = artwork.authorRes)} (${stringResource(id = artwork.yearRes)})"
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onIndexChange(currentIndex - 1) },
                    enabled = currentIndex > 0
                ) {
                    Text(stringResource(R.string.previous))
                }
                Button(
                    onClick = { onIndexChange(currentIndex + 1) },
                    enabled = currentIndex < total - 1
                ) {
                    Text(stringResource(R.string.next))
                }
            }
        }
    }
}
