package nl.avans.informatica.bioscoopapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView qrImage;
    private File qrGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrGenerate = (File) QRCode.from("Hello World").file();
        qrImage = (ImageView) findViewById(R.id.qrCode);

        Picasso.with(this)
                .load(qrGenerate.toString())
                .into(qrImage);
    }
}
