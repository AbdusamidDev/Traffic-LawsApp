package developer.abdusamid.trafficlawsapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import developer.abdusamid.trafficlawsapp.databinding.ActivityMainBinding
import developer.abdusamid.trafficlawsapp.Utils.Data

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onBackPressed() {
        if (Data.isHome) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> {
                    findNavController(R.id.my_host).navigate(
                        R.id.mainFragment,
                        bundleOf("pos" to 1)
                    )
                }
                R.id.heart -> {
                    findNavController(R.id.my_host).navigate(
                        R.id.favoriteFragment,
                        bundleOf("pos" to 2)
                    )
                }
                R.id.info -> {
                    findNavController(R.id.my_host).navigate(R.id.fragmentInfo)
                }
            }
            true
        }

    }

    fun isVisible(i: Boolean) {
        if (i) {
            binding.bottomNav.visibility = View.VISIBLE
        } else {
            binding.bottomNav.visibility = View.GONE
        }
    }
}