package com.davidgrath.restaurantapptwo.restaurants

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.davidgrath.restaurantapptwo.R
import com.davidgrath.restaurantapptwo.databinding.FragmentDiscoverBinding
import com.davidgrath.restaurantapptwo.databinding.MapAnnotationRestaurantBinding
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.DiscoverViewModel
import com.davidgrath.restaurantapptwo.restaurants.viewmodels.factories.DiscoverFragmentViewModelFactory
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.Style
import com.mapbox.maps.ViewAnnotationAnchor
import com.mapbox.maps.ViewAnnotationOptions
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.viewannotation.ViewAnnotationManager


class DiscoverFragment : Fragment() {

    private val REQUEST_CODE_LOCATION = 100

    lateinit var binding: FragmentDiscoverBinding
    lateinit var viewModel : DiscoverViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity(),
            DiscoverFragmentViewModelFactory((requireActivity().application as com.davidgrath.restaurantapptwo.application.RestaurantAppTwo).getLocationUseCase()))
            .get(DiscoverViewModel::class.java)
        val locationPermission = Manifest.permission.ACCESS_COARSE_LOCATION
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            &&  requireActivity().checkSelfPermission(locationPermission) != PackageManager.PERMISSION_GRANTED) {
                val permissionsRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(), object : ActivityResultCallback<Boolean> {
                    override fun onActivityResult(result: Boolean?) {
                        result?.let {
                            if(it) {
                                onMapReady()
                            }
                        }
                    }
                })
            permissionsRequestLauncher.launch(locationPermission)
        } else {
            onMapReady()
        }
        return binding.root
    }

    private fun onMapReady() {
        val map = binding.mapViewDiscover.getMapboxMap()
        val userLocation = viewModel.getUserLocation()
        map.setCamera(
            CameraOptions.Builder()
                .center(Point.fromLngLat(userLocation.lng, userLocation.lat))
                .zoom(11.0)
                .build()
        )
        map.loadStyleUri(Style.MAPBOX_STREETS) {
            val locationComponent = binding.mapViewDiscover.location
            locationComponent.updateSettings {
                enabled = true
                pulsingEnabled = true
                locationPuck = LocationPuck2D(
                    bearingImage = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.map_annotation_location_marker
                    ),
                    scaleExpression = interpolate {
                        linear()
                        zoom()
                        stop {
                            literal(0.0)
                            literal(0.6)
                        }
                        stop {
                            literal(20.0)
                            literal(1.0)
                        }
                    }.toJson()
                )
            }
        }
        val annotationPlugin = binding.mapViewDiscover.annotations
        val pointAnnotationManager = annotationPlugin.createPointAnnotationManager(binding.mapViewDiscover)
        val viewAnnotationManager = binding.mapViewDiscover.viewAnnotationManager
        for(location in viewModel.getAllLocations()) {
            createPointAndViewAnnotation(pointAnnotationManager, viewAnnotationManager, location)
        }
    }

    private fun createPointAndViewAnnotation(pointAnnotationManager: PointAnnotationManager, viewAnnotationManager: ViewAnnotationManager, location: SimpleLocation) {
        val point = Point.fromLngLat(location.lng, location.lat)
        val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(bitmapFromDrawableRes(requireContext(), R.drawable.map_annotation_restaurant_marker)!!)
            .withIconAnchor(IconAnchor.BOTTOM)
        val pointAnnotation = pointAnnotationManager.create(pointAnnotationOptions)
        val annotationView = getAnnotationView(location)
        viewAnnotationManager.addViewAnnotation(
            annotationView,
            ViewAnnotationOptions.Builder()
                .geometry(point)
                .associatedFeatureId(pointAnnotation.featureIdentifier)
                .anchor(ViewAnnotationAnchor.BOTTOM)
                .offsetY(pointAnnotation.iconImageBitmap?.height!!.toInt())
                .build()
        )
        pointAnnotationManager.addClickListener { clickedAnnotation ->
            if(clickedAnnotation == pointAnnotation) {
                annotationView.visibility = if(annotationView.visibility == View.VISIBLE) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
            true
        }
    }

    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int): Bitmap? {
        return convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))
    }

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
// copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }


    private fun getAnnotationView(location : SimpleLocation): View {
        val viewBinding = MapAnnotationRestaurantBinding.inflate(LayoutInflater.from(requireContext()), binding.mapViewDiscover, false)
        val images = arrayOf(
            R.raw.sample_food_square_1,
            R.raw.sample_food_square_2,
            R.raw.sample_food_square_3,
            R.raw.sample_food_1,
            R.raw.sample_food_2,
            R.raw.sample_food_3,
            R.raw.sample_food_4,
            R.raw.sample_food_5,
            R.raw.sample_food_6,
            R.raw.sample_food_7,
            R.raw.sample_food_8,
            R.raw.sample_food_9
        )
        with(viewBinding) {
            Glide.with(requireContext())
                .load(images.random())
                .fallback(R.drawable.simple_image_placeholder)
                .into(imageviewMapAnnotation)
            textviewMapAnnotationTitle.text = location.name
            textviewMapAnnotationAddress.text = "Placeholder address"
            ratingBarMapAnnotation.rating = 4.5F
        }
        return viewBinding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(): DiscoverFragment {
            return DiscoverFragment().apply {
            }
        }
    }
}