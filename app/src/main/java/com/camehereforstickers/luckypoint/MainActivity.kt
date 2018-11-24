package com.camehereforstickers.luckypoint

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mapFragment = MapFragment()
    val ticketFragment = TicketFragment()
    val rankFragment = RankFragment()

    var currentFragmentTag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        changeFragment(mapFragment, false, "map")
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(mapFragment, false, "map")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                changeFragment(ticketFragment, false, "ticket")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                changeFragment(rankFragment, false, "rank")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun changeFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        if (currentFragmentTag != tag) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_frame, fragment).also {
                        if (addToBackStack) it.addToBackStack(tag)
                    }.commit()
            currentFragmentTag = tag
        }
    }
}
