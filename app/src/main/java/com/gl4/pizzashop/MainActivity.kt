package com.gl4.pizzashop

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var txtName: TextInputEditText
    private lateinit var txtPhone: TextInputEditText
    private lateinit var txtAddress: TextInputEditText
    private lateinit var pizzaType: AutoCompleteTextView
    private lateinit var orderButton: Button

    @SuppressLint("CutPasteId", "MissingInflatedId", "QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        val pizzaTypes = resources.getStringArray(R.array.pizzaTypes);
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, pizzaTypes);
        val dropdown = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        dropdown.setAdapter(arrayAdapter)
        txtName = findViewById(R.id.name);
        txtPhone = findViewById(R.id.phone);
        txtAddress = findViewById(R.id.address);
        pizzaType = findViewById(R.id.autoCompleteTextView);
        orderButton = findViewById(R.id.orderButton);

        orderButton.setOnClickListener {
            val toast =
                Toast.makeText(applicationContext, "Thanks for your order", Toast.LENGTH_SHORT);
            toast.show();

            val name = txtName.text.toString();
            val phone = txtPhone.text.toString();
            val address = txtAddress.text.toString();
            val pizza = pizzaType.text.toString();
            val order =
                "$name, your $pizza pizza will be delivered to $address we will call you on your phone number $phone";

            val intent = Intent(this@MainActivity, SplashScreen::class.java);
            intent.putExtra("order", order);
            startActivity(intent);

            val intentMail = Intent(Intent.ACTION_SEND);
            intentMail.data = Uri.parse("mailto:"); // only email apps should handle this
            intentMail.putExtra(Intent.EXTRA_EMAIL, "pizzashop@mydomain.tn");
            intentMail.putExtra(Intent.EXTRA_SUBJECT, order);
            if (intentMail.resolveActivity(packageManager) != null) {
                startActivity(intentMail);
            }
        }
    }
}