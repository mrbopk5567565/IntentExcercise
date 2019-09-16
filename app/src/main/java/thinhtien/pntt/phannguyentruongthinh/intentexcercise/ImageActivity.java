package thinhtien.pntt.phannguyentruongthinh.intentexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class ImageActivity extends Activity {

    TableLayout myTablel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        myTablel = findViewById(R.id.tableLayoutImgage);

        int soDong = 6;
        int soCot = 3;

        // truoc khi chay for shuffle mang
        Collections.shuffle(MainActivity.arrayName);

        // tạo dòng với cột
        for (int i = 1;i <= soDong;i++){
            TableRow tableRow = new TableRow(this);
            for (int j = 1; j <= soCot;j++){
                ImageView imageView =  new ImageView(this);

                // Converts 14 dip into its equivalent px
                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100f,r.getDisplayMetrics());


                // LayoutParams : lấy theo dòng
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);


                final int vitri = soCot * (i - 1) + j - 1;
                int idHinh = getResources().getIdentifier(MainActivity.arrayName.get(vitri),
                        "drawable",getPackageName());
                imageView.setImageResource(idHinh);

                // add imageview vao tablerow
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra("tenhinhchon",MainActivity.arrayName.get(vitri));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            // add tablerow vao table
            myTablel.addView(tableRow);
        }
    }
}
