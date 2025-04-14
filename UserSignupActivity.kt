import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserSignupActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var resetButton: Button
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_signup)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirmPassword)
        resetButton = findViewById(R.id.resetButton)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPrefs = getSharedPreferences("userDetails", MODE_PRIVATE)
                val editor = sharedPrefs.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                val intent = Intent(this, UserLoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        resetButton.setOnClickListener {
            emailEditText.text.clear()
            passwordEditText.text.clear()
            confirmPasswordEditText.text.clear()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
