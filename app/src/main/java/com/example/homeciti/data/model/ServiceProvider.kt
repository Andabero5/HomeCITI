package com.example.homeciti.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ServiceProvider {
    companion object {

        // Crear la lista mutable de home
        val homes = mutableListOf<HomeService>(
            HomeService("WIDGET_GENERAL", TitleObj("Que quieres hacer hoy pues?","1D0071"), ShowMore("Ver mas pues","#12345678",true),3,2),
            HomeService("WIDGET_QUICK_ACCESS", TitleObj("Accesos rapidos","1D0071"), ShowMore("Ver mas hagale","#12345678",true),4,1),
            HomeService("WIDGET_BANNER",TitleObj(),ShowMore(),1,2)
        )

        // Crear nuestra Lista mutable de servicios generals
        val generals = mutableListOf<GeneralService>(
            GeneralService("Send money","https://w7.pngwing.com/pngs/936/836/png-transparent-computer-icons-currency-money-bank-i-ll-send-you-the-money-thumbnail.png", "", ""),
            GeneralService("Charge phone", "https://pngfree.io/upload/cache/upload/imgs/homepngwing/free-png-zzwmk-f350x360.png", "New", "red"),
            GeneralService("Pay services", "https://w7.pngwing.com/pngs/160/178/png-transparent-salary-payroll-payment-management-tax-pay-human-resource-management-service-people-thumbnail.png","", ""),
            GeneralService("Pay commerce", "https://w7.pngwing.com/pngs/753/261/png-transparent-mobile-payment-payment-gateway-computer-icons-payment-system-payment-gadget-electronics-service.png","", ""),
            GeneralService("Claro Pay card", "https://e7.pngegg.com/pngimages/954/306/png-clipart-computer-icons-credit-card-bank-payment-card-minimal-text-payment.png","Favorite", "blue"),
            GeneralService("My account CLABE", "https://images-na.ssl-images-amazon.com/images/I/41jkyotpKhL.png","", ""),
            GeneralService("Withdraw money", "https://www.pngkit.com/png/detail/17-172892_this-page-contains-all-info-about-money-icon.png","", ""),
            GeneralService("Movements", "https://www.pngall.com/wp-content/uploads/1/Withdraw-PNG.png","New", "red"),
            GeneralService("My wallet", "https://pngimg.com/uploads/wallet/wallet_PNG77049.png","Favorite", "blue"),
        )

        // Crear nuestra lista mutable de servicios quick_access
        val services = mutableListOf<Service>(
            Service("Expedia","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("Pase","https://cursokotlin.com/wp-content/uploads/2017/07/flash.png","",""),
            Service("Musica","https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg","New","red"),
            Service("Noticias","https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg","",""),
            Service("Telcel","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Favorite","blue"),
            Service("Prestamo","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("Telmex","https://e7.pngegg.com/pngimages/347/142/png-clipart-logo-telmex-mobile-phones-font-aol-logo-text-logo.png","",""),
            Service("Cerca de mi","https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg","New","red"),
            Service("Promos","https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg","",""),
            Service("Sky","https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","Favorite","blue"),
            Service("CFE","https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg","New","red"),
            Service("¿Que es Claro Pay?","https://cursokotlin.com/wp-content/uploads/2017/07/flash.png","",""),
        )
    }
}