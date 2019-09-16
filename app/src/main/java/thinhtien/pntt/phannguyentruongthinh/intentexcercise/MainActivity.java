package thinhtien.pntt.phannguyentruongthinh.intentexcercise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgOrigin, imgResult;
    int Request_code_image = 123;
    String tenHinhGoc = "";
    TextView txtPoint;
    int point = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        txtPoint.setText(point + "");

        String[] mangTen = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(mangTen));

        // trộn mảng : shuffle
        Collections.shuffle(arrayName);
        tenHinhGoc = arrayName.get(0);
        int idHinh = getResources().getIdentifier(arrayName.get(0),"drawable", getPackageName());

        imgOrigin.setImageResource(idHinh);

        imgResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
//                startActivity(intent);
                startActivityForResult(new Intent(MainActivity.this,ImageActivity.class),Request_code_image);
            }
        });
    }

    private void AnhXa() {
        imgOrigin = findViewById(R.id.imageViewOrigin);
        imgResult = findViewById(R.id.imageViewResult);
        txtPoint = findViewById(R.id.textViewPoint);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Request_code_image && resultCode == RESULT_OK && data != null){
            String tenHinhNhan = data.getStringExtra("tenhinhchon");
            int idHinhNhan = getResources().getIdentifier(tenHinhNhan,"drawable",getPackageName());
            imgResult.setImageResource(idHinhNhan);

            // so sanh theo ten hinh
            if (tenHinhGoc.equals(tenHinhNhan)){
                Toast.makeText(this, "Chính xác \n Bạn được cộng 10 điểm", Toast.LENGTH_SHORT).show();

                // cộng điểm
                point += 10;

                // đổi hình gốc
                new CountDownTimer(2000, 100) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        // trộn mảng : shuffle
                        Collections.shuffle(arrayName);
                        tenHinhGoc = arrayName.get(0);
                        int idHinh = getResources().getIdentifier(arrayName.get(0),"drawable", getPackageName());

                        imgOrigin.setImageResource(idHinh);
                    }
                }.start();
            } else{
                Toast.makeText(this, "Sai rồi! \n Bạn bị trừ 5 điểm", Toast.LENGTH_SHORT).show();

                // trừ điểm
                point -= 5;
            }
            txtPoint.setText(point + "");
        }

        // kiểm tra màn hình thứ 2 không chọn hình
        if (requestCode == Request_code_image && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Bạn chưa chọn hình, muốn xem lại à? \n Bị trừ 15 điểm ^^", Toast.LENGTH_SHORT).show();
            point -= 15;
            txtPoint.setText(point + "");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuReload){
            // trộn mảng : shuffle
            Collections.shuffle(arrayName);
            tenHinhGoc = arrayName.get(0);
            int idHinh = getResources().getIdentifier(arrayName.get(0),"drawable", getPackageName());

            imgOrigin.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }
}
