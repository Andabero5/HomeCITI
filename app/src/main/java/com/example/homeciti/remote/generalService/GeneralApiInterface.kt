package com.example.homeciti.remote.generalService

import retrofit2.Call
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GeneralApiInterface {
    @GET("general.json?jk=AFshE3VrX65KmxOb-gFhT4qgzzJmjaSxQAlSyBWSeWX5_UoOTK90hJIPYD2M8S35UmAvkCWJVVBCUzBmyKJMYYYfrab_6iCkuz6DozdjoAlMMrHEJ3QI-jbp9l0-r1e1p-ZDTW-DtHd343g0fD_cSWleA99gy992Ieu_nHfwaLdb8PBs01TxszYVC1vcbMP5Qe1SxR13MgsKG8dnypg_b9I35iYmsvXRxeP2gnNq-gV7bjqh8zzwr6fXM2dq_AwKUGgJhoKf0bjjwpPhymU54yb7ZiN1u2X7eMOEmmp-vPcS90vivKmVGCdRxiUoF0212zxmwATDF9ob4zESUpSaXVAYUnhndLwySPY69cHVUwS0GxQDsptIFJBryL14WqK6qJmDXUIVgVdxWT6x7yeKdFQIMWYTjFn5WwYGHZuK-CncL9Rgmv3hFp6ZDWBV8_aADjhBNgxlm5s2JTaAwSTCXDfpU_k6026taRBr8FrbMyPu6ifRY8PrslXMiDotfx76BeEhV-Vo_ZSOvqRLfGqd8jZPEPWwBvlHi9UFxwCLLgric9rFp_xbEkkRIVxodth-XKjgXcP17nL1sD1UGdn0yN_TleayVnMPHDXkI1lrSZTv_tT4iSfQZHmpqlbm24BM2Dyt3ixMNOscCx5g7Ll9IbzyVQLp-7xcn_SUw5W4KAroGmM2PQM5SK26wSIxQrKdxnWfid1gaiRQGTH1oHULa4Z4QG6P-PShiasSrTnq0jp5_amiJNgl7UT5UPvqFzEpRGc8bViDrC4BzN8jGP2lbNBPfSptmL_BuyxZQWW06ACTPNxseal4SszznpMQ08zOE43Mcy_It_BBMTlfbtpTDUXUKHP6Pt935xjPm6DnMPbsWos3ODHfoIUpeXjxxp0HOt_0-WXaB2OkwcM6mOo0YIQm32yqwWCspkvvzYoTpkkAE9LrolhRxD9B3P-F-XfS1LPcFXdJ&isca=1")
    fun getGenerals() : Call<JSONObject>

    companion object {

        var BASE_URL = "https://00f74ba44b6e045459d8f9bca6846ebaa63273f3b2-apidata.googleusercontent.com/download/storage/v1/b/example_apiget1/o/"

        fun create() : GeneralApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(GeneralApiInterface::class.java)

        }
    }
}