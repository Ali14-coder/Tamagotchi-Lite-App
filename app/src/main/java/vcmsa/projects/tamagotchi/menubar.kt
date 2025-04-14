package vcmsa.projects.tamagotchi

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView

private val FragInit = FragmentDisplay()
private val FragSleep = FragmentSleep()
private val FragEat = FragmentEat()
private val FragRead = FragmentRead()
private val FragPaint = FragmentPaint()

var statusArrayList = arrayListOf<BarEntry>()

class menubar : AppCompatActivity() {
    private lateinit var barChart: HorizontalBarChart
    private lateinit var statusArrayList: ArrayList<BarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menubar)
        barChart = findViewById(R.id.statusBars)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //val barChart: HorizontalBarChart = findViewById(R.id.statusBars)

        statusArrayList = arrayListOf<BarEntry>()
        statusArrayList.add( BarEntry(2f,10f))
        statusArrayList.add( BarEntry(3f,20f))
        statusArrayList.add( BarEntry(4f,5f))
        statusArrayList.add( BarEntry(5f,15f))

        statusArrayList = arrayListOf(
            BarEntry(1f, 10f), // sleep
            BarEntry(2f, 15f), // eat
            BarEntry(3f, 20f), // read
            BarEntry(4f, 25f)  // paint
        )
        updateBarChart()

        val bottomNav = findViewById<BottomNavigationView>(R.id.NavBar)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_sleep -> modifyBarValue(0, 2f) // increase sleep
                R.id.ic_eat -> modifyBarValue(1, -2f)  // decrease eat
                R.id.ic_read -> modifyBarValue(2, 2f)  // increase read
                R.id.ic_paint -> modifyBarValue(3, -2f) // decrease paint
            }
            true
        }

        replaceFrag(FragInit)
      //  val bottomBar = findViewById<BottomNavigationView>(R.id.NavBar)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_sleep -> {
                    modifyBarValue(0, 2f)
                    replaceFrag(FragSleep)
                }
                R.id.ic_eat -> {
                    modifyBarValue(1, -2f)
                    replaceFrag(FragEat)
                }
                R.id.ic_read -> {
                    modifyBarValue(2, 2f)
                    replaceFrag(FragRead)
                }
                R.id.ic_paint -> {
                    modifyBarValue(3, -2f)
                    replaceFrag(FragPaint)
                }
            }
            true
        }

    }

    private fun replaceFrag(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }

    private fun updateBarChart() {
        val barDataSet = BarDataSet(statusArrayList, "Status")
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 16f

        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setFitBars(true)
        barChart.invalidate() // Refresh the chart
    }


    private fun modifyBarValue(index: Int, change: Float) {
        if (index in statusArrayList.indices) {
            val original = statusArrayList[index]
            val newValue = original.y + change
            statusArrayList[index] =
                BarEntry(original.x, newValue.coerceAtLeast(0f)) // avoid negative
            updateBarChart()
        }
    }

}

