package com.unfortunatefortunes.ui
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.unfortunatefortunes.R
import com.unfortunatefortunes.adapter.FortuneAdapter
import com.unfortunatefortunes.databinding.FragmentFortuneBinding
import com.unfortunatefortunes.ui.FortuneViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FortuneFragment : Fragment(), View.OnClickListener {
    //VARIABLES ========================================================================================
    //Main_Window Activity Instantiation
    var Main_Window: Window? = null


    //GUI Elements
    var fortuneText: TextView? = null
    var genderText: TextView? = null
    var ratingText: TextView? = null

    private val viewModel: FortuneViewModel by viewModels()

    // Binding object instance with access to the views in the game_fragment.xml layout
    private lateinit var binding: FragmentFortuneBinding
    private lateinit var adapter: FortuneAdapter
    //LIFECYCLE METHODS ================================================================================
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFortuneBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_fortune, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//adapter.getAllFortunes()

        //Find GUI Elements
        fortuneText = view.findViewById(R.id.item_fortune_fortuneText_text_view)
        genderText = view.findViewById(R.id.item_fortune_gender_text_view)
        ratingText = view.findViewById(R.id.item_fortune_rating_text_view)

        //Attaches onClickListener to Buttons
        view.findViewById<View>(R.id.btnGetOne).setOnClickListener(this)

    }


    //LISTENER METHODS =================================================================================
    override fun onClick(view: View) {
//        //Determines how to respond to the click
        when (view.id) {

            R.id.btnGetOne -> {
//            adapter.getAllFortunes()
                //Creates References To The User Input Boxes
                val fortuneText: TextView =
                    view.findViewById(R.id.item_fortune_fortuneText_text_view)
                val genderText: TextView = view.findViewById(R.id.item_fortune_gender_text_view)
                val ratingText: TextView = view.findViewById(R.id.item_fortune_rating_text_view)

            }
        }
    }
//            R.id.btnUpdateFall -> {
//
//                //Creates References To The User Input Boxes
//                val txtFallMonth: EditText = Main_Window.findViewById(R.id.fallFrostDateMonth)
//                val txtFallDay: EditText = Main_Window.findViewById(R.id.fallFrostDateDay)
//                val txtFallYear: EditText = Main_Window.findViewById(R.id.fallFrostDateYear)
//
//                //User Input Validation
//                val fallInputDate =
//                    txtFallMonth.text.toString() + "/" + txtFallDay.text.toString() + "/" + txtFallYear.text.toString()
//                Main_Window.dateFormat.setLenient(false)
//                try {
//                    val fallDate: Date = Main_Window.dateFormat.parse(fallInputDate)
//                    //Updates The Database With The User Inputted Fall Frost Date Values
//                    Main_Window.setFirstFallFrostDate(fallDate)
//                    txtFallMonth.setText("")
//                    txtFallDay.setText("")
//                    txtFallYear.setText("")
//                    txtFallMonth.requestFocus()
//                    makeToast("First Fall Frost Date Successfully Updated!")
//                } //If Date Is Invalid, Toast The User To Input A Valid Date
//                catch (e: ParseException) {
//                    makeToast("Please Enter A Valid Date")
//                    e.printStackTrace()
//                }
//                //Update the Fall Frost Date on GUI
//                FallFrostDate = Main_Window.getFirstFallFrostDate()
//                txtFallFrost!!.text = dateFormat.format(FallFrostDate)
//            }
//            R.id.btnResetDB -> {
//                openConfirmationDialog(Main_Window)
//            }
//            else -> {
//
//                //Toast Error Information
//                makeToast("[ERROR] Menu parameter passed was not found, returning to main menu...")
//                println("[ERROR] Menu parameter passed was not found, returning to main menu...\n")
//                Main_Window.changeFragment("MainMenu")
//            }
//        }
//    }

    //METHODS ==========================================================================================
    fun makeToast(Message: String?) {
        val toast = Toast.makeText(activity, Message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }



    companion object {
        fun hideKeyboardFrom(context: Context?, view: View) {
            val imm =
                context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}