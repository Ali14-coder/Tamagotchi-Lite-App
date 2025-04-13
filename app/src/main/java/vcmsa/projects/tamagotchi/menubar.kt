package vcmsa.projects.tamagotchi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private val FragInit = FragmentDisplay()
private val FragSleep = FragmentSleep()
private val FragEat = FragmentEat()
private val FragRead = FragmentRead()
private val FragPaint = FragmentPaint()


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
        replaceFrag(FragInit)
        val bottomBar = findViewById<BottomNavigationView>(R.id.NavBar)
        bottomBar.setOnItemSelectedListener{
            when (it.itemId){
               // R.id.ic_init->replaceFrag(FragInit)
                R.id.ic_sleep->replaceFrag(FragSleep)
                R.id.ic_paint->replaceFrag(FragPaint)
                R.id.ic_eat->replaceFrag(FragEat)
                R.id.ic_read->replaceFrag(FragRead)
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


}