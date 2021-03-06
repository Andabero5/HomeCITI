package com.example.homeciti.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.ui.viewmodel.BannerViewModel
import com.example.homeciti.ui.viewmodel.GeneralViewModel
import com.example.homeciti.ui.viewmodel.HomeViewModel
import com.example.homeciti.ui.viewmodel.QuickAccessViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    // Declarar mi binding asociado al fragment
    private lateinit var binding: FragmentHomeBinding

    // Declaracion del viewmodel
    private val homeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    // Metodo cuando la vista este creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        loadDataHome()
    }

    private fun orderList(listHome: MutableList<HomeService>): List<HomeService> {
        return listHome.sortedBy { it.order }.map { it }
    }

    // Funcion de organizar los datos que hay en el home para mostrar los layout
    private fun loadDataHome() {
        homeViewModel.fetchHomeData().observe(viewLifecycleOwner) { listHome ->

            if (listHome.isNullOrEmpty()) {
                binding.clUploadHome.visibility = View.VISIBLE

                // Correr skeleton shimmer
                //binding.shimmerLayoutQuick.visibility = View.GONE
                //binding.shimmerLayoutGeneral.visibility = View.GONE
                //binding.shimmerLayoutBanner.visibility = View.GONE

                //binding.shimmerLayoutQuick.stopShimmer()
                //binding.shimmerLayoutGeneral.stopShimmer()
                //binding.shimmerLayoutBanner.stopShimmer()

            } else {
                binding.clUploadHome.visibility = View.GONE
                //binding.shimmerLayoutGeneral.visibility = View.VISIBLE
                //binding.shimmerLayoutGeneral.startShimmer()

                // Arreglar lista por order
                val orderHome = orderList(listHome)

                // Ciclo para recorrer cada objeto de la lista
                for (item: HomeService in orderHome) {

                    // Crear el recyclerview a partir de la lista de objetos
                    var widgetView: View

                    context?.let { ctx ->
                        when (item.type) {
                            "WIDGET_GENERAL" -> {
                                widgetView = WidgetGeneralView(ctx, item)
                                //binding.shimmerLayoutGeneral.stopShimmer()
                                //binding.shimmerLayoutGeneral.visibility = View.GONE
                                binding.llHome.addView(widgetView)
                            }
                            "WIDGET_QUICK_ACCESS" -> {
                                widgetView = WidgetQuickAccessView(ctx, item)
                                //binding.shimmerLayoutQuick.stopShimmer()
                                //binding.shimmerLayoutQuick.visibility = View.GONE
                                binding.llHome.addView(widgetView)
                            }
                            "WIDGET_BANNER" -> {
                                widgetView = WidgetBannerView(ctx, item)
                                //binding.shimmerLayoutBanner.stopShimmer()
                                //binding.shimmerLayoutBanner.visibility = View.GONE
                                binding.llHome.addView(widgetView)
                            }
                        }
                    }
                }
            }
        }
    }
}

interface MyViewModelAccessor {
    var generalViewModel: GeneralViewModel
    var quickAccessViewModel: QuickAccessViewModel
    var bannerViewModel: BannerViewModel

    val activity: FragmentActivity
}

class MyViewModelInjector(val context: Context) : MyViewModelAccessor {
    override val activity: FragmentActivity by lazy {
        try {
            context as FragmentActivity
        } catch (exception: ClassCastException) {
            throw ClassCastException("Please ensure that the provided Context is a valid FragmentActivity")
        }
    }
    override var generalViewModel = ViewModelProvider(activity)[GeneralViewModel::class.java]
    override var quickAccessViewModel = ViewModelProvider(activity)[QuickAccessViewModel::class.java]
    override var bannerViewModel = ViewModelProvider(activity)[BannerViewModel::class.java]

}