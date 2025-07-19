package com.android.weatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.weatherapp.domain.api.WeatherModel

@Composable
fun WeatherDetails(data : WeatherModel) {
    Column (
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Row 1 Starts
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location icon",
                modifier =  Modifier.size(40.dp)
            )
            Text(text = data.location.name, fontSize = 30.sp )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country , fontSize = 18.sp, color = Color.Gray)
        }

        //Row 1 ends


         Spacer(modifier = Modifier.height(16.dp))
         Text(
             text = "${data.current.temp_c} c",
             fontSize = 56.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center
         )

        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition.icon}".replace("64*64","128*128"),
            contentDescription = "Condition Icon"
        )

        Text(
            text = data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        // Row 2 ends
        Spacer (modifier = Modifier.height(16.dp))
        Card {
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal(key = "Humidity", value = data.current.humidity)
                    WeatherKeyVal(key = "Wind Speed" , value = data.current.wind_kph)
                }

                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal(key = "UV", data.current.uv)
                    WeatherKeyVal(key = "Participation" , data.current.precip_mm)
                }
                //
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyVal(key = "Local time", value = data.location.localtime.split( " ")[1])
                    WeatherKeyVal("Local Date" , value = data.location.localtime.split(" ")[0])
                }

            }
        }

    }
}

@Composable
fun WeatherKeyVal(key : String, value : String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = value , fontSize = 24.sp , fontWeight = FontWeight.Bold)
        Text(text = key , fontWeight = FontWeight.SemiBold , color = Color.Gray)
    }
}