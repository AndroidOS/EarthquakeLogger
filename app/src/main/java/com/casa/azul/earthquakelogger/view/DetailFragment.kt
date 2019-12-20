package com.casa.azul.earthquakelogger.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.casa.azul.earthquakelogger.R
import com.casa.azul.earthquakelogger.model.Feature
import com.casa.azul.earthquakelogger.viewmodel.ListViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "DetailFragment"

const val DayInMilliSec = 86400000
class DetailFragment : Fragment(), OnMapReadyCallback {

    private lateinit var detailQuake: Feature
    private lateinit var viewModel: ListViewModel

    private var quakeNumber = 0
    private lateinit var map: GoogleMap
//    private lateinit var mapView: MapView
//    private lateinit var view1: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[ListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        observeViewModel()


        arguments?.let {
            quakeNumber = DetailFragmentArgs.fromBundle(it).uuid
        }

        val viewModel = activity?.run {
            ViewModelProviders.of(this)[ListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        detailQuake = viewModel.getDetailQuake(quakeNumber)

        mapDetail.onCreate(null)
        mapDetail.onResume()
        mapDetail.getMapAsync(this)

    }

    private fun observeViewModel() {

        viewModel.menu_email.observe(this, Observer { isEmail ->
            isEmail?.let {

                if (it) {

                    Toast.makeText(activity, "Menu selection", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        Log.d(TAG, "on Map ready")

        if (detailQuake != null) {
            val quakeLtLn = LatLng(
                detailQuake.geometry?.coordinates?.get(1)!!,
                detailQuake.geometry?.coordinates!![0]
            )

            map.addMarker(MarkerOptions().position(quakeLtLn).title(detailQuake.properties?.place.toString()))
            map.moveCamera(CameraUpdateFactory.newLatLng(quakeLtLn))

            val quakeTime = getDateTime(detailQuake.properties?.time.toString())

            tv_quake_detail.text = "Place: ${detailQuake.properties?.place}\n" +
                    "Mag: ${detailQuake.properties?.mag.toString()}\n" +
                    "Time: $quakeTime"
        }
    }


    private fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm") //MM/dd/yyyy
            val netDate = Date(s.toLong()).addDays(1)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun Date.addDays(numberOfDaysToAdd: Int): Date {
        return Date(this.time + numberOfDaysToAdd * DayInMilliSec)
    }


}
