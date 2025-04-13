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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menubar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val barChart: HorizontalBarChart = findViewById(R.id.statusBars)

        statusArrayList = arrayListOf<BarEntry>()
        statusArrayList.add( BarEntry(2f,10f))
        statusArrayList.add( BarEntry(3f,20f))
        statusArrayList.add( BarEntry(4f,5f))
        statusArrayList.add( BarEntry(5f,15f))

        val barDataSet = BarDataSet(statusArrayList, "Current Health")
        val barData = BarData(barDataSet)

        barChart.data = barData

        // Set color
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        // Text color
        barDataSet.valueTextColor = Color.BLACK

        // Text size
        barDataSet.valueTextSize = 16f

        // Enable description
        barChart.description.isEnabled = true



        replaceFrag(FragInit)
        val bottomBar = findViewById<BottomNavigationView>(R.id.NavBar)
        bottomBar.setOnItemSelectedListener{
            when (it.itemId){
                R.id.ic_sleep->replaceFrag(FragSleep)
                R.id.ic_paint->replaceFrag(FragPaint)
                R.id.ic_eat->replaceFrag(FragEat)
                R.id.ic_read->replaceFrag(FragRead)
//                if ()
//                {
//                    R.id.ic_init->replaceFrag(FragInit)
//                }
            }

            true
        }
    }

    private fun replaceFrag(fragment: Fragment)
    {
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout,fragment)
            transaction.commit()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val viewModel = ViewModelProvider(this)[FragmentStatusBarsViewModel::class.java]

        when (item.itemId) {
            R.id.ic_sleep -> viewModel.updateBarsForAction("sleep")
            R.id.ic_eat   -> viewModel.updateBarsForAction("eat")
            R.id.ic_read  -> viewModel.updateBarsForAction("read")
            R.id.ic_paint -> viewModel.updateBarsForAction("paint")
        }

        return super.onOptionsItemSelected(item)
    }


}