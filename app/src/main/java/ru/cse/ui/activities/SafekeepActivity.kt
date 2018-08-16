package ru.cse.ui.activities

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.app_navigation_view.*
import kotlinx.android.synthetic.main.safekeep_activity.*
import ru.atol.drivers10.fptr.Fptr
import ru.atol.drivers10.fptr.IFptr
import ru.atol.drivers10.fptr.settings.SettingsActivity
import ru.cse.R
import ru.cse.databinding.SafekeepActivityBinding
import ru.cse.ui.adapters.SafekeepFragmentAdapter
import ru.cse.utils.AppUtilities


class SafekeepActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private lateinit var safekeepFragmentAdapter: SafekeepFragmentAdapter
    private lateinit var searchView: SearchView
    private lateinit var menuItem: MenuItem
    private lateinit var fptr: IFptr
    private lateinit var binding: SafekeepActivityBinding

    private val MICROPHONE_REQUEST_CODE = 121
    private val REQUEST_SHOW_SETTINGS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.safekeep_activity)

        setupToolbar()
        setupTabs()
        setupNavBar()

        fptr = Fptr(application)
    }

    override fun onDestroy() {
        fptr.destroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.safekeep_toolbar_item, menu)

        menuItem = menu!!.findItem(R.id.safekeep_search)
        menuItem.setOnActionExpandListener(this)

        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setIconifiedByDefault(false)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        AppUtilities.showSnackbar(this, query, true)

        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem): Boolean {
        safekeepTabLayout.visibility = View.GONE

        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        safekeepTabLayout.visibility = View.VISIBLE

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.appNavigationSettings -> {
                val intent = Intent(application, SettingsActivity::class.java)
                intent.putExtra(SettingsActivity.DEVICE_SETTINGS, fptr.settings)
                startActivityForResult(intent, REQUEST_SHOW_SETTINGS)
            }
        }

        safekeepDrawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == MICROPHONE_REQUEST_CODE) {
            val matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            if (!matches.isEmpty()) {
                searchView.setQuery(matches[0], true)
            }
        }

        if (requestCode == REQUEST_SHOW_SETTINGS && resultCode == Activity.RESULT_OK) {
            fptr.settings = data.getStringExtra(SettingsActivity.DEVICE_SETTINGS)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (safekeepDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            safekeepDrawerLayout.closeDrawer(GravityCompat.START)

            return
        }

        if (safekeepTabLayout.selectedTabPosition != 0) {
            safekeepTabLayout.getTabAt(0)!!.select()

            return
        } else {
            super.onBackPressed()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(safekeepToolbar)
    }

    private fun setupTabs() {
        safekeepFragmentAdapter = SafekeepFragmentAdapter(supportFragmentManager)
        safekeepFragmentAdapter.setFragments()

        safekeepViewPager.adapter = safekeepFragmentAdapter
        safekeepTabLayout.setupWithViewPager(safekeepViewPager)
    }

    private fun setupNavBar() {
        val toggle = ActionBarDrawerToggle(this, safekeepDrawerLayout, safekeepToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        safekeepDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        appNavigationView.setNavigationItemSelectedListener(this)
    }

}