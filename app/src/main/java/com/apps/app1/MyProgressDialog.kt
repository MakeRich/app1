package com.apps.app1

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.CountDownTimer

abstract class MyProgressDialog : AsyncTask<Void, Void, Void> {

    val contexto: Context
    lateinit var progressDialog: ProgressDialog
    val titulo: String
    val mensajes: List<String>
    val duracion: Long

    constructor(contexto: Context, titulo: String, mensajes: List<String>, duracion: Long) {
        this.contexto = contexto
        this.titulo = titulo
        this.mensajes = mensajes
        this.duracion = duracion
    }

    override fun onPreExecute() {
        super.onPreExecute()
        this.progressDialog = ProgressDialog(contexto)
        this.progressDialog.setCancelable(false)
        this.progressDialog.setTitle(this.titulo)
        this.progressDialog.show()

        this.mostrarMensajes()
    }

    override fun onPostExecute(result: Void?) {
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss()
        }

        // Abrir activity
        abrirActivity()

        // Otras operaciones
        otrasOperaciones()
    }

    abstract fun abrirActivity()

    abstract fun otrasOperaciones()

    /**
     * Muestra todos los mensajes de la lista repartidos equitativamente en todo el tiempo que dure
     * el progress dialog.
     */
    private fun mostrarMensajes(): CountDownTimer {
        val numeroMensajes: Int = this.mensajes.size
        var contador = 0

        // Se le suman 100 milisegundos para que muestre todos los mensajes
        return object : CountDownTimer(this.duracion + 100, this.duracion / numeroMensajes) {

            override fun onTick(millisUntilFinished: Long) {
                if (contador < numeroMensajes) {
                    progressDialog.setMessage(mensajes.get(contador))
                }
                contador += 1
            }

            override fun onFinish() {
                contador = 0
            }
        }.start()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        Thread.sleep(this.duracion)

        return null
    }

}