package com.example.pokedex.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper.getMainLooper
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.data.Pokemon
import com.example.pokedex.databinding.FragmentListOfPokemonBinding
import com.example.pokedex.ui.adapter.PokemonAdapter
import com.example.pokedex.ui.models.toDetailPokemonModel
import com.example.pokedex.ui.viewmodels.ListOfPokemonViewModel
import com.example.pokedex.ui.viewmodels.PokemonActions
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfPokemon : Fragment() {

    private val binding : FragmentListOfPokemonBinding by lazy {
        FragmentListOfPokemonBinding.inflate(layoutInflater)
    }
    private val viewModel: ListOfPokemonViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter
    private var listAux = mutableListOf<Pokemon>()

    private val requestingLocationUpdates = true
    private var count: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindActionLiveData()
        initRecyclerView()

        binding.btShowPokemon.setOnClickListener {
            viewModel.getNewPokemon()
        }
            requestPermission()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getListOfPokemon()

        if (requestingLocationUpdates) {
            count = 0
            startLocationUpdates()
        }

    }

    private fun bindActionLiveData() {
        viewModel.getActionsLiveData().observe(viewLifecycleOwner,this::handleAction)
    }

    private fun handleAction(actions: PokemonActions){
        when (actions) {
            is PokemonActions.Message -> showMessage(actions.msg)
            is PokemonActions.ShowNewPokemon -> showNewPokemon(actions.pokemon)
            is PokemonActions.Loading -> loadingBar(actions.loading)
            is PokemonActions.SetCurrentList -> setCurrentList(actions.list)
        }
    }

    private fun setCurrentList(list: List<Pokemon>) {
        listAux.clear()
        listAux.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun loadingBar(loading: Boolean) {
        if (loading) {
            binding.pBar.isVisible
        }else
            binding.pBar.isGone
    }

    private fun initRecyclerView() {
        adapter = PokemonAdapter(listAux,this::navigateToDetailPokemon)
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter
    }

    private fun showMessage(msg: String) {
        loadingBar(false)
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetailPokemon(pokemon: Pokemon){
        val model = pokemon.toDetailPokemonModel()
        val action = ListOfPokemonDirections.actionListOfPokemonToDetailPokemon(model)
        findNavController().navigate(action)
    }

    private fun showNewPokemon(pokemon: Pokemon) {
        loadingBar(false)
        navigateToDetailPokemon(pokemon)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            count += 1
            if(count > 1) {
                viewModel.getNewPokemon()
                vibratePhone()
            }
        }
    }

    private var mLocationRequest: LocationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(1500)
            .setMaxUpdateDelayMillis(2500)
            .setMinUpdateDistanceMeters(10.0f)
            .build()

    private var fusedLocationClient: FusedLocationProviderClient? = null

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                getLastLocation()
            }
        }
    }

    private fun requestPermission() {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation()
        } else {
            locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION))
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
       // fusedLocationClient?.lastLocation?.addOnSuccessListener { location : Location? ->
               // location?.let {
                   // onSetMap(it.latitude, it.longitude)
                //}
            //}
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient?.requestLocationUpdates(mLocationRequest, locationCallback, getMainLooper())
    }

    private fun vibratePhone() {
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            // create vibrator effect with the constant EFFECT_HEAVY_CLICK
            val vibrationEffect = VibrationEffect.createOneShot(2000, VibrationEffect.EFFECT_HEAVY_CLICK)

            // it is safe to cancel other vibrations currently taking place
            vibrator.cancel();

            vibrator.vibrate(vibrationEffect);
        } else {
            vibrator.vibrate(2000)
        }
    }
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }









}