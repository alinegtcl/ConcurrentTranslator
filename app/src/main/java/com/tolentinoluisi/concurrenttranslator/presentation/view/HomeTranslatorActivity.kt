package com.tolentinoluisi.concurrenttranslator.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.tolentinoluisi.concurrenttranslator.R
import com.tolentinoluisi.concurrenttranslator.databinding.ActivityHomeTranslatorBinding

class HomeTranslatorActivity : AppCompatActivity() {
    private val binding: ActivityHomeTranslatorBinding by lazy {
        ActivityHomeTranslatorBinding.inflate(layoutInflater)
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarTranslator)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerTranslator) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerTranslator)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}
