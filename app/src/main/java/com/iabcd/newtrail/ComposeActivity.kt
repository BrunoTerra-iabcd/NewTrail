package com.iabcd.newtrail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iabcd.newtrail.model.Holder
import com.iabcd.newtrail.ui.theme.NewTrailTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewTrailTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 80.dp),
                        reverseLayout = true
                    ) {
                        itemsIndexed(Holder.generateValues()) { index: Int, item ->
                            Holder
                            HolderActivity(holder = item, position = index)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HolderActivity(holder: Holder, position: Int) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .background(Color(0xFFEBEFF8))
            .padding(32.dp),
        contentAlignment = if (position % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_adb_24),
                contentDescription = null,
                modifier = Modifier
                    .background(Color(0xFF94B5F6), RoundedCornerShape(4.dp))
                    .padding(32.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))

            Text(text = holder.name, style = MaterialTheme.typography.body2)
        }
    }
}

@Preview
@Composable
fun Prev() {
    Column() {
        HolderActivity(holder = Holder("Hello"), position = 0)
        HolderActivity(holder = Holder("Hello"), position = 1)
    }
}

