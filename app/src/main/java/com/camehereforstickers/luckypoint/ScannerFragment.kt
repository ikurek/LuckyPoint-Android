package com.camehereforstickers.luckypoint


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_scanner.*

class ScannerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onStart() {
        super.onStart()
        bindScanner()
        scanner_view.startScanning()
    }

    override fun onStop() {
        super.onStop()
        scanner_view.stopScanning()
    }

    override fun onResume() {
        super.onResume()
        scanner_view.startScanning()
    }

    private fun bindScanner() {
        scanner_view.setOnDecodedCallback { decoded ->
            Toast.makeText(this.context, decoded, Toast.LENGTH_SHORT).show()
        }
    }




}
