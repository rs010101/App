//package com.example.quizapp
//
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.util.Log
//import android.view.View
//import android.widget.Button
//import android.widget.Toast
//import androidx.annotation.RequiresApi
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import com.example.quizapp.databinding.ActivityQuizBinding
//import com.example.quizapp.databinding.ScoreDialogBinding
//
//class QuizActivity : AppCompatActivity(), View.OnClickListener {
//
//    companion object {
//        var questionModelList : List<QuestionModel> = listOf()
//        var time: String = ""
//    }
//
//    lateinit var binding: ActivityQuizBinding
//    private var currentQuestionIndex= 0
//    private var selectedAnswer = ""
//    private var score = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityQuizBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.apply {
//           btn0.setOnClickListener(this@QuizActivity)
//            btn1.setOnClickListener(this@QuizActivity)
//            btn2.setOnClickListener(this@QuizActivity)
//            btn3.setOnClickListener(this@QuizActivity)
//            nextBtn.setOnClickListener(this@QuizActivity)
//        }
//        loadQuestions()
//        startTimer()
//    }
//
//    private fun startTimer() {
//        val totalTimeinMili = time.toInt() *60 *1000L
//        object : CountDownTimer(totalTimeinMili, 1000L) {
//            override fun onTick(millisUntilFinished: Long) {
//                val seconds = millisUntilFinished/1000
//                val min = seconds/60
//                val remainingSeconds = seconds %60
//                binding.timerIndicatorTextview.text = String.format("%02d: %02d", min, remainingSeconds)
//            }
//
//            override fun onFinish() {
//            }
//        }.start()
//    }
//    private fun loadQuestions() {
//        selectedAnswer = ""
//        if(currentQuestionIndex == questionModelList.size){
//            finishQuiz()
//            return
//        }
//        binding.apply {
//            questionIndicatorTextview.text = "Question ${currentQuestionIndex +1} / ${questionModelList.size} "
//            questionProgressIndicator.progress =
//                (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
//            questionTextview.text = questionModelList[currentQuestionIndex].question
//            btn0.text = questionModelList[currentQuestionIndex].options[0]
//            btn1.text = questionModelList[currentQuestionIndex].options[1]
//            btn2.text = questionModelList[currentQuestionIndex].options[2]
//            btn3.text = questionModelList[currentQuestionIndex].options[3]
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onClick(view: View?) {
//        binding.apply {
//            btn0.setBackgroundColor(getColor(R.color.white))
//            btn1.setBackgroundColor(getColor(R.color.white))
//            btn2.setBackgroundColor(getColor(R.color.white))
//            btn3.setBackgroundColor(getColor(R.color.white))
//        }
//       val clickedBtn = view as Button
//        if(clickedBtn.id == R.id.next_btn){
//            if(selectedAnswer.isEmpty()){
//                Toast.makeText(applicationContext, "Please select an answer to continue", Toast.LENGTH_SHORT).show()
//                return
//            }
//            if(selectedAnswer == questionModelList[currentQuestionIndex].correct) {
//                score++
//                Log.i("Score of Quiz: ", score.toString())
//            }
//            currentQuestionIndex++
//            loadQuestions()
//        }else{
//            selectedAnswer = clickedBtn.text.toString()
//            clickedBtn.setBackgroundColor(getColor(R.color.orange))
//        }
//    }
//    private fun finishQuiz(){
//        val totalQuestions = questionModelList.size
//        val percentage = ((score.toFloat() / totalQuestions.toFloat()) * 100).toInt()
//        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
//        dialogBinding.apply {
//            scoreProgressIndicator.progress = percentage
//            scoreProgressText.text = "$percentage %"
//            if(percentage>60){
//                scoreTitle.text = "Congrats! You have passed"
//                scoreTitle.setTextColor(Color.BLUE)
//            }else{
//                scoreTitle.text = "Oops! You have failed"
//                scoreTitle.setTextColor(Color.RED)
//            }
//            scoreSubtitle.text = "$score out of $totalQuestions are correct"
//            finishBtn.setOnClickListener {
//                finish()
//            }
//        }
//        AlertDialog.Builder(this)
//            .setView(dialogBinding.root)
//            .setCancelable(false)
//            .show()
//    }
//}

package com.example.quizapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityQuizBinding
import com.example.quizapp.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var questionModelList: List<QuestionModel> = listOf()
        var time: String = ""
    }

    private lateinit var binding: ActivityQuizBinding
    private var currentQuestionIndex = 0
    private var selectedAnswer = ""
    private var score = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupClickListeners()
        loadQuestions()
        startTimer()
    }

    private fun setupClickListeners() {
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
    }

    private fun startTimer() {
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d", minutes, remainingSeconds)
            }

            override fun onFinish() {
                finishQuiz()
            }
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadQuestions() {
        if (currentQuestionIndex == questionModelList.size) {
            finishQuiz()
            return
        }
        val currentQuestion = questionModelList[currentQuestionIndex]
        binding.apply {
            questionIndicatorTextview.text = "Question ${currentQuestionIndex + 1} / ${questionModelList.size}"
            questionProgressIndicator.progress = ((currentQuestionIndex.toFloat() / questionModelList.size.toFloat()) * 100).toInt()
            questionTextview.text = currentQuestion.question
            btn0.text = currentQuestion.options[0]
            btn1.text = currentQuestion.options[1]
            btn2.text = currentQuestion.options[2]
            btn3.text = currentQuestion.options[3]
            resetButtonColors()
        }
        selectedAnswer = ""
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View) {
        val clickedButton = view as Button
        if (clickedButton.id == R.id.next_btn) {
            handleNextButtonClick()
        } else {
            handleAnswerButtonClick(clickedButton)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun handleNextButtonClick() {
        if (selectedAnswer.isEmpty()) {
            Toast.makeText(this, "Please select an answer to continue", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedAnswer == questionModelList[currentQuestionIndex].correct) {
            score++
            Log.i("Quiz Score", score.toString())
        }
        currentQuestionIndex++
        loadQuestions()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun handleAnswerButtonClick(clickedButton: Button) {
        resetButtonColors()
        selectedAnswer = clickedButton.text.toString()
        clickedButton.setBackgroundColor(getColor(R.color.orange))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun resetButtonColors() {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.white))
            btn1.setBackgroundColor(getColor(R.color.white))
            btn2.setBackgroundColor(getColor(R.color.white))
            btn3.setBackgroundColor(getColor(R.color.white))
        }
    }

    private fun finishQuiz() {
        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat()) * 100).toInt()
        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage%"
            scoreTitle.text = if (percentage > 60) {
                scoreTitle.setTextColor(Color.BLUE)
                "Congrats! You have passed"
            } else {
                scoreTitle.setTextColor(Color.RED)
                "Oops! You have failed"
            }
            scoreSubtitle.text = "$score out of $totalQuestions are correct"
            finishBtn.setOnClickListener {
                finish()
            }
        }
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()
    }
}
