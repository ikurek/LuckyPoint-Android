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
    val profileFragment = ProfileFragment()

    var currentFragmentTag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        changeFragment(ticketFragment, false, "ticket")
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_map -> {
                changeFragment(mapFragment, false, "map")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_tickets -> {
                changeFragment(ticketFragment, false, "ticket")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_ranking -> {
                changeFragment(rankFragment, false, "rank")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                changeFragment(profileFragment, false, "profile")
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
