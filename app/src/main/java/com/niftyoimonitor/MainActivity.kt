package com.niftyoimonitor

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var content: LinearLayout

    private val bg = Color.rgb(12, 18, 31)
    private val panel = Color.rgb(25, 35, 54)
    private val white = Color.rgb(238, 243, 250)
    private val muted = Color.rgb(157, 173, 194)
    private val cyan = Color.rgb(68, 205, 220)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showScreen("Home")
    }

    private fun text(
        value: String,
        size: Float,
        color: Int = white,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 8, 0, 8)
        }
    }

    private fun card(title: String, body: String) {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 14, 18, 14)
            setBackgroundColor(panel)
        }

        box.addView(text(title, 17f, cyan, true))
        box.addView(text(body, 14f, muted))

        val params = LinearLayout.LayoutParams(-1, -2)
        params.setMargins(0, 8, 0, 10)

        content.addView(box, params)
    }

    private fun showScreen(screen: String) {

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(18, 18, 18, 12)
        }

        root.addView(text("NIFTY OI MONITOR", 22f, white, true))
        root.addView(
            text("Early warning • Breakdown confirmation", 13f, muted)
        )

        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        listOf("Home", "OI Chain", "Alerts", "Settings").forEach { name ->
            val button = Button(this).apply {
                text = name
                textSize = 10f
                isAllCaps = false
                setOnClickListener { showScreen(name) }
            }

            nav.addView(
                button,
                LinearLayout.LayoutParams(0, -2, 1f)
            )
        }

        root.addView(nav)

        val scroll = ScrollView(this)

        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 10, 0, 10)
        }

        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        when (screen) {

            "Home" -> {
                content.addView(text("Dashboard", 20f, white, true))

                card(
                    "Market status",
                    "No live market feed connected yet. Live values will appear after the data source is configured."
                )

                card(
                    "EARLY WARNING",
                    "PE OI unwinding + rising PE premium near support. This is a watch signal, not a confirmed breakdown."
                )

                card(
                    "CONFIRMED BREAKDOWN",
                    "Requires a 5-minute NIFTY close below support with volume expansion. Retest confirmation can strengthen the signal."
                )

                card(
                    "Monitoring plan",
                    "Nearest weekly expiry • ATM ±10 strikes • 5-minute snapshots"
                )
            }

            "OI Chain" -> {
                content.addView(text("Option chain", 20f, white, true))

                card(
                    "Data connection pending",
                    "Strike-wise CE/PE OI, OI change, premium and volume will be displayed here when the broker feed is connected."
                )

                card(
                    "Calculation",
                    "5-minute OI change = current OI snapshot minus the previous 5-minute snapshot."
                )
            }

            "Alerts" -> {
                content.addView(text("Alert centre", 20f, white, true))

                card(
                    "Early warning",
                    "Not active — waiting for live option-chain data."
                )

                card(
                    "Breakdown confirmed",
                    "Not active — waiting for index candles and volume data."
                )
            }

            else -> {
                content.addView(text("Settings", 20f, white, true))

                card("Underlying", "NIFTY")
                card("Expiry", "Nearest weekly expiry (dynamic)")
                card("Strike range", "ATM −10 to ATM +10")
                card("Refresh interval", "5 minutes")

                card(
                    "Data security",
                    "Broker credentials and access tokens must stay on a secure backend, never inside the APK."
                )
            }
        }

        root.addView(
            text(
                "Prototype UI • Not connected to live data • No trading orders",
                11f,
                muted
            ).apply {
                gravity = Gravity.CENTER
            }
        )

        setContentView(root)
    }
}
