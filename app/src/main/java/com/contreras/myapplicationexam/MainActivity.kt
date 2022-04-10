package com.contreras.myapplicationexam

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*

const val EDIT_REQUEST = 991
const val PARAMETER_EXTRA_NOMBRE2 = "nombre"
const val PARAMETER_EXTRA_CORREO2 = "correo"
const val PARAMETER_EXTRA_DIRECCION2 = "direccion"
const val PARAMETER_EXTRA_TELEFONO2 = "telefono"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendExplicit(view: android.view.View) {
        val nombre = tvNombre.text.toString()
        val correo = tvCorreo.text.toString()
        val direccion = tvDireccion.text.toString()
        val telefono = tvTelefono.text.toString()

        validateInputFields(nombre, correo, direccion, telefono)
        goDetailActivity(nombre, correo, direccion, telefono)
    }

    private fun goDetailActivity(
        nombre: String,
        correo: String,
        direccion: String,
        telefono: String
    ) {
        val intent = Intent(this, Edit::class.java)
        intent.putExtra(PARAMETER_EXTRA_NOMBRE, nombre)
        intent.putExtra(PARAMETER_EXTRA_CORREO, correo)
        intent.putExtra(PARAMETER_EXTRA_DIRECCION, direccion)
        intent.putExtra(PARAMETER_EXTRA_TELEFONO, telefono)
        startActivityForResult(intent, EDIT_REQUEST)
    }

    private fun validateInputFields(
        nombre: String,
        correo: String,
        direccion: String,
        telefono: String
    ) {
        if (nombre.isBlank() && correo.isBlank() && direccion.isBlank() && telefono.isBlank()) {
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show()
            return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(ContentValues.TAG, "requestCode:$requestCode")
        Log.d(ContentValues.TAG, "resultCode:$resultCode")
        Log.d(ContentValues.TAG, "data:" + android.R.attr.data)

        when (requestCode) {
            EDIT_REQUEST -> {
                Log.d(ContentValues.TAG, "Regresamos del EDIT")
                if (resultCode === RESULT_OK) {
                    val valor: String = data?.extras?.getString("valor").toString()
                }
                val extras = data?.extras
                if (extras != null) {
                    if (extras.get(PARAMETER_EXTRA_NOMBRE2) != null) {
                        tvNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE2))
                    }

                    if (extras.get(PARAMETER_EXTRA_CORREO2) != null) {
                        tvCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO2))
                    }

                    if (extras.get(PARAMETER_EXTRA_DIRECCION2) != null) {
                        tvDireccion.setText(extras.getString(PARAMETER_EXTRA_DIRECCION2))
                    }
                    if (extras.get(PARAMETER_EXTRA_TELEFONO2) != null) {
                        tvTelefono.setText(extras.getString(PARAMETER_EXTRA_TELEFONO2))
                    }
                }
            }
        }

    }

    fun callPhone(view: View) {
        val phone = tvTelefono.text.toString()
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    }

    fun senMessage(view: View) {
        val nombre = tvNombre.text.toString()
        val telefono = tvTelefono.text.toString()

        val uri = Uri.parse("smsto:$telefono")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra("sms_body", "¿Que tal $nombre como estas?")
        startActivity(it)

    }

    fun sentWatssap(view: View) {
        try {
            val text = "Quiero comunicarme contigo"
            val telefono = tvTelefono.text.toString()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$telefono&text=$text%22")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}