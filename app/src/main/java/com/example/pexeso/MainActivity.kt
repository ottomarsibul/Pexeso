package com.example.pexeso

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.pexeso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 6x6 buttoms
    val buttons = listOf(
        R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6,
        R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12,
        R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18,
        R.id.button19, R.id.button20, R.id.button21, R.id.button22, R.id.button23, R.id.button24,
        R.id.button25, R.id.button26, R.id.button27, R.id.button28, R.id.button29, R.id.button30,
        R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36
    )

    // pictures
    val images = listOf(
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8,
        R.drawable.img9, R.drawable.img10, R.drawable.img11, R.drawable.img12,
        R.drawable.img13, R.drawable.img14, R.drawable.img15, R.drawable.img16,
        R.drawable.img17, R.drawable.img18
    )

    private lateinit var binding: ActivityMainBinding
    private var firstCard: ImageButton? = null
    private var secondCard: ImageButton? = null
    private var score = 0
    private var cardImages = mutableListOf<Int>() // pictures
    private var revealedCards = mutableSetOf<ImageButton>() // revealed carsa
    private var isFlippingAllowed = true

    // card background
    private val cardBack = android.R.color.white

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGame()
    }

    private fun setupGame() {
        cardImages = (images + images).toMutableList() // list where every image is 2x times
        cardImages.shuffle() // schuffling them

        // every buttoms has a card
        for ((index, buttonId) in buttons.withIndex()) {
            val button = findViewById<ImageButton>(buttonId)
            button.setBackgroundColor(ContextCompat.getColor(this, cardBack)) // white background
            button.layoutParams.width = 150 // buttoms width and height
            button.layoutParams.height = 150 // perfect for my phone
            button.tag = cardImages[index] // every buttom has a picture
            button.setOnClickListener {
                onCardClicked(button)
            }
        }
    }

    private fun onCardClicked(button: ImageButton) {
        if (!isFlippingAllowed || revealedCards.contains(button)) return
        //choosing cards is forbiddeng atm or already paired card

        // choosing card
        button.setImageResource(button.tag as Int)

        // if it is first
        if (firstCard == null) {
            firstCard = button
        } else if (secondCard == null && firstCard != button) {
            // second and not the same card
            secondCard = button
            checkForMatch()
        }
    }

    private fun checkForMatch() {
        isFlippingAllowed = false // stoping new choosings

        if (firstCard?.tag == secondCard?.tag) {
            // matcing cards -> they stay open
            revealedCards.add(firstCard!!)
            revealedCards.add(secondCard!!)
            score++
            Toast.makeText(this, "You found a pair!", Toast.LENGTH_SHORT).show()
            resetTurn()
        } else {
            // if not matching, urning back after 1 sec
            Handler(Looper.getMainLooper()).postDelayed({
                firstCard?.setBackgroundColor(ContextCompat.getColor(this, cardBack)) // backside back to white
                firstCard?.setImageDrawable(null) // no first side
                secondCard?.setBackgroundColor(ContextCompat.getColor(this, cardBack)) //same thing with second acrd
                secondCard?.setImageDrawable(null)
                resetTurn()
            }, 1000)
        }
    }

    private fun resetTurn() {
        // starting over again with cards choosing
        firstCard = null
        secondCard = null
        isFlippingAllowed = true

        // if all the fields all open then over
        if (revealedCards.size == buttons.size) {
            Toast.makeText(this, "You are WINNER!", Toast.LENGTH_LONG).show()
        }
    }
}
