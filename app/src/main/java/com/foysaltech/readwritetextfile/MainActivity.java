package com.foysaltech.readwritetextfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnWriteSDFile, btnReadSDFile, btnClearScreen, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.txtData);
        editText.setHint("Enter some lines of data here...");

        btnWriteSDFile = findViewById(R.id.btnWriteSDFile);
        btnWriteSDFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // write on SD card file data in the text box
                try {
                    File filename;
                    String path = Environment.getExternalStorageDirectory().toString();

                    new File(path + "/folder/subfolder").mkdirs();
                    filename = new File(path + "/folder/subfolder/mysdfile.txt");
                    FileOutputStream fOut = new FileOutputStream(filename);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                    myOutWriter.append(editText.getText());
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getBaseContext(), "Done writing SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReadSDFile = findViewById(R.id.btnReadSDFile);
        btnReadSDFile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // write on SD card file data in the text box
                try {

                    File filename;
                    String path = Environment.getExternalStorageDirectory().toString();
                    new File(path + "/folder/subfolder").mkdirs();
                    filename = new File(path + "/folder/subfolder/mysdfile.txt");
                    FileInputStream fIn = new FileInputStream(filename);

                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    editText.setText(aBuffer);
                    myReader.close();
                    Toast.makeText(getBaseContext(), "Done reading SD 'mysdfile.txt'", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClearScreen = findViewById(R.id.btnClearScreen);
        btnClearScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editText.setText("");
            }
        });

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}