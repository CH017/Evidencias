package com.example.proyecto;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private static final long OVERLAY_MS = 1000;

    private final int[] DOOR_BUTTON_IDS = new int[]{
            R.id.imageButton4,
            R.id.imageButton2,
            R.id.imageButton3,
            R.id.ImageButton
    };



    private final int[] NEW_IMAGES = new int[]{
            R.drawable.shinobu,
            R.drawable.akaza,
            R.drawable.giyu,
            R.drawable.inosuke
    };

    private final String[] NEW_TEXTS = new String[]{
            "Shinobu Kocho\n" +"\n"+
                    "Bio: Hashira del Insecto, de carácter amable y sonrisa tranquila; experta en química y medicina que usa venenos en combate.\n" +"\n"+
                    "Habilidades:\n" +
                    "• Técnicas del Insecto (ataques precisos y rápidos)\n" +
                    "• Uso de venenos y toxinas en la hoja\n" +
                    "• Alta agilidad y conocimientos médicos",

            "Akaza\n" +"\n"+
                    "Bio: Luchador demoníaco de élite (Luna Superior Tres), brutal y obsesionado con el combate; posee enorme fuerza y regeneración.\n" +"\n"+
                    "Habilidades:\n" +
                    "• Posesión de técnicas marciales demoníacas de gran potencia\n" +
                    "• Regeneración acelerada\n" +
                    "• Aumento de fuerza y durabilidad en combate",

            "Giyu Tomioka\n" +"\n"+
                    "Bio: Hashira del Agua, serio y reservado; un espadachín extremadamente eficiente, fiel a su deber de proteger a los demás.\n" +"\n"+
                    "Habilidades:\n" +
                    "• Técnicas de Respiración del Agua (ataques fluidos y poderosos)\n" +
                    "• Gran control y precisión con la espada\n" +
                    "• Resistencia y calma táctica en combate",

            "Inosuke Hashibira\n" +"\n"+
                    "Bio: Joven salvaje que pelea con una máscara de jabalí; impulsivo y competitivo, se basa en el instinto y la agresividad.\n" +"\n"+
                    "Habilidades:\n" +
                    "• Respiración de la Bestia (estilo agresivo y acrobático)\n" +
                    "• Sentidos y reflejos sobrehumanos\n" +
                    "• Uso de dos espadas y ataques impredecibles"
    };


    private ImageView castillo;
    private ImageButton[] doorButtons;
    private ImageButton btnReturn;
    private ImageView sideImage;
    private TextView textBox;
    private View whiteOverlay;

    private Drawable originalSideDrawable;
    private String originalLeftText;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        castillo = findViewById(R.id.Fondo);
        castillo.setBackgroundResource(R.drawable.castillo);


        sideImage = findViewById(R.id.sideImage);
        textBox = findViewById(R.id.textBox);
        whiteOverlay = findViewById(R.id.whiteOverlay);
        btnReturn = findViewById(R.id.btnReturn);


        originalSideDrawable = sideImage.getDrawable();
        originalLeftText = textBox.getText().toString();


        doorButtons = new ImageButton[DOOR_BUTTON_IDS.length];
        for (int i = 0; i < DOOR_BUTTON_IDS.length; i++) {
            final int index = i;
            ImageButton btn = findViewById(DOOR_BUTTON_IDS[i]);
            doorButtons[i] = btn;
            if (btn != null) {
                btn.setOnClickListener(v -> onDoorButtonClicked(index));
            }
        }


        btnReturn.setVisibility(View.INVISIBLE);
        btnReturn.setEnabled(false);


        btnReturn.setOnClickListener(v -> restoreOriginalState());


        textBox.setVisibility(View.INVISIBLE);
        sideImage.setVisibility(View.INVISIBLE);
        whiteOverlay.setVisibility(View.INVISIBLE);
        whiteOverlay.setClickable(false);
    }


    private void onDoorButtonClicked(int index) {

        whiteOverlay.setVisibility(View.VISIBLE);
        whiteOverlay.setClickable(true);


        setDoorButtonsVisibility(View.INVISIBLE);


        if (index >= 0 && index < NEW_IMAGES.length) {
            int imageRes = NEW_IMAGES[index];
            sideImage.setImageResource(imageRes);
            sideImage.setVisibility(View.VISIBLE);
        }
        if (index >= 0 && index < NEW_TEXTS.length) {
            textBox.setText(NEW_TEXTS[index]);
            textBox.setVisibility(View.VISIBLE);
        }


        btnReturn.setEnabled(true);
        btnReturn.setVisibility(View.VISIBLE);


        handler.postDelayed(() -> {
            whiteOverlay.setVisibility(View.INVISIBLE);
            whiteOverlay.setClickable(false);
        }, OVERLAY_MS);
    }

    private void setDoorButtonsVisibility(int visibility) {
        if (doorButtons == null) return;
        for (ImageButton b : doorButtons) {
            if (b != null) b.setVisibility(visibility);
        }
    }

    private void restoreOriginalState() {
        if (originalSideDrawable != null) {
            sideImage.setImageDrawable(originalSideDrawable);
        } else {
            sideImage.setVisibility(View.INVISIBLE);
        }
        textBox.setText(originalLeftText);

        setDoorButtonsVisibility(View.VISIBLE);


        btnReturn.setVisibility(View.INVISIBLE);
        btnReturn.setEnabled(false);


        whiteOverlay.setVisibility(View.INVISIBLE);
        whiteOverlay.setClickable(false);


        textBox.setVisibility(View.INVISIBLE);
        sideImage.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}