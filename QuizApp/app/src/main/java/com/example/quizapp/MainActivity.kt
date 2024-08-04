//package com.example.quizapp
//
//import QuizListAdapter
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.quizapp.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//     private lateinit var binding: ActivityMainBinding
//    private lateinit var quizModelList: MutableList<QuizModel>
//     private lateinit var adapter: QuizListAdapter
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        quizModelList = mutableListOf()
//        getDatafromFirebase()
//    }
//
//    private fun setUpRecyclerView() {
//        adapter = QuizListAdapter(quizModelList)
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = adapter
//    }
//
//    private fun getDatafromFirebase(){
//        val listQuestionModel = mutableListOf<QuestionModel>()
//        listQuestionModel.add(QuestionModel("What is Android", mutableListOf("Language", "OS", "Product", "None"), "OS"))
//        listQuestionModel.add(QuestionModel("Who owns Android", mutableListOf("Apple", "Google", "Microsoft", "Netflix"), "Google"))
//        listQuestionModel.add(QuestionModel("What is the default programming language for Android development?", mutableListOf("Java", "Kotlin", "Python", "C++"), "Kotlin"))
//        listQuestionModel.add(QuestionModel("Which file is the entry point for an Android application?", mutableListOf("MainActivity.kt", "AndroidManifest.xml", "build.gradle", "activity_main.xml"), "AndroidManifest.xml"))
//        listQuestionModel.add(QuestionModel("What is an APK?", mutableListOf("Android Package", "Android Phone Kit", "Android Programming Kit", "None"), "Android Package"))
//        listQuestionModel.add(QuestionModel("Which method is used to start an activity in Android?", mutableListOf("startActivity", "beginActivity", "initiateActivity", "launchActivity"), "startActivity"))
//        listQuestionModel.add(QuestionModel("What is the name of the UI toolkit used in Android?", mutableListOf("UIKit", "Jetpack", "SwiftUI", "None"), "Jetpack"))
//        listQuestionModel.add(QuestionModel("Which component is not part of the Android application architecture?", mutableListOf("Services", "Intents", "Fragments", "Middleware"), "Middleware"))
//        listQuestionModel.add(QuestionModel("What does ADB stand for?", mutableListOf("Android Debug Bridge", "Android Data Bridge", "Application Debug Bridge", "Android Development Bridge"), "Android Debug Bridge"))
//        listQuestionModel.add(QuestionModel("Which lifecycle method is called when an activity becomes visible to the user?", mutableListOf("onCreate", "onStart", "onResume", "onPause"), "onStart"))
//        listQuestionModel.add(QuestionModel("What is the purpose of the RecyclerView in Android?", mutableListOf("Display a list of items", "Handle navigation", "Manage background tasks", "None"), "Display a list of items"))
//        listQuestionModel.add(QuestionModel("Which file do you use to define the layout of an Android activity?", mutableListOf("layout.xml", "activity.xml", "content.xml", "activity_main.xml"), "activity_main.xml"))
//
//
//        val listQuestionModel1 = mutableListOf<QuestionModel>()
//        listQuestionModel1.add(QuestionModel("What does CPU stand for in computer terminology?", mutableListOf("Central Processing Unit", "Central Program Unit", "Central Power Unit", "Central Protocol Unit"), "Central Processing Unit"))
//        listQuestionModel1.add(QuestionModel("Which of the following is a type of volatile memory?", mutableListOf("Hard Disk Drive", "Solid State Drive", "RAM (Random Access Memory)", "ROM (Read-Only Memory)"), "RAM (Random Access Memory)"))
//        listQuestionModel1.add(QuestionModel("What is the main function of an operating system?", mutableListOf("Manage hardware resources", "Compile code", "Run applications", "Store data"), "Manage hardware resources"))
//        listQuestionModel1.add(QuestionModel("Which language is known as the 'mother of all languages' in computer programming?", mutableListOf("C", "Assembly", "Fortran", "Pascal"), "Assembly"))
//        listQuestionModel1.add(QuestionModel("What does IDE stand for?", mutableListOf("Integrated Development Environment", "Internet Development Environment", "Internal Development Engine", "Interactive Development Environment"), "Integrated Development Environment"))
//        listQuestionModel1.add(QuestionModel("Which programming language is primarily used for web development?", mutableListOf("Python", "Java", "HTML", "C++"), "HTML"))
//        listQuestionModel1.add(QuestionModel("What does the 'if' statement in programming do?", mutableListOf("Repeat a block of code", "Check a condition and execute a block of code", "Declare a variable", "Define a function"), "Check a condition and execute a block of code"))
//        listQuestionModel1.add(QuestionModel("What is the purpose of a loop in programming?", mutableListOf("Execute a block of code multiple times", "Store data", "Define a function", "Terminate a program"), "Execute a block of code multiple times"))
//        listQuestionModel1.add(QuestionModel("Which of the following is a type of loop?", mutableListOf("for", "while", "do-while", "All of the above"), "All of the above"))
//        listQuestionModel1.add(QuestionModel("What does 'OOP' stand for?", mutableListOf("Object-Oriented Programming", "Object Operating Protocol", "Open Office Protocol", "Online Operating Program"), "Object-Oriented Programming"))
//        listQuestionModel1.add(QuestionModel("Which symbol is used to comment a single line in Python?", mutableListOf("#", "//", "/*", "'"), "#"))
//        listQuestionModel1.add(QuestionModel("What is the output of 2 + 2 in most programming languages?", mutableListOf("4", "22", "Error", "None of the above"), "4"))
//
//
//        quizModelList.add(QuizModel("1", "Android", "All about Android", "10", listQuestionModel))
//        quizModelList.add(QuizModel("2", "Computer", "All about Computer ", "15",listQuestionModel1))
//
//                setUpRecyclerView()
//            }
//    }
//
package com.example.quizapp

import QuizListAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quizModelList: MutableList<QuizModel>
    private lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        fetchQuizData()
    }

    private fun setupRecyclerView() {
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchQuizData() {
        val androidQuestions = mutableListOf<QuestionModel>().apply {
            add(QuestionModel("What is Android?", listOf("Language", "OS", "Product", "None"), "OS"))
            add(QuestionModel("Who owns Android?", listOf("Apple", "Google", "Microsoft", "Netflix"), "Google"))
            add(QuestionModel("What is the default programming language for Android development?", listOf("Java", "Kotlin", "Python", "C++"), "Kotlin"))
            add(QuestionModel("Which file is the entry point for an Android application?", listOf("MainActivity.kt", "AndroidManifest.xml", "build.gradle", "activity_main.xml"), "AndroidManifest.xml"))
            add(QuestionModel("What is an APK?", listOf("Android Package", "Android Phone Kit", "Android Programming Kit", "None"), "Android Package"))
            add(QuestionModel("Which method is used to start an activity in Android?", listOf("startActivity", "beginActivity", "initiateActivity", "launchActivity"), "startActivity"))
            add(QuestionModel("What is the name of the UI toolkit used in Android?", listOf("UIKit", "Jetpack", "SwiftUI", "None"), "Jetpack"))
            add(QuestionModel("Which component is not part of the Android application architecture?", listOf("Services", "Intents", "Fragments", "Middleware"), "Middleware"))
            add(QuestionModel("What does ADB stand for?", listOf("Android Debug Bridge", "Android Data Bridge", "Application Debug Bridge", "Android Development Bridge"), "Android Debug Bridge"))
            add(QuestionModel("Which lifecycle method is called when an activity becomes visible to the user?", listOf("onCreate", "onStart", "onResume", "onPause"), "onStart"))
            add(QuestionModel("What is the purpose of the RecyclerView in Android?", listOf("Display a list of items", "Handle navigation", "Manage background tasks", "None"), "Display a list of items"))
            add(QuestionModel("Which file do you use to define the layout of an Android activity?", listOf("layout.xml", "activity.xml", "content.xml", "activity_main.xml"), "activity_main.xml"))
        }

        val computerQuestions = mutableListOf<QuestionModel>().apply {
            add(QuestionModel("What does CPU stand for in computer terminology?", listOf("Central Processing Unit", "Central Program Unit", "Central Power Unit", "Central Protocol Unit"), "Central Processing Unit"))
            add(QuestionModel("Which of the following is a type of volatile memory?", listOf("Hard Disk Drive", "Solid State Drive", "RAM (Random Access Memory)", "ROM (Read-Only Memory)"), "RAM (Random Access Memory)"))
            add(QuestionModel("What is the main function of an operating system?", listOf("Manage hardware resources", "Compile code", "Run applications", "Store data"), "Manage hardware resources"))
            add(QuestionModel("Which language is known as the 'mother of all languages' in computer programming?", listOf("C", "Assembly", "Fortran", "Pascal"), "Assembly"))
            add(QuestionModel("What does IDE stand for?", listOf("Integrated Development Environment", "Internet Development Environment", "Internal Development Engine", "Interactive Development Environment"), "Integrated Development Environment"))
            add(QuestionModel("Which programming language is primarily used for web development?", listOf("Python", "Java", "HTML", "C++"), "HTML"))
            add(QuestionModel("What does the 'if' statement in programming do?", listOf("Repeat a block of code", "Check a condition and execute a block of code", "Declare a variable", "Define a function"), "Check a condition and execute a block of code"))
            add(QuestionModel("What is the purpose of a loop in programming?", listOf("Execute a block of code multiple times", "Store data", "Define a function", "Terminate a program"), "Execute a block of code multiple times"))
            add(QuestionModel("Which of the following is a type of loop?", listOf("for", "while", "do-while", "All of the above"), "All of the above"))
            add(QuestionModel("What does 'OOP' stand for?", listOf("Object-Oriented Programming", "Object Operating Protocol", "Open Office Protocol", "Online Operating Program"), "Object-Oriented Programming"))
            add(QuestionModel("Which symbol is used to comment a single line in Python?", listOf("#", "//", "/*", "'"), "#"))
            add(QuestionModel("What is the output of 2 + 2 in most programming languages?", listOf("4", "22", "Error", "None of the above"), "4"))
        }

        quizModelList.apply {
            add(QuizModel("1", "Android", "All about Android", "10", androidQuestions))
            add(QuizModel("2", "Computer", "All about Computers", "15", computerQuestions))
        }

        setupRecyclerView()
    }
}

