package com.apps.app1

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd

class Formularioctivity : Activity() {

    lateinit var adView : AdView
    lateinit var intersticialAd : InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_activity)

        inicializarFuenteTextos()

        cargarAnunciosBanner()

        inicializarIntersticial()

        cargarAnunciosIntersticial()
    }

    /**
     * Establece la fuente personalizada a los textos.
     */
    private fun inicializarFuenteTextos() {
        var customFont = Typeface.createFromAsset(assets, "font/thesims.ttf")
        val introducirUsuario = findViewById<TextView>(R.id.introducir_usuario)
        introducirUsuario.typeface = customFont
    }

    /**
     * Carga los anuncios del banner.
     */
    private fun cargarAnunciosBanner() {
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder()
            .addTestDevice("971E70F52A188292A6C04A6A5DD4D145")
            .build()
        adView.loadAd(adRequest)
    }


    /**
     * Inicializa el intersticial.
     */
    private fun inicializarIntersticial() {
        intersticialAd = InterstitialAd(this)
        intersticialAd.adUnitId = "ca-app-pub-1560403654736360/3106952798"
    }

    /**
     * Carga los anuncios del intersticial.
     */
    private fun cargarAnunciosIntersticial() {
        val adRequest = AdRequest.Builder()
            .addTestDevice("971E70F52A188292A6C04A6A5DD4D145")
            .build()
        intersticialAd.loadAd(adRequest)
    }
}