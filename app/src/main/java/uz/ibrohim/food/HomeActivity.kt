package uz.ibrohim.food

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import uz.ibrohim.food.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDrawerLayout()

        val close: ImageView = binding.navView.getHeaderView(0).findViewById(R.id.header_exit)
        binding.apply {
            close.setOnClickListener {
                drawerLayout.closeDrawer(GravityCompat.START)
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment -> {
                        binding.homeRelative.isVisible = true
                        binding.homeText.text = "Home"
                    }

                    R.id.favoriteFragment -> {
                        binding.homeRelative.isVisible = true
                        binding.homeText.text = "Favorite"
                    }

                    R.id.foodInfoFragment -> {
                        binding.homeRelative.isVisible = false
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    private fun setupDrawerLayout() {
        binding.navView.setupWithNavController(navController)
        binding.homeIcon.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
//        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}