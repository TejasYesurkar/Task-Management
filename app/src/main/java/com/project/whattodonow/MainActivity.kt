package com.project.whattodonow

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.project.whattodonow.databinding.ActivityMainBinding
import com.project.whattodonow.ui.add_task.AddTasksFragment
import com.project.whattodonow.ui.gallery.DailyReminderFragment
import com.project.whattodonow.ui.gallery.DateReminderFragment
import com.project.whattodonow.ui.tasklist.TaskListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {

                R.id.nav_home -> {
                    val fragment: TaskListFragment = TaskListFragment()
                    if (fragment != null && fragment.isAdded()) {
                        fragment.receiveMessage("")
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment , "TaskListFragment")
                        .commit()

                }
                R.id.nav_date_reminder -> {
                    val fragment: DateReminderFragment = DateReminderFragment()
                    if (fragment != null && fragment.isAdded()) {
                        fragment.receiveMessage("")
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment , "DateReminderFragment")
                        .commit()
                }

                R.id.nav_daily_expense -> {
                    val fragment: DailyReminderFragment = DailyReminderFragment()
                    if (fragment != null && fragment.isAdded()) {
                        fragment.receiveMessage("")
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment , "DailyReminderFragment")
                        .commit()
                }
                R.id.nav_task -> {
                    val fragment: AddTasksFragment = AddTasksFragment()
                    if (fragment != null && fragment.isAdded()) {
                        fragment.receiveMessage("")
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment , "AddTasksFragment")
                        .commit()

                    //this is fragment method, we call it from activity
                    fragment.receiveMessage("")
                }
            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_add_task
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}