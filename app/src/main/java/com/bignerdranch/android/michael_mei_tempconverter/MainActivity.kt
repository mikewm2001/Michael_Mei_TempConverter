package com.bignerdranch.android.michael_mei_tempconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI components
        var celsiusBar : SeekBar = findViewById(R.id.celsiusBar)
        var fahrenheitBar : SeekBar = findViewById(R.id.fahrenheitBar)
        var celsiusText : TextView = findViewById(R.id.textCelsius)
        var fahrenheitText : TextView = findViewById(R.id.textFahrenheit)
        var textIntersting : TextView = findViewById(R.id.textInteresting)

//        celsiusBar.min = 0
        celsiusBar.max = 100
//        fahrenheitBar.min = 0
        fahrenheitBar.max = 212

        // Initial values for seekBars
        celsiusBar.progress = 0
        fahrenheitBar.progress = 0

        // Conversion functions
        fun celsiusToFahrenheit(celsius: Int): Int {
            return (celsius * 9 / 5) + 32
        }

        fun fahrenheitToCelsius(fahrenheit: Int): Int {
            return ((fahrenheit - 32) * 5 / 9)
        }

        // SeekBar change listener
        celsiusBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update Celsius text
                celsiusText.text = getString(R.string.celsius_format, progress)

                // Calculate corresponding Fahrenheit value
                val fahrenheitValue = celsiusToFahrenheit(progress)
                // Update Fahrenheit SeekBar and text
                fahrenheitBar.progress = fahrenheitValue
                fahrenheitText.text = getString(R.string.fahrenheit_format, fahrenheitValue)

                if (fromUser && celsiusBar.progress <= 20) {
                    textIntersting.text = resources.getString(R.string.wishWarm)
                }
                else if (fromUser && celsiusBar.progress > 20) {
                    textIntersting.text = resources.getString(R.string.wishCold)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used in this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used in this example
            }
        })

        fahrenheitBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Ensure Fahrenheit SeekBar does not go below 32ºF
                if (progress < 32) {
                    fahrenheitBar.progress = 32
                } else {
                    // Update Fahrenheit text
                    fahrenheitText.text = getString(R.string.fahrenheit_format, progress)

                    // Calculate corresponding Celsius value
                    val celsiusValue = fahrenheitToCelsius(progress)
                    // Update Celsius SeekBar and text
                    celsiusBar.progress = celsiusValue
                    celsiusText.text = getString(R.string.celsius_format, celsiusValue)

                    if (fromUser && celsiusBar.progress <= 20) {
                        textIntersting.text = resources.getString(R.string.wishWarm)
                    }
                    else if (fromUser && celsiusBar.progress > 20) {
                        textIntersting.text = resources.getString(R.string.wishCold)
                    }

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used in this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used in this example
            }
        })







    }
}