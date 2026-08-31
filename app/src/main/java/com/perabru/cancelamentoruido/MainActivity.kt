package com.perabru.cancelamentoruido


import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlin.math.log10
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    lateinit var txtDb: TextView
    lateinit var txtCancelado: TextView
    lateinit var btnCancelar: Button

    var cancelamento = false
    var gravando = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtDb = findViewById(R.id.txtDb)
        txtCancelado = findViewById(R.id.txtCancelado)
        btnCancelar = findViewById(R.id.btnCancelar)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            1
        )

        btnCancelar.setOnClickListener {

            cancelamento = !cancelamento

            if (cancelamento)
                btnCancelar.text = "DESATIVAR CANCELAMENTO"
            else
                btnCancelar.text = "ATIVAR CANCELAMENTO"
        }

        Thread {
            medirRuido()
        }.start()
    }

    fun medirRuido() {

        val buffer = ShortArray(1024)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        val audio = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            44100,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            1024
        )

        audio.startRecording()

        while (gravando) {

            audio.read(buffer, 0, buffer.size)

            var soma = 0.0

            for (valor in buffer) {
                soma += valor * valor
            }

            val rms = sqrt(soma / buffer.size)

            var db = 0.0

            if (rms > 0) {
                db = 20 * log10(rms)
            }

            var cancelado = 0.0

            if (cancelamento) {
                cancelado = db * 0.30
            }

            runOnUiThread {

                txtDb.text =
                    "Ruído: %.1f dB".format(db)

                txtCancelado.text =
                    "Cancelado: %.1f dB".format(cancelado)
            }
        }
    }
}