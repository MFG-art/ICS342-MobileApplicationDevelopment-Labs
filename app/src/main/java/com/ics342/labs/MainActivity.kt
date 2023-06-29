package com.ics342.labs


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ics342.labs.data.DataItem
import com.ics342.labs.ui.theme.LabsTheme

// Used for remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


private val dataItems = listOf(
    DataItem(1, "Item 1", "Description 1"),
    DataItem(2, "Item 2", "Description 2"),
    DataItem(3, "Item 3", "Description 3"),
    DataItem(4, "Item 4", "Description 4"),
    DataItem(5, "Item 5", "Description 5"),
    DataItem(6, "Item 6", "Description 6"),
    DataItem(7, "Item 7", "Description 7"),
    DataItem(8, "Item 8", "Description 8"),
    DataItem(9, "Item 9", "Description 9"),
    DataItem(10, "Item 10", "Description 10"),
    DataItem(11, "Item 11", "Description 11"),
    DataItem(12, "Item 12", "Description 12"),
    DataItem(13, "Item 13", "Description 13"),
    DataItem(14, "Item 14", "Description 14"),
    DataItem(15, "Item 15", "Description 15"),
    DataItem(16, "Item 16", "Description 16"),
    DataItem(17, "Item 17", "Description 17"),
    DataItem(18, "Item 18", "Description 18"),
    DataItem(19, "Item 19", "Description 19"),
    DataItem(20, "Item 20", "Description 20"),
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()


            NavHost(navController = navController, startDestination = "data-list-screen") {
                composable("data-list-screen") { DataListScreen(navController, dataItems)}
                composable("data-item-view"){
                    DataItemSelectedView(navController)
                }

            }

        }
    }
}

@Composable
fun DataListScreen(navController: NavController, items: List<DataItem>){
//    var showDialog by remember { mutableStateOf(false) }  - not using this, using rememberDataItem instead



    LabsTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column() {
                DataItemList(navController = navController, dataItems = items)
            }

        }

    }
}


@Composable
fun DataItemSelectedView(navController: NavController){
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "id"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "name"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "description"

        )

    }
}

@Composable
fun DataItemView(dataItem: DataItem) {
    /* Create the view for the data item here. */
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = dataItem.id.toString()
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = dataItem.name
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = dataItem.description
        )

    }

}

@Composable
fun DataItemList(navController: NavController, dataItems: List<DataItem>) {
/* Create the list here. This function will call DataItemView() */

    LazyColumn {
        items(dataItems) {
                DataItem ->
            Box( modifier = Modifier.clickable{navController.navigate("data-item-view")}){
                DataItemView(DataItem)
            }
        }
    }
}
