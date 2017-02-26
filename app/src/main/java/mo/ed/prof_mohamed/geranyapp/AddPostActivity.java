package mo.ed.prof_mohamed.geranyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AddPostActivity extends AppCompatActivity {


    LinearLayout camera_Linear_posts_addpostactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        this.setTitle("Share to Grany");
        camera_Linear_posts_addpostactivity=(LinearLayout) findViewById(R.id.camera_Linear_posts_addpostactivity);
        camera_Linear_posts_addpostactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GalleryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Post_Btn_confirm:
                // post  - database saving ...
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}