package com.camehereforstickers.luckypoint


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = context?.getSharedPreferences("luckypoint", Context.MODE_PRIVATE)

        name_text.text = sharedPrefs?.getString("name", "")
        number_text.text = sharedPrefs?.getString("number", "")

        sign_out_button.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        context?.getSharedPreferences("luckypoint", Context.MODE_PRIVATE)?.edit()?.clear()?.apply()

        val intent = Intent(context, LoginActivity::class.java)

        activity?.finish()
        startActivity(intent)
    }


}
