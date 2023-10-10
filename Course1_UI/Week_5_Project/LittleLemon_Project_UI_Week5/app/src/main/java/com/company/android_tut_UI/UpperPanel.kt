package com.company.android_tut_UI


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.company.android_tut_UI.ui.theme.green
import com.company.android_tut_UI.ui.theme.yellow

@Composable
fun UpperPanel() {
    Column(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
            .background(Color(0XFF495E57)),

    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp),
            color = yellow,

        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 10.dp),
            color = Color.White
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
//                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f),
                color = Color.LightGray
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
//                    .padding(end = 10.dp)
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .clip(RoundedCornerShape(3.dp))
                .padding(start = 10.dp, bottom = 10.dp),
            colors = ButtonDefaults.buttonColors(yellow)
        ) {
            Text(
                text = stringResource(id = R.string.order_button_text)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpperPanelPreview() {
    UpperPanel()
}
