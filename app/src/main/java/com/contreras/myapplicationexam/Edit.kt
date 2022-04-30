package com.contreras.myapplicationexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.contreras.myapplicationexam.databinding.ActivityEditBinding
import com.contreras.myapplicationexam.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_edit.*

const val PARAMETER_EXTRA_NOMBRE = "nombre"
const val PARAMETER_EXTRA_CORREO = "correo"
const val PARAMETER_EXTRA_DIRECCION = "direccion"
const val PARAMETER_EXTRA_TELEFONO = "telefono"

class Edit : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityEditBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NOMBRE) != null) {
                binding.edtNombre.setText(extras.getString(PARAMETER_EXTRA_NOMBRE))
            }

            if (extras.get(PARAMETER_EXTRA_CORREO) != null) {
                binding.edtCorreo.setText(extras.getString(PARAMETER_EXTRA_CORREO))
            }

            if (extras.get(PARAMETER_EXTRA_DIRECCION) != null) {
                binding.edtDireccion.setText(extras.getString(PARAMETER_EXTRA_DIRECCION))
            }
            if (extras.get(PARAMETER_EXTRA_TELEFONO) != null) {
                binding.edtNumero.setText(extras.getString(PARAMETER_EXTRA_TELEFONO))
            }

        }
    }
    fun closeActivity(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(PARAMETER_EXTRA_NOMBRE2, binding.edtNombre.text.toString())
        intent.putExtra(PARAMETER_EXTRA_CORREO2,binding.edtCorreo.text.toString())
        intent.putExtra(PARAMETER_EXTRA_DIRECCION2, binding.edtDireccion.text.toString())
        intent.putExtra(PARAMETER_EXTRA_TELEFONO2, binding.edtNumero.text.toString())
        setResult(RESULT_OK, intent) // Set ResultCode and DataIntent
        finish()
    }
}