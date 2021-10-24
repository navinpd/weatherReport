package com.big.myweather.presentation.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.big.myapplication.commons.ViewModelFactory
import com.big.myapplication.commons.disposedBy
import com.big.myapplication.commons.observe
import com.big.myapplication.commons.withViewModel
import com.big.myweather.R
import com.big.myweather.databinding.FragmentWeatherBinding
import com.big.myweather.di.WeatherApp
import com.big.myweather.domain.model.Location
import com.big.myweather.presentation.model.WeatherBaseUiModel
import com.big.myweather.presentation.ui.adapter.WeatherAdapter
import com.big.myweather.presentation.viewmodel.WeatherViewModel
import com.big.myweather.presentation.viewmodel.WeatherViewState
import com.big.myweather.util.LocationPermissionChecker
import com.big.myweather.util.MarginItemDecoration
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.internal.functions.Functions
import javax.inject.Inject

internal class WeatherFragment : BaseFragment<FragmentWeatherBinding>(R.layout.fragment_weather), HasAndroidInjector {

    companion object {
        @JvmStatic
        fun newInstance() = WeatherFragment()

        private const val LOCATION_PERMISSION_REQUEST_CODE = 101
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val adapter = WeatherAdapter()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        withViewModel<WeatherViewModel>(viewModelFactory) {
            observeWeatherReportViewState()
            observeWeatherViewState()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WeatherApp.inject(this)
        super.onCreate(savedInstanceState)
    }



    private fun WeatherViewModel.observeWeatherReportViewState() {
        observe(weatherReportViewState) {
            updateRecyclerView(it)
        }
    }

    private fun WeatherViewModel.observeWeatherViewState() {
        observe(weatherViewState) {
            when (it) {
                is WeatherViewState.LocationPermissionMissing -> {
                    if (it.show) {
                        showLocationPermissionUi()
                    } else {
                        hideErrorView()
                    }
                }
                is WeatherViewState.ShowError -> {
                    showErrorView()
                    attachErrorViewListener()
                }
                is WeatherViewState.HideError -> {
                    hideErrorView()
                }
                is WeatherViewState.HideLoading -> {
                    hideLoading()
                }
                is WeatherViewState.ShowLoading -> {
                    showLoading()
                }
            }
        }
    }

    private fun attachErrorViewListener() {
        viewBinding.run {
            errorView.setErrorDescription(getString(R.string.some_error_occured))
            errorView.addRetryButtonClickObservable()
                .subscribe({
                    getLocation()
                }, Functions.emptyConsumer())
                .disposedBy(compositeDisposable)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val activity = requireActivity()
        if(LocationPermissionChecker.isLocationPermissionGranted(activity)) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    viewModel.onViewReady(
                        Location(latitude = it.latitude, longitude = it.longitude)
                    )
                }
        }
    }

    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun showLocationPermissionUi() {
        viewBinding.run {
            showErrorView()
            attachPermissionErrorViewListener()
            hideLoading()
        }
    }

    private fun updateRecyclerView(data: List<WeatherBaseUiModel>) {
        adapter.update(data)
    }

    private fun showErrorView() {
        viewBinding.errorView.isVisible = true
    }

    private fun hideErrorView() {
        viewBinding.errorView.isVisible = false
    }

    override fun showLoading() {
        viewBinding.progressBar.isVisible = true
    }

    override fun hideLoading() {
        viewBinding.progressBar.isVisible = false
    }

    private fun attachPermissionErrorViewListener() {
        viewBinding.run {
            errorView.setErrorDescription(getString(R.string.location_service_requred))
            errorView.addRetryButtonClickObservable()
                .subscribe({
                    requestLocationPermission()
                }, Functions.emptyConsumer())
                .disposedBy(compositeDisposable)
        }
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        checkLocationPermission()
        viewBinding.run {
            weatherRecyclerView.addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.d2).toInt()
                )
            )
            weatherRecyclerView.adapter = adapter
        }
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                onLocationPermissionGranted()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showLocationPermissionUi()
            }
            else -> {
                requestLocationPermission()
            }
        }
    }

    private fun onLocationPermissionGranted() {
        hideErrorView()
        getLocation()
    }

    override fun bindView(view: View) = FragmentWeatherBinding.bind(view)

    override fun androidInjector() = androidInjector
}