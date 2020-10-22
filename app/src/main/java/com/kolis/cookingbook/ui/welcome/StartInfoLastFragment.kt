package com.kolis.cookingbook.ui.welcome

import android.app.AlertDialog
import android.content.*
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.kolis.cookingbook.MainActivity
import com.kolis.cookingbook.R
import com.kolis.cookingbook.utils.AppPreffManager
import com.kolis.cookingbook.utils.Constants
import kotlinx.android.synthetic.main.info_fragment.*
import kotlinx.android.synthetic.main.login_stub.*

class StartInfoLastFragment : StartInfoFragment(), OnPasswordCheckObserver {
    //    var db: DressRepositoryImpl = DressRepositoryImpl()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title_info.visibility = View.INVISIBLE
        stub.inflate()
        if (Constants.IS_REGISTRATION_REQUIRED) {
            skip_button.visibility = View.GONE
        } else {
            skip_button.visibility = View.VISIBLE
            skip_button.setOnClickListener { v: View ->
                startActivity(Intent(v.context, MainActivity::class.java))
                requireActivity().finish()
            }
        }
        signIn.setOnClickListener { v: View? -> showLoginDialog() }
        signUp.setOnClickListener { v: View? -> showGenerateLoginDialog() }
    }

    private fun showGenerateLoginDialog() {
        val login: String = "PasswordGenerator.getRandomLogin()"
        val password: String = "PasswordGenerator.getRandomPassword()"
//        db.addProfile(login, password)
        val builder = AlertDialog.Builder(activity)
        val message = getString(R.string.login_password, login, password)
        builder.setTitle(getString(R.string.you_are_registered))
            .setMessage(message)
            .setPositiveButton(getString(R.string.copy_to_clipboard)) { dialog: DialogInterface, id: Int ->
                val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", message)
                clipboard.setPrimaryClip(clip)
                dialog.cancel()
            }
        builder.create().show()
    }

    private fun showLoginDialog() {
        val li = LayoutInflater.from(requireContext())
        val prompt: View = li.inflate(R.layout.login_dialog, null)
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setView(prompt)
        val user = prompt.findViewById<EditText>(R.id.login_name)
        val pass = prompt.findViewById<EditText>(R.id.login_password)
        alertDialogBuilder.setTitle(getString(R.string.enter_login))
        alertDialogBuilder.setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                val password = pass.text.toString()
                val username = user.text.toString()
                if (username.length < 1 || password.length < 1) {
                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_LONG).show()
                    showLoginDialog()
                } else {
                    onPasswordCorrect(username, password)
//                    db.isRightPassword(username, password, this@StartInfoLastFragment)
                }
            }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }
        alertDialogBuilder.show()
    }

    override fun onPasswordCorrect(login: String?, password: String?) {
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(AppPreffManager.IS_REGISTERED, true).apply()
        pref.edit().putString(AppPreffManager.USER_NAME_PREF, login).apply()
        pref.edit().putString(AppPreffManager.USER_PASSWORD_PREF, password).apply()
        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

    override fun onPasswordWrong() {
        Toast.makeText(context, R.string.password_is_wrong, Toast.LENGTH_LONG).show()
    }
}