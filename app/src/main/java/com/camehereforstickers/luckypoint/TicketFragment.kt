package com.camehereforstickers.luckypoint


import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_ticket.*

class TicketFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onStart() {
        super.onStart()
        bindUI()
    }

    private fun bindUI() {
        scan_button.setOnClickListener {

            Dexter.withActivity(this.activity)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        goToScanFragment()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                        token!!.continuePermissionRequest()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    }

                }).check()
        }

        buy_button.setOnClickListener {
            goToBuyFragment()
        }
    }

    private fun goToScanFragment() {
        val mainActivity = this.activity as MainActivity
        mainActivity.changeFragment(ScannerFragment(), true, "scan")
    }

    private fun goToBuyFragment() {

        val mainActivity = this.activity as MainActivity
        mainActivity.changeFragment(BuyTicketFragment(), true, "buy")
    }



}
