package ua.com.delydreamer.cryptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editPass;

    CryptosJ crypt = new CryptosJ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editPass = (EditText) findViewById(R.id.editPass);
    }

    public void encryptClick(View view)
    {
        String text = editText.getText().toString();
        String pass = editPass.getText().toString();

        Log.d("delylog", "encrypt - text:"+text+"| pass:"+pass);

        editText.setText(crypt.encryptVigenere(text, pass, false));
    }

    public void decryptClick(View view)
    {
        String text = editText.getText().toString();
        String pass = editPass.getText().toString();

        Log.d("delylog", "decrypt - text:"+text+"| pass:"+pass);

        editText.setText(crypt.encryptVigenere(text, pass, true));
    }

    public void copyClick(View view)
    {
        String text = editText.getText().toString();

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", text);
        clipboard.setPrimaryClip(clip);

        Toast toast = Toast.makeText(getApplicationContext(),"Copied", Toast.LENGTH_SHORT);
        toast.show();
    }
}
