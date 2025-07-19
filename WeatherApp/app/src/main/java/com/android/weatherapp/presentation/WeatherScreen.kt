package com.android.weatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue // For the 'by' delegate
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.weatherapp.domain.api.NetworkResponse

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {

    var city by remember {
        mutableStateOf("")
    }

    val weatherResult = weatherViewModel.weatherResult.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    )  {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        )  {
            //Text Field Starts
           OutlinedTextField(
               //Added not to hide search field
               modifier = Modifier.weight(1f),
               value = city,
               onValueChange = { it ->
               city = it
           },
               label = {
                   Text(text = "Search for any location")
               }
           )
           //Text Field ends
           IconButton(onClick = {
                weatherViewModel.getData(city)
           }) {
               Icon(imageVector = Icons.Default.Search, contentDescription = "Search for Any location")
           }

        }
        when(val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.meesage)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                Text(text = result.data.toString())
            }
            null -> {

            }
        }
    }
}