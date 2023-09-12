package com.example.skarvoassigment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skarvoassigment.ui.theme.SkarvoAssigmentTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ViewModel>()
    private var modelData = listOf<Model>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkarvoAssigmentTheme {
                // A surface container using the 'background' color from the theme
                viewModel.getData()
                viewModel.getMainDataData()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val scaffoldState = rememberBottomSheetScaffoldState()
                    BottomSheetScaffold(scaffoldState = scaffoldState, sheetContent = {
                            modelData = viewModel.bottomSheetData.collectAsState().value

                        Column(modifier = Modifier.heightIn( max = (LocalConfiguration.current.screenHeightDp * 0.9f).dp)) {


                            BottomSheetData(modelData)
                        }
                    }, sheetPeekHeight = (LocalConfiguration.current.screenHeightDp * 0.2f).dp) {

                        HomeScreenApp(viewModel)
                    }
                }
            }
        }
    }
}
@Composable
fun HomeScreenApp(viewModel: ViewModel) {
    var mainViewData: List<Model>

    Column {
       mainViewData= viewModel.mainViedData.collectAsState().value

        TopAppBar(title = { Text(text = "Test") },  actions = {
            IconButton(onClick = {
                viewModel.addItem(Model("DynamicValue","DynamicDesc"))
            }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        })
        MainView(platList = mainViewData,viewModel)
    }
}


@Composable
fun BottomSheetData(model: List<Model>) {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        model.forEach { model ->
            StudentRow(model, null)
        }
    }
}
@Composable
fun MainView(platList: List<Model>, viewModel: ViewModel) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(bottom = (LocalConfiguration.current.screenHeightDp * 0.2f).dp)) {
        platList.forEach { model ->
            StudentRow(model,viewModel)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentRow(student: Model, viewModel: ViewModel?) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(), onClick = {
                viewModel?.let {
                    it.delItem(student)
                }
        }
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                student.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                student.description.toString(),
                color = Color.Gray,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkarvoAssigmentTheme {
        //Greeting("Android")
    }
}