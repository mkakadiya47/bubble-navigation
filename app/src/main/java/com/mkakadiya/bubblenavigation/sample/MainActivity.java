package com.mkakadiya.bubblenavigation.sample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mkakadiya.bubblenavigation.BubbleNavigationItem;
import com.mkakadiya.bubblenavigation.BubbleNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BubbleNavigationView bubbleNavigation = findViewById(R.id.bubbleNavigation);

        // Add navigation items
        bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Home", android.R.drawable.ic_menu_view));
        bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Search", android.R.drawable.ic_menu_search));
        bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Favorites", android.R.drawable.ic_menu_more));
        bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Profile", android.R.drawable.ic_menu_myplaces));

        // Set navigation listener
        bubbleNavigation.setOnNavigationItemSelectedListener((position, item) -> {
            Toast.makeText(MainActivity.this, 
                "Selected: " + item.getTitle(), 
                Toast.LENGTH_SHORT).show();
        });
    }
}
