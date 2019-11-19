package com.tietha.thebestteamofcreatingrobots;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Info extends AppCompatActivity {
    ViewFlipper viewFlipper;
    ViewFlipper viewFlipper2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        textView = findViewById(R.id.infomation);
        viewFlipper = findViewById(R.id.simpleViewFlipper1);
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        viewFlipper.setInAnimation(in);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();


        viewFlipper2 = findViewById(R.id.simpleViewFlipper2);
        viewFlipper2.setInAnimation(out);
        viewFlipper2.setOutAnimation(in);
        viewFlipper2.setFlipInterval(3000);
        viewFlipper2.startFlipping();

        String text = "<h1>The Best Team Creating Robots</h1>";
        text += "<h2>Commands/characters sent to the car</h2>";
        text += " - Forward -> F<br>";
        text += " - Back -> B<br>";
        text += " - Left -> L<br>";
        text += " - Right -> R<br>";
        text += " - Forward Left -> G<br>";
        text += " - Forward Right -> I<br>";
        text += " - Back Left -> F<br>";
        text += " - Back Right -> J<br>";
        text += " - Stop -> S<br>";
        text += " - Speed 0 -> 0<br>";
        text += " - Speed 10 -> 1<br>";
        text += " - Speed 20 -> 2<br>";
        text += " - Speed 30 -> 3<br>";
        text += " - Speed 40 -> 4<br>";
        text += " - Speed 50 -> 5<br>";
        text += " - Speed 60 -> 6<br>";
        text += " - Speed 70 -> 7<br>";
        text += " - Speed 80 -> 8<br>";
        text += " - Speed 90 -> 9<br>";
        text += " - Speed 100 -> q<br>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
        } else {
            textView.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
        }
    }
}
