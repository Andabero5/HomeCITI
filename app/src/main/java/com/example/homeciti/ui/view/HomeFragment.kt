package com.example.homeciti.ui.view

import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.toColorInt
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeciti.R
import com.example.homeciti.data.model.HomeService
import com.example.homeciti.data.model.Service
import com.example.homeciti.data.model.ServiceProvider.Companion.homes
import com.example.homeciti.databinding.FragmentHomeBinding
import com.example.homeciti.databinding.ItemHomeQuickaccessBinding
import com.example.homeciti.databinding.LayoutGeneralHomePartnerBinding
import com.example.homeciti.databinding.LayoutQuickaccessHomePartnerBinding
import com.example.homeciti.ui.adapters.GeneralAdapter
import com.example.homeciti.ui.adapters.ServiceAdapter
import com.example.homeciti.ui.viewmodel.GeneralViewModel
import com.example.homeciti.ui.viewmodel.HomeViewModel
import com.example.homeciti.ui.viewmodel.ServiceViewModel
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home){

    // Declarar mi binding asociado al fragment
    private lateinit var binding: FragmentHomeBinding

    // Declaracion del adapter con su tipo
    //private lateinit var adapterService : ServiceAdapter


    // Declaracion del viewmodel
    //private val serviceViewModel by lazy { ViewModelProviders.of(this).get(ServiceViewModel::class.java)}
    private val generalViewModel by lazy { ViewModelProviders.of(this).get(GeneralViewModel::class.java)}
    private val homeViewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java)}

    // Metodo cuando la vista este creadad
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        orderDataHome()
        observeData()

    }

    fun observeData(){

        // Widget general
        generalViewModel.fetchServiceData().observe(this,{
            //adapterGeneral.setListData(it)
            //adapterGeneral.notifyDataSetChanged()
        })

    }

    // Funcion de organizar los datos que hay en el home para mostrar los layout
    fun orderDataHome(){
        homeViewModel.fetchHomeData().observe(this,{
            // Arreglar lista por order
            val orderHome = it.sortedBy { it.order }.map { it }

            // Ciclo para recorrer cada objeto de la lista
            for (item in orderHome){

                // Obtener el numero de columnas que debe de llevar el grid
                if(item.columns <= 1){
                    // No sería un gridlayout
                    println ("NO es gridLayout")
                }
                else{
                    println("SI es gridLayout")

                    // Crear el recyclerview a partir de la lista de objetos
                    var widgetView : View
                    context?.let { ctx ->
                        when (item.type){
                            "WIDGET_GENERAL" -> {
                                // asignar el comportamiento y adapter
                                //widgetView = LayoutInflater.from(context).inflate(R.layout.layout_general_home_partner,null,false)

                                widgetView = WidgetGeneralView(ctx, item)
                                binding.llHome.addView(widgetView)

                                //bindGeneral(widgetView, item)

                                // así puedo referenciar mi item
                                //var viewLayout = LayoutGeneralHomePartnerBinding.inflate(LayoutInflater.from(context),null,false)
                                // no se puede realizar
                                //binding.llHome.addView(viewLayout)

                                //LLAMAR AL METODO

                            }
                            "WIDGET_QUICK_ACCESS" -> {


                                //LLAMAR AL METODO
                                //bindQuickAccess(binding.lyQuickaccess, item)
                            }
                        }
                    }


                }

            }
        })
    }

    fun bindGeneral(bindGeneral:LayoutGeneralHomePartnerBinding, item:HomeService){
        // Texto del layout
        bindGeneral.lblGeneral.text = item.titleObj.title

        val color = verifyColor(item.titleObj.textColor)
        bindGeneral.lblGeneral.setTextColor(color.toColorInt())

        // Boton del layout
        if (item.showMore.visibility) {
            bindGeneral.btnGeneralSeemore.visibility = View.VISIBLE
            bindGeneral.btnGeneralSeemore.text = item.showMore.title
        }

    }


    // Funcion para verificar que el color esté escrito con # del hex
    fun verifyColor( color: String): String {
        var newColor : String
        if(color[0].equals("#")){
            println("Tiene caracter numeral")
            newColor = color
        }
        else{
            newColor = "#" + color
        }
        return newColor
    }

}

interface MyViewModelAccessor {
    var generalViewModel: GeneralViewModel
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
    override var generalViewModel = ViewModelProvider(activity).get(GeneralViewModel::class.java)
}