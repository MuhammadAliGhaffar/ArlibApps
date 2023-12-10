package com.example.arlibapps.feature.medicine

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.arlibapps.R
import com.example.arlibapps.data.model.Medicine
import com.google.gson.Gson

@Composable

fun MedicineScreen(
    navController: NavController,
    serializationMedicine: String?
) {
    val deserializeUserConfiguration = Gson().fromJson(serializationMedicine, Medicine::class.java)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {

        Header(navController = navController)
        Name(name = deserializeUserConfiguration.name)
        MedicineContent(label = "Milligram", value = deserializeUserConfiguration.dosageMg.toString())
        MedicineContent(label = "Benefits", value = deserializeUserConfiguration.benefits)
        MedicineContent(label = "Disease Cure", value = deserializeUserConfiguration.diseaseCure)

    }
}

@Composable
fun Header(navController: NavController) {
    Row {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (backButton, textTitle) = createRefs()
                Image(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "backButton",
                    modifier = Modifier
                        .constrainAs(backButton) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 10.dp)
                            bottom.linkTo(parent.bottom)
                            centerVerticallyTo(parent)
                        }
                        .clickable {
                            navController.navigateUp()
                        })
                Text(
                    text = "Medicine Details",
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(textTitle) {
                        centerTo(parent)
                    })
            }

        }
    }

}

@Composable
fun Name(name:String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MedicineContent(label:String, value:String) {
    Divider()
    Column(modifier = Modifier.padding(vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = label,style = MaterialTheme.typography.labelMedium)
        Text(text = value,style = MaterialTheme.typography.titleMedium)
    }
}