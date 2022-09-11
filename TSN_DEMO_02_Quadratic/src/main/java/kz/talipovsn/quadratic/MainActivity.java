package kz.talipovsn.quadratic;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Локальные переменные для доступа к компонентам окна
    private EditText editText_a, editText_b, editText_c;
    private TextView textView_x1, textView_x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация переменных доступа к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_c = findViewById(R.id.editText_c);
        textView_x1 = findViewById(R.id.textView_x1);
        textView_x2 = findViewById(R.id.textView_x2);

        // Проверка на переворот экрана и восстановление нужных значений в компонентах
        if (savedInstanceState != null) {
            textView_x1.setText(savedInstanceState.getString("x1"));
            textView_x2.setText(savedInstanceState.getString("x2"));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Сохранение нужных нам значений компонент при перевороте экрана
        outState.putString("x1", textView_x1.getText().toString());
        outState.putString("x2", textView_x2.getText().toString());
    }

    // МЕТОД ДЛЯ КНОПКИ РАСЧЕТА
    @SuppressLint("DefaultLocale")
    public void onCalc(View v) {

        // Локальные переменные
        double a, b, c, d, x1, x2;

        try {
            // Считывание введенных входных значений в переменные
            a = Double.parseDouble(editText_a.getText().toString());
            b = Double.parseDouble(editText_b.getText().toString());
            c = Double.parseDouble(editText_c.getText().toString());
        } catch (Exception e) {
            // Выдача всплывающего сообщения на экран об ошибке
            Toast.makeText(MainActivity.this, "Неверные входные данные!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        d = (b * b) - 4 * a * c; // Расчет дискриминанта

        // Расчет значений x1 и x2
        try {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isInfinite(x1) || Double.isInfinite(x2)) {
                throw new Exception();
            }
            textView_x1.setText(String.format("x1 = %.3f", x1));
            textView_x2.setText(String.format("x2 = %.3f", x2));
        } catch (Exception e) {
            String err = "Нет решения!";
            textView_x1.setText(err);
            textView_x2.setText(err);
        }

    }

}
