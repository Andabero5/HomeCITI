package com.example.homeciti.data.model

class ServiceProvider {
    companion object {

        // Crear la lista mutable de home
        val homes = mutableListOf(
            HomeService("WIDGET_GENERAL", TitleObj("¿Qué quieres hacer hoy?","#1D0071"), ShowMore("Ver más","#1D0071",true),3,1),
            HomeService("WIDGET_QUICK_ACCESS", TitleObj("Acceso rápido","#1D0071"), ShowMore("Ver más","#1D0071",true),4,3),
            HomeService("WIDGET_BANNER", TitleObj(), ShowMore(),1,2),
            //HomeService("WIDGET_BANNER",TitleObj("Banner","#1D0071"),ShowMore("Ver más", "#1D0071", true),1,2)
        )

        // Crear nuestra Lista mutable de servicios generals
        /*
        val generals = mutableListOf(
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
        val services = mutableListOf(
            QuickAccessService("Expedia","https://www.nicepng.com/png/full/395-3957278_expedia-png-get-started-expedia-logo-png.png","New","red"),
            QuickAccessService("Pase","https://pbs.twimg.com/profile_images/725759818267422720/hYsLwrGg_400x400.jpg","",""),
            QuickAccessService("Musica","https://w7.pngwing.com/pngs/727/579/png-transparent-app-store-music-itunes-computer-icons-apple-apple-purple-violet-text-thumbnail.png","New","red"),
            QuickAccessService("Noticias","https://cdn.pixabay.com/photo/2018/10/26/09/24/news-3774160_960_720.png","",""),
            QuickAccessService("Telcel","https://toppng.com/uploads/preview/telcel-digital-pcs-vector-logo-free-11574029807u5xja7ooh1.png","Favorite","blue"),
            QuickAccessService("Prestamo","https://e7.pngegg.com/pngimages/711/287/png-clipart-payday-loan-money-bank-financial-services-give-money-text-service.png","New","red"),
            QuickAccessService("Telmex","https://e7.pngegg.com/pngimages/347/142/png-clipart-logo-telmex-mobile-phones-font-aol-logo-text-logo.png","",""),
            QuickAccessService("Cerca de mi","https://flyclipart.com/thumb2/flat-location-logo-icons-png-934757.png","New","red"),
            QuickAccessService("Promos","https://e7.pngegg.com/pngimages/37/353/png-clipart-sales-promotion-discounts-and-allowances-computer-icons-marketing-public-relations-logo-thumbnail.png","",""),
            QuickAccessService("Sky","https://image.pngaaa.com/738/2524738-middle.png","Favorite","blue"),
            QuickAccessService("CFE","https://cpng.pikpng.com/pngl/s/365-3652622_cfe-logo-comision-federal-de-electricidad-png-sport.png","New","red"),
            QuickAccessService("¿Que es Claro Pay?","https://play-lh.googleusercontent.com/5sTC1Sa7WI0SHwVK9dJo2hbq-JC-iUVW_ymmWnlinLlqZAf0yWqn_LJBC8p-scn03qk_","",""),
        )

         */

        val banners = mutableListOf(
            BannerService("https://img.freepik.com/psd-gratis/portada-facebook-muebles-plantilla-banner-web_237398-331.jpg?size=626&ext=jpg","New","#e03333"),
            BannerService("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTg9RsCUqU65wQCUS92rwdigFiQcCHmOB_i0Q&usqp=CAU","Favorite","#0012B5"),
            BannerService("https://www.guiaspracticas.com/wp-content/uploads/image/banner.jpg","","")
        )
    }
}