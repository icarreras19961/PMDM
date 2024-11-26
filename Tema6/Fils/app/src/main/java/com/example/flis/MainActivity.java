package com.example.flis;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private TextView output;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
    }
    public void calcularOperacio(View view) {
        int n = Integer.parseInt(input.getText().toString());
        output.append(n + "! = ");
        //MyThread thread = new MyThread(n);
        //thread.start();
        MyTask task = new MyTask();
        task.execute(n);
    }
    public int factorial(int n) {
        int res=1;
        for (int i=1; i<=n; i++){
            res*=i;
            SystemClock.sleep(1000);
        }
        return res;
    }

    class MyThread extends Thread {
        private int n, res;
        public MyThread(int n) {
            this.n = n;
        }
        @Override
        public void run() {
            res = factorial(n);
            output.append(res + "\n");
        }
    }

    class MyTask extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog progres;
        @Override protected void onPreExecute() {
            progres = new ProgressDialog(MainActivity.this);
            progres.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progres.setMessage("Calculant...");
            progres.setCancelable(true);
            progres.setOnCancelListener(new DialogInterface.OnCancelListener()
            {
                @Override public void onCancel(DialogInterface dialog) {
                    MyTask.this.cancel(true);
                }
            });
            progres.setMax(100);
            progres.setProgress(0);
            progres.show();
        }
        @Override protected Integer doInBackground(Integer... n) {
            int res = 1;
            for (int i = 1; i <= n[0] && !isCancelled(); i++){
                res *= i;
                SystemClock.sleep(1000);
                publishProgress(i*100 / n[0]);
            }
            return res;
        }
        @Override protected void onProgressUpdate(Integer... perc) {
            progres.setProgress(perc[0]);

        }
        @Override protected void onPostExecute(Integer res) {
            progres.dismiss();
            output.append(res + "\n");

        }
        @Override protected void onCancelled() {
            output.append("cancel·lat\n");
        }
    }
}
