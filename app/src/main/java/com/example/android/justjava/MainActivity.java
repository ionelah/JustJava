/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
       if (quantity == 100) {
           // shows a message
           Toast.makeText(this, "You can order only 100 coffes", Toast.LENGTH_SHORT).show();
           // exit de method early
           return;
           }
        quantity = quantity + 1;
        displayQuantity(quantity);

            }
    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 1){
            Toast.makeText(this, "You can't order less than 1 coffe", Toast.LENGTH_SHORT).show();
            return;}
        quantity = quantity - 1;
        displayQuantity(quantity);
            }
    /**
     * This method is called when the order button is clicked.
     * hasWippedCream is used to find if the customer want's wipped cream at coffee
     * hasChocolate is used to find if the customer want's chocolate at coffee
     */
    public void submitOrder(View view) {
        //find the name of the customer
        EditText customer = (EditText) findViewById(R.id.name_of_customer);
        String nameOfCustomer = customer.getText().toString();
        //find if the customer want's whipped cream
        CheckBox wippedCreamBox = (CheckBox) findViewById(R.id.order_whipped_cream);
        boolean hasWippedCream = wippedCreamBox.isChecked();
        //find if the customer want's chocolate
        CheckBox chocolateBox = (CheckBox) findViewById(R.id.order_chocolate);
        boolean hasChocolate = chocolateBox.isChecked();
        int price = calculatePrice(hasWippedCream,hasChocolate);
        String summery = createOrderSummary(nameOfCustomer,price,hasWippedCream,hasChocolate);
        //create an intent to send the information about the order by email to kitchen
        //ACTION_SENDTO - is used for no attachment
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        // only email apps should handle this
        intent.setData(Uri.parse("mailto:"));
        //intent.EXTRA_SUBJECT - a string with the email subject
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + nameOfCustomer);
        intent.putExtra(Intent.EXTRA_TEXT, summery);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
       // displayMessage(summery);
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int no) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + no);
    }

    /**calculates the price of a order
     *returns a integer, the total price for the coffee ordered
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param addWippedCream is whether or not the user wants wipped cream topping
     */
    private int calculatePrice(boolean addWippedCream, boolean addChocolate){
        int basePrice = 5;
        if (addWippedCream) {
            basePrice = basePrice + 1;}
        if (addChocolate) {
            basePrice = basePrice +2;}
        return basePrice * quantity;
    }

    /** present the order detail
     * @name is used to define the person who orderd the coffee
     * @priceMessage is used to define de return value of method
     * if the customer order whipped cream the param addWippedCream will be true, if not false
     * if the customer order chocolate the param addChocolate will be true, if not false
     * price is used for the total price of the order
     */
    private String createOrderSummary(String name, int price, boolean addWippedCream, boolean addChocolate){
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage = priceMessage + "\n" + getString(R.string.Add_whipped_cream) + addWippedCream;
        priceMessage += "\n" + getString(R.string.Add_chololate) + addChocolate;
        priceMessage = priceMessage + "\n" + getString(R.string.Quantity) + quantity;
        priceMessage = priceMessage + "\n" + getString(R.string.Total) + price;
        priceMessage = priceMessage + "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displays the given text on the screen
     */
 //   private void displayMessage(String message) {
 //       TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
 //       orderSummaryTextView.setText(message);
  //        }
}
