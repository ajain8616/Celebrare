package com.example.celebrate

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayDeque

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var inputEditText: EditText
    private lateinit var submitButton: ImageButton
    private lateinit var linearLayout: LinearLayout
    private lateinit var undoButton: ImageButton
    private lateinit var redoButton: ImageButton
    private lateinit var spinnersLinearLayout: LinearLayout
    private lateinit var spinnerFontText: Spinner
    private lateinit var spinnerColorText: Spinner
    private lateinit var spinnerSizeText: Spinner
    private val textStack = ArrayDeque<String>()
    private var currentTextIndex = -1
    private var isMovingTextView = false
    private var initialX = 0f
    private var initialY = 0f
    private var initialTouchX = 0f
    private var initialTouchY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findIdOfElements()
        setEventsHandle()
        setupSpinners()
        moveResultTextView()
    }


    private fun findIdOfElements() {
        resultTextView = findViewById(R.id.resultTextView)
        inputEditText = findViewById(R.id.inputEditText)
        submitButton = findViewById(R.id.submitButton)
        linearLayout = findViewById(R.id.linearLayout)
        undoButton = findViewById(R.id.undoButton)
        redoButton = findViewById(R.id.redoButton)
        spinnersLinearLayout = findViewById(R.id.spinnersLinearLayout)
        spinnerFontText = findViewById(R.id.spinnerFontText)
        spinnerColorText = findViewById(R.id.spinnerColorText)
        spinnerSizeText = findViewById(R.id.spinnerSizeText)
    }

    private fun setEventsHandle() {
        submitButton.setOnClickListener {
            val inputText = inputEditText.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show()
            } else {
                textStack.add(inputText)
                resultTextView.text = inputText
                submitButton.isEnabled = true
                undoButton.isEnabled = true
                redoButton.isEnabled = false
                currentTextIndex = textStack.size - 1
            }
        }

        undoButton.setOnClickListener {
            if (currentTextIndex > 0) {
                currentTextIndex--
                resultTextView.text = textStack[currentTextIndex]
                undoButton.isEnabled = currentTextIndex != 0
                redoButton.isEnabled = true
            }
        }

        redoButton.setOnClickListener {
            if (currentTextIndex < textStack.size - 1) {
                currentTextIndex++
                resultTextView.text = textStack[currentTextIndex]
                redoButton.isEnabled = currentTextIndex != textStack.size - 1
                undoButton.isEnabled = true
            }
        }

        spinnerFontText.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val fontArray = resources.getStringArray(R.array.font_array)
                val selectedFont = fontArray[position]
                when (selectedFont) {
                    "Choose Your Font Accordingly",
                    "Arial" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Times New Roman" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Verdana" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                    "Adobe Jenson" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
                    "Baskerville" -> resultTextView.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
                    "Bodoni" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Caslon" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC)
                    "Didot" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                    "Garamond" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                    "Georgia" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Palatino" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.ITALIC)
                    "Trajan" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.ITALIC)
                    "Avant Garde Gothic" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Calibri" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Century Gothic" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.ITALIC)
                    "Comic Sans" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.ITALIC)
                    "Franklin Gothic" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD_ITALIC)
                    "Futura" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                    "Gill Sans" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
                    "Helvetica" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Roboto" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Brush Script" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                    "Edwardian Script" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD_ITALIC)
                    "Freestyle Script" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Lucida Calligraphy" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
                    "Mistral" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                    "Segoe Script" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL)
                    "Vivaldi" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                    "Zapfino" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC)
                    "Marlett" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
                    "Symbol" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD)
                    "Webdings" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL)
                    "Wingdings" -> resultTextView.typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.NORMAL)
                    "Zapf Dingbats" -> resultTextView.typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultTextView.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
            }
        }

        spinnerColorText.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val colorArray = resources.getStringArray(R.array.color_array)
                val selectedColor = colorArray[position]
                when (selectedColor) {
                    "Choose Your Color Accordingly",
                    "Red" -> resultTextView.setTextColor(Color.RED)
                    "Blue" -> resultTextView.setTextColor(Color.BLUE)
                    "Green" -> resultTextView.setTextColor(Color.GREEN)
                    "Orange" -> resultTextView.setTextColor(Color.rgb(255, 165, 0))
                    "Yellow" -> resultTextView.setTextColor(Color.YELLOW)
                    "Purple" -> resultTextView.setTextColor(Color.rgb(128, 0, 128))
                    "Pink" -> resultTextView.setTextColor(Color.rgb(255, 192, 203))
                    "Brown" -> resultTextView.setTextColor(Color.rgb(165, 42, 42))
                    "Black" -> resultTextView.setTextColor(Color.BLACK)
                    "White" -> resultTextView.setTextColor(Color.WHITE)
                    "Gray" -> resultTextView.setTextColor(Color.GRAY)
                    "Silver" -> resultTextView.setTextColor(Color.rgb(192, 192, 192))
                    "Gold" -> resultTextView.setTextColor(Color.rgb(255, 215, 0))
                    "Copper" -> resultTextView.setTextColor(Color.rgb(184, 115, 51))
                    "Bronze" -> resultTextView.setTextColor(Color.rgb(205, 127, 50))
                    "Magenta" -> resultTextView.setTextColor(Color.MAGENTA)
                    "Cyan" -> resultTextView.setTextColor(Color.CYAN)
                    "Turquoise" -> resultTextView.setTextColor(Color.rgb(48, 213, 200))
                    "Navy" -> resultTextView.setTextColor(Color.rgb(0, 0, 128) )
                    "Maroon" -> resultTextView.setTextColor(Color.rgb(128, 0, 0))
                    "Olive" -> resultTextView.setTextColor(Color.rgb(128, 128, 0) )
                    "Teal" -> resultTextView.setTextColor(Color.rgb(0, 128, 128))
                    "Indigo" -> resultTextView.setTextColor(Color.rgb(75, 0, 130))
                    "Violet" -> resultTextView.setTextColor(Color.rgb(138, 43, 226))
                    "Lavender" -> resultTextView.setTextColor(Color.rgb(230, 230, 250))
                    "Fuchsia" -> resultTextView.setTextColor(Color.rgb(255, 0, 255))
                    "Crimson" -> resultTextView.setTextColor(Color.rgb(220, 20, 60))
                    "Scarlet" -> resultTextView.setTextColor(Color.rgb(255, 36, 0))
                    "Vermilion" -> resultTextView.setTextColor(Color.rgb(227, 66, 52))
                    "Saffron" -> resultTextView.setTextColor(Color.rgb(244, 200, 66))
                    "Amber" -> resultTextView.setTextColor(Color.rgb(255, 191, 0))
                    "Topaz" -> resultTextView.setTextColor(Color.rgb(17, 142, 163))
                    "Emerald" -> resultTextView.setTextColor(Color.rgb(0, 102, 51))
                    "Sapphire" -> resultTextView.setTextColor(Color.rgb(0, 80, 109))
                    "Ruby" -> resultTextView.setTextColor(Color.rgb(224, 17, 95))
                    "Amethyst" -> resultTextView.setTextColor(Color.rgb(153, 102, 204))
                    "Jade" -> resultTextView.setTextColor(Color.rgb(0, 168, 107))
                    "Onyx" -> resultTextView.setTextColor(Color.rgb(53, 56, 57))
                    "Opal" -> resultTextView.setTextColor(Color.rgb(214, 209, 199))
                    "Pearl" -> resultTextView.setTextColor(Color.rgb(234, 224, 200))
                    "Ivory" -> resultTextView.setTextColor(Color.rgb(255, 255, 240))
                    "Eggshell" -> resultTextView.setTextColor(Color.rgb(240, 234, 214))
                    "Cream" -> resultTextView.setTextColor(Color.rgb(255, 253, 208))
                    "Beige" -> resultTextView.setTextColor(Color.rgb(245, 245, 220))
                    "Khaki" -> resultTextView.setTextColor(Color.rgb(240, 230, 140))
                    "Tan" -> resultTextView.setTextColor(Color.rgb(210, 180, 140))
                    "Rust" -> resultTextView.setTextColor(Color.rgb(183, 65, 14))
                    "Terracotta" -> resultTextView.setTextColor(Color.rgb(226, 114, 91))
                    "Salmon" -> resultTextView.setTextColor(Color.rgb(250, 128, 114))
                    "Peach" -> resultTextView.setTextColor(Color.rgb(255, 229, 180))
                    "Apricot" -> resultTextView.setTextColor(Color.rgb(255, 215, 181))
                    "Mango" -> resultTextView.setTextColor(Color.rgb(255, 165, 0))
                    "Papaya" -> resultTextView.setTextColor(Color.rgb(255, 191, 120))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                   resultTextView.setTextColor(Color.BLACK)
            }
        }

        spinnerSizeText.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sizeArray = resources.getStringArray(R.array.size_array)
                val selectedSize = sizeArray[position].toFloat()
                resultTextView.textSize = selectedSize
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultTextView.textSize = 7f
            }
        }


        resultTextView.setOnClickListener {
           spinnersLinearLayout.visibility = View.VISIBLE
       }

    }
    private fun setupSpinners() {
        val fontAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.font_array,
            android.R.layout.simple_spinner_item
        )
        fontAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFontText.adapter = fontAdapter

        val colorAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.color_array,
            android.R.layout.simple_spinner_item
        )
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColorText.adapter = colorAdapter

        val sizeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.size_array,
            android.R.layout.simple_spinner_item
        )
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSizeText.adapter = sizeAdapter

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun moveResultTextView() {
        resultTextView.setOnLongClickListener {
            isMovingTextView = true
            true
        }

        resultTextView.setOnTouchListener { view, event ->
            if (isMovingTextView) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialX = view.x
                        initialY = view.y
                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val offsetX = event.rawX - initialTouchX
                        val offsetY = event.rawY - initialTouchY
                        view.x = initialX + offsetX
                        view.y = initialY + offsetY
                    }
                    MotionEvent.ACTION_UP -> {
                        isMovingTextView = false
                    }
                }
                true
            } else {
                false
            }
        }
    }

}

