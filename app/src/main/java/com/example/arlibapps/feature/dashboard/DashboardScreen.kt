package com.example.arlibapps.feature.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.arlibapps.data.model.CalendarUiModel
import com.example.arlibapps.data.model.Medicine
import com.example.arlibapps.ui.composables.MedicineListItem
import com.example.arlibapps.utilities.CalendarUtils
import com.example.arlibapps.utilities.toast
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    navController: NavController,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState = dashboardViewModel.uiState.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (firstContainer, secondContainer) = createRefs()
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(firstContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        ) {
            TopContainer()
        }
        ListContainer(modifier = Modifier.constrainAs(secondContainer) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(firstContainer.bottom)
        }, medicines = uiState.value.medicines, navController = navController)
    }
}

@Composable
fun ListContainer(modifier: Modifier, medicines: List<Medicine>, navController: NavController) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 8.dp),
        content = {
            items(medicines) { medicine ->
                MedicineListItem(
                    medicine = medicine
                )
                {
                    toast(navController.context, it.name)
                }
            }
        })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopContainer() {
    val calendarUtils = CalendarUtils()
    var data by remember { mutableStateOf(calendarUtils.getData(lastSelectedDate = calendarUtils.today)) }

    Header(
        data = data,
        onPrevClickListener = { startDate ->
            val finalStartDate = startDate.minusDays(1)
            data = calendarUtils.getData(
                startDate = finalStartDate,
                lastSelectedDate = data.selectedDate.date
            )
        },
        onNextClickListener = { endDate ->
            val finalStartDate = endDate.plusDays(2)
            data = calendarUtils.getData(
                startDate = finalStartDate,
                lastSelectedDate = data.selectedDate.date
            )
        }
    )
    Content(data = data) { date ->
        data = data.copy(
            selectedDate = date,
            visibleDates = data.visibleDates.map {
                it.copy(
                    isSelected = it.date.isEqual(date.date)
                )
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            text = if (data.selectedDate.isToday) {
                "Today ${
                    data.selectedDate.date.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                    )
                }"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                )
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = "Back"
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Next"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    data: CalendarUiModel,
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 46.dp)) {
        items(data.visibleDates.size) { index ->
            ContentItem(
                date = data.visibleDates[index],
                onDateClickListener
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable {
                onClickListener(date)
            },
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}