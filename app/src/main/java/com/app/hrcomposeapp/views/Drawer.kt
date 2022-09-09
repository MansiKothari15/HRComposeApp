package com.app.hrcomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.utils.AppScreens


private val screens = listOf(
    AppScreens.HomeScreen,
    AppScreens.Account,
    AppScreens.Help
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_person_pin_24),
                colorFilter = ColorFilter.tint(
                    colorResource(id = R.color.secondaryColor),
                ),
                contentDescription = "App icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(50)),
            )
        }
        screens.forEach { screen ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onDestinationClicked(screen.route) })
                    .height(45.dp)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    screen.icon,
                    contentDescription = screen.title,
                    tint = colorResource(id = R.color.secondaryColor)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                )
            }
            Divider(color = Color.Gray)
        }
    }
}