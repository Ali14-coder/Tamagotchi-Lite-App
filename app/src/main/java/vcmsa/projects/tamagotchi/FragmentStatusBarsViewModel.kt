package vcmsa.projects.tamagotchi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.BarEntry
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet

private val _entries = MutableLiveData<List<BarEntry>>()
val entries: LiveData<List<BarEntry>> get() = _entries

class FragmentStatusBarsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _barEntries = MutableLiveData<List<BarEntry>>()
    val barEntries: LiveData<List<BarEntry>> get() = _barEntries

    fun updateBarsForAction(action: String) {
        val updatedEntries = when (action) {
            "sleep" -> listOf(BarEntry(2f, 30f), BarEntry(3f, 10f))
            "eat"   -> listOf(BarEntry(2f, 20f), BarEntry(3f, 40f))
            "read"  -> listOf(BarEntry(2f, 15f), BarEntry(3f, 35f))
            "paint" -> listOf(BarEntry(2f, 5f),  BarEntry(3f, 25f))
            else    -> emptyList()
        }
        _entries.value = updatedEntries
    }

}