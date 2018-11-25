package com.camehereforstickers.luckypoint


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_new_ticket.*
import kotlin.concurrent.thread

class NewTicketFragment : Fragment() {

    lateinit var ticketNumber: String
    lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = context?.getSharedPreferences("luckypoint", Context.MODE_PRIVATE)?.getString(
            "name",
            ""
        )!!

        name_text.text = name
        number_text.text = ticketNumber

        confirm_button.setOnClickListener {
            thread {
                Thread.sleep(1000)
                killMe()
            }

        }
    }

    private fun killMe() {
        this.activity!!.supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        val activity = this.activity as MainActivity
        activity.changeFragment(activity.ticketFragment, false, "ticket")
    }


    companion object {
        fun instantiateWithTicketCode(ticketCode: String): NewTicketFragment {
            val fragment = NewTicketFragment().apply {
                ticketNumber = ticketCode
            }

            return fragment
        }
    }
}
