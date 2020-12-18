package com.bureauart;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.bureauart.model.Artist;
import com.bureauart.model.Picture;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillDB();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_artists, R.id.navigation_gallery, R.id.navigation_contacts)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        this.navController = navController;
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @SuppressLint("CheckResult")
    private void fillDB(){
        List<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture(
                "https://user-images.githubusercontent.com/66691210/102537429-99e24a80-40b3-11eb-8bf7-ac38175aad2f.png",
                "Натюрморт",
                "Маркова Анастасия",
                50,
                70,
                1500
        ));
        pictures.add(new Picture(
                "https://user-images.githubusercontent.com/66691210/102537427-9949b400-40b3-11eb-9808-7df5d80a4771.png",
                "Натроение",
                "Охрименко Владислава",
                50,
                70,
                2500
        ));
        pictures.add(new Picture(
                "https://user-images.githubusercontent.com/66691210/102530797-1e2fd000-40aa-11eb-9f8b-3eeade0cda58.png",
                "Настя",
                "Охрименко Владислава",
                50,
                70,
                2500
        ));
        pictures.add(new Picture(
                "https://user-images.githubusercontent.com/66691210/102530784-1c660c80-40aa-11eb-9cd6-0479821df4d1.png",
                "Васильки",
                "Маркова Анастасия",
                50,
                70,
                2000
        ));
        pictures.add(new Picture(
                "https://user-images.githubusercontent.com/66691210/102530788-1d973980-40aa-11eb-8059-bfaca62e07f6.png",
                "Моя Абстракция",
                "Кононенко Полина",
                50,
                70,
                2500
        ));
        pictures.add(new Picture(
                "https://user-images.githubusercontent.com/66691210/102530789-1d973980-40aa-11eb-8f50-349fe3501428.png",
                "Пейзаж",
                "Кононенко Полина",
                50,
                70,
                2500
        ));

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(
                "https://user-images.githubusercontent.com/66691210/102530940-4dded800-40aa-11eb-8d20-190269b6c803.png",
                "Маркова Анастасия",
                "Привет! Меня зовут Настя и " +
                        "я начинающий художник. " +
                        "Сейчас я учусь в университете на специальности " +
                        "Информационные технологии проектирования " +
                        "и параллельно работаю в компании Неткрекер. " +
                        "В свободной время я рисую."
        ));
        artists.add(new Artist(
                "https://user-images.githubusercontent.com/66691210/102530938-4d464180-40aa-11eb-8980-b91fa1198727.png",
                "Кононенко Полина",
                "Хола! Меня зовут Полина и я люблю в свободное время рисовать. В основном, я рисую маслом и акрилом."
        ));
        artists.add(new Artist(
                "https://user-images.githubusercontent.com/66691210/102530942-4dded800-40aa-11eb-9008-0f7b1f16d9e3.png",
                "Охрименко Владислава",
                "Привет!!!. Еще с детства у меня была большая любовь к рисованию, поэтому мама меня отдала в художественную школу еще в начальной школе." +
                        "Сейчас мне уже 21 и любовь к рисованию так же сильна, как когда мне было 7."
        ));

        Completable.fromAction(() -> {
            App.getInstance().getDatabase().clearAllTables();

            App.getInstance().getDatabase().artistDao().insertAllArtists(artists);
            App.getInstance().getDatabase().pictureDao().insertAllPictures(pictures);

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {

                        },
                        error -> {

                        }
                );
    }

}