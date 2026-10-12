package com.example.chucksolitario.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chucksolitario.Greeting
import com.example.chucksolitario.ui.theme.ChucksolitarioTheme
import com.example.chucksolitario.viewmodel.JokesViewModel

@Composable
fun ChuckMain(modifier: Modifier = Modifier, jokesViewmodel: JokesViewModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            onClick = { /*TODO*/ },
            colors = CardDefaults.cardColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "ola",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )

            Text(text="aaa",
                fontSize = 12.sp,
                modifier = Modifier.padding(12.dp)
            )

            Box(modifier = Modifier.padding(10.dp).size(50.dp).background(Color.LightGray),
                contentAlignment = Alignment.Center ){
                Text(text= "TODO" ,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(16.dp),){
                Text(text = "button")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChucksolitarioTheme {
        ChuckMain(Modifier, JokesViewModel())
    }
}