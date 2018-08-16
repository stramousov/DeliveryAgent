package ru.cse.ui.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.app_navigation_view.*
import kotlinx.android.synthetic.main.waybill_activity.*
import ru.cse.R
import ru.cse.ui.adapters.WaybillFragmentAdapter

class WaybillActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var waybillAdapter: WaybillFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waybill_activity)

        setupToolbar()
        setupTabs()
        setupNavBar()
    }

    private fun setupToolbar() {
        setSupportActionBar(waybillToolbar)
    }

    private fun setupTabs() {
        waybillAdapter = WaybillFragmentAdapter(supportFragmentManager)
        waybillAdapter.setFragments()
    }

    private fun setupNavBar() {
        val toggle = ActionBarDrawerToggle(this, waybillDrawerLayout, waybillToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        waybillDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        appNavigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        waybillDrawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}
