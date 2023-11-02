package com.example.jokenpo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.ifmg.jokenpo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AppCompatActivity
    private var placarPlayer: Int = 0
    private var placarBot: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        listenerEvent()
    }

    private fun listenerEvent() {
        this.binding.scissorsButton.setOnClickListener(this)
        this.binding.stoneButton.setOnClickListener(this)
        this.binding.paperButton.setOnClickListener(this)
        this.binding.novoJogoBtn.setOnClickListener(this)
    }

    private fun prizeDrawBot(): String {
        val list = listOf("Tesoura", "Pedra", "Papel")
        return list.random()
    }

    private fun choosePlayer(gamerChoose: String) {
        val botChoose = prizeDrawBot()

        binding.choosePlayerTxt.text = gamerChoose
        binding.optionChooseBotTxt.text = botChoose

        val result = makeDecision(gamerChoose, botChoose)

        when(result){
            Result.BOT_WINNER -> placarBot++
            Result.PLAYER_WINNER -> placarPlayer++
            else -> {
            }
        }


        binding.resultPlayerTxt.text = placarPlayer.toString()
        binding.resultBotTxt.text = placarBot.toString()
    }

    private fun makeDecision(gamerChoose: String, botChoose: String): Result {
        return when {
            gamerChoose == botChoose -> Result.A_TIE
            (gamerChoose == "Tesoura" && botChoose == "Papel") ||
                    (gamerChoose == "Papel" && botChoose == "Pedra") ||
                    (gamerChoose == "Pedra" && botChoose == "Tesoura") -> Result.PLAYER_WINNER

            else -> Result.BOT_WINNER
        }
    }

    private fun newGame() {
        placarPlayer = 0
        placarBot = 0

        this.binding.resultPlayerTxt.text = "0"
        this.binding.resultBotTxt.text = "0"
        this.binding.choosePlayerTxt.text = "-"
        this.binding.optionChooseBotTxt.text = "-"
    }

    enum class Result {
        PLAYER_WINNER, BOT_WINNER, A_TIE
    }
}