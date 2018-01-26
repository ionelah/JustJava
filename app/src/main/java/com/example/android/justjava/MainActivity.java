/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
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
        quantity = quantity + 1;
        displayQuantity(quantity);
            }
    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
            }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String summery = createOrderSummary(5);
        displayMessage(summery);
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
     */
    private int calculatePrice(){
        int price = quantity * 5;
        return price;
    }
    /** present the order detail
     * @name is used to define the person who orderd the coffee
     * @priceMessage is used to define de return value of method
     */
    private String createOrderSummary(int price){
        String name ="Name: Kaptain Kunal";
        String priceMessage = name + "\nQuantity: " + quantity;
        int priceOfOrder = calculatePrice();
        priceMessage = priceMessage + "\nTotal: $" + priceOfOrder;
        priceMessage = priceMessage + "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given text on the screen
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        View textView = findViewById(R.id.title);
        textView.setVisibility(View.GONE);
    }
}
