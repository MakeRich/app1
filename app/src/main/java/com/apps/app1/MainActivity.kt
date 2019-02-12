package com.apps.app1

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*

class MainActivity : Activity() {

    lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarFuenteTextos()

        inicializarAdmob()

        cargarAnunciosBanner()
    }

    /**
     * Establece la fuente personalizada a los textos.
     */
    private fun inicializarFuenteTextos() {
        var customFont = Typeface.createFromAsset(assets, "font/thesims.ttf")

        val introducirUsuario = findViewById<TextView>(R.id.introducir_usuario_text_view)
        val continuar = findViewById<Button>(R.id.continuar_bt)

        introducirUsuario.typeface = customFont
        continuar.typeface = customFont
    }

    /**
     * Iniciliza AdMob.
     */
    private fun inicializarAdmob() {
        MobileAds.initialize(this, "ca-app-pub-1560403654736360~3709588022")
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
     * Comprueba que el nombre de usuario no esté vacío.
     * OJO -> tiene que ser sin private sino no lo detecta desde el xml
     */
    fun comprobarUsuario(view: View?) {
        val usuario = findViewById<EditText>(R.id.usuario_edit_text)
        if (usuario.text.toString().isNotEmpty()) {
            abrirProgressDialog()
        } else {
            Toast.makeText(this, getString(R.string.usuario_vacio), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Abre el dialogo de "carga".
     */
    private fun abrirProgressDialog() {
        val mensajes: List<String> = Arrays.asList(getString(R.string.verificando_usuario))
        MyProgressDialog(this, getString(R.string.verificacion_datos), mensajes, 3000).execute()
    }
}