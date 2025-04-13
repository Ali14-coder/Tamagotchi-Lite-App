package vcmsa.projects.tamagotchi

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class FragmentStatusBars : Fragment() {
    private lateinit var viewModel: FragmentStatusBarsViewModel
    private lateinit var barChart: HorizontalBarChart

    companion object {
        fun newInstance() = FragmentStatusBars()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fragment_status_bars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barChart = view.findViewById(R.id.statusBars)
        viewModel = ViewModelProvider(requireActivity())[FragmentStatusBarsViewModel::class.java]

       // viewModel.entries.observe(viewLifecycleOwner) { entries ->
       //     val dataSet = BarDataSet(entries, "Status")
        //    val barData = BarData(dataSet)

       //     dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
       //     dataSet.valueTextSize = 16f

       //     barChart.data = barData
            barChart.invalidate()
      //  }
    }
}