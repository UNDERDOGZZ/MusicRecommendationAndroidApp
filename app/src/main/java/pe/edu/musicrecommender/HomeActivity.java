package pe.edu.musicrecommender;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.paperdb.Paper;
import pe.edu.musicrecommender.Prevalent.Prevalent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SongsAdapter.ClickedItem {

    CoordinatorLayout coordinatorLayout;
    private AppBarConfiguration mAppBarConfiguration;
    RecyclerView recyclerView;
    SongsAdapter songsAdapter;
    SongsGenreAdapter songsGenreAdapter;
    EmotionService emotionService;
    GenreService genreService;
    int years = 0;
    int count = 0;
    int kinki=0;
    int love=0;
    int success=0;
    int hard=0;
    int need=0;
    int feel=0;
    int day=0;
    int neww=0;
    int gone=0;
    int heart=0;
    int boy=0;
    int realli=0;
    int real=0;
    int think=0;
    int would=0;
    int thing=0;
    int one=0;
    int hold=0;
    int sdanger=0;
    int bitch=0;
    int leav=0;
    int danger=0;
    int around=0;
    int talk=0;
    int run=0;
    int keep=0;
    int nigga=0;
    int live=0;
    int sweet=0;
    int world=0;
    int alway=0;
    int successt=0;
    int eye=0;
    int parti=0;
    int wait=0;
    int bodi=0;
    int made=0;
    int noth=0;
    int show=0;
    int bad=0;
    int turn=0;
    int longg=0;
    int home=0;
    int light=0;
    int hand=0;
    int work=0;
    int nigparti=0;
    int caheart=0;
    int walk=0;

    float energy= 0;
    float liveness = 0;
    float tempo = 0;
    float speechiness = 0;
    float acousticness = 0;
    int yeargenre = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_menu);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        songsAdapter = new SongsAdapter(this::ClickedSong);
        songsGenreAdapter = new SongsGenreAdapter(this::ClickedSong);





        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Paper.init(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Inicio");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        userNameTextView.setText(Prevalent.currentOnlineUser.getName());


        //Toast.makeText(getApplicationContext(),Prevalent.currentOnlineUser.getYears().toString() , Toast.LENGTH_LONG).show();
        String aux = Prevalent.currentOnlineUser.getYears().toString();
        try {
            years = Integer.parseInt(aux);
        }
        catch (NumberFormatException e)
        {
            years = 0;
        }



        if (years>1) {

            getAllSongs();
            Toast.makeText(getApplicationContext(), "largoplazo", Toast.LENGTH_LONG).show();

        }
        else {
            getAllSongsGenre();
            Toast.makeText(getApplicationContext(), "cortoplazo", Toast.LENGTH_LONG).show();
        }

    }

    private void getAllSongsGenre() {
        Call<List<SongsGenreResponse>> songlist = ApiClient.getSongsGenreService().getAllSongsGenre();

        songlist.enqueue(new Callback<List<SongsGenreResponse>>() {
            @Override
            public void onResponse(Call<List<SongsGenreResponse>> call, Response<List<SongsGenreResponse>> response) {

                if(response.isSuccessful()){
                    List<SongsGenreResponse> songsGenreResponses = response.body();
                    songsGenreAdapter.setData(songsGenreResponses);
                    recyclerView.setAdapter(songsGenreAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<SongsGenreResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });

    }

    private void ClickedSong(SongsGenreResponse songsGenreResponse) {
        count +=1;
        energy+=songsGenreResponse.getEnergy();
        liveness+=songsGenreResponse.getLiveness();
        tempo+=songsGenreResponse.getTempo();
        speechiness+=songsGenreResponse.getSpeechiness();
        acousticness+=songsGenreResponse.getAcousticness();
        int aux = songsGenreResponse.getDate().length();
        char first = songsGenreResponse.getDate().charAt(aux-2);
        char second = songsGenreResponse.getDate().charAt(aux-1);
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        sb.append(second);
        String str = sb.toString();
        int auxyear;
        try {
            auxyear = Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            auxyear = 15;
        }
        yeargenre+=auxyear;
        if (count ==5){

            energy=energy/5;
            liveness=liveness/5;
            tempo=tempo/5;
            speechiness=speechiness/5;
            acousticness=acousticness/5;
            yeargenre=yeargenre/5;

            GenreSent genreSent = new GenreSent();
            genreSent.setYear(yeargenre);
            genreSent.setEnergy(energy);
            genreSent.setLiveness(liveness);
            genreSent.setTempo(tempo);
            genreSent.setSpeechiness(speechiness);
            genreSent.setAcousticness(acousticness);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:4000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            genreService = retrofit.create(GenreService.class);
            Call<GenreResponse> call = genreService.sendPost(genreSent);
            call.enqueue(new Callback<GenreResponse>() {
                @Override
                public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                    //Log.i("response",response.body().getTitle());
                    Snackbar snackbar=Snackbar.make(findViewById(R.id.rootlayout),"Te recomendamos escuchar "+response.body().getTitle(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                @Override
                public void onFailure(Call<GenreResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }

            });


            //Toast.makeText(getApplicationContext(), String.valueOf(yeargenre), Toast.LENGTH_LONG).show();

            count = 0;
            energy=0;
            liveness=0;
            tempo=0;
            speechiness=0;
            acousticness=0;
            yeargenre=0;
        }

    }

    public void getAllSongs(){

        Call<List<SongResponse>> songlist = ApiClient.getSongService().getAllSongs();

        songlist.enqueue(new Callback<List<SongResponse>>() {
            @Override
            public void onResponse(Call<List<SongResponse>> call, Response<List<SongResponse>> response) {

                if(response.isSuccessful()){
                    List<SongResponse> songResponses = response.body();
                    songsAdapter.setData(songResponses);
                    recyclerView.setAdapter(songsAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<SongResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_logout) {
            Paper.book().destroy();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void ClickedSong(SongResponse songResponse) {
        count +=1;
        kinki += songResponse.getKinki();
        love += songResponse.getLove();
        success += songResponse.getSuccess();
        hard += songResponse.getHard();
        need += songResponse.getNeed();
        feel += songResponse.getFeel();
        day += songResponse.getDay();
        neww += songResponse.getNeww();
        gone += songResponse.getGone();
        heart += songResponse.getHeart();
        boy += songResponse.getBoy();
        realli += songResponse.getRealli();
        real += songResponse.getReal();
        think += songResponse.getThink();
        would += songResponse.getWould();
        thing += songResponse.getThing();
        one += songResponse.getOne();
        hold += songResponse.getHold();
        sdanger += songResponse.getSdanger();
        bitch += songResponse.getBitch();
        leav += songResponse.getLeav();
        danger += songResponse.getDanger();
        around += songResponse.getAround();
        talk += songResponse.getTalk();
        run += songResponse.getRun();
        keep += songResponse.getKeep();
        nigga += songResponse.getNigga();
        live += songResponse.getLive();
        sweet += songResponse.getSweet();
        world += songResponse.getWorld();
        alway += songResponse.getAlway();
        successt += songResponse.getSuccesst();
        eye += songResponse.getEye();
        parti += songResponse.getParti();
        wait += songResponse.getWait();
        bodi += songResponse.getBodi();
        made += songResponse.getMade();
        noth += songResponse.getNoth();
        show += songResponse.getShow();
        bad += songResponse.getBad();
        turn += songResponse.getTurn();
        longg += songResponse.getLongg();
        home += songResponse.getHome();
        light += songResponse.getLight();
        hand += songResponse.getHand();
        work += songResponse.getWork();
        nigparti += songResponse.getNigparti();
        caheart += songResponse.getCaheart();
        walk += songResponse.getWalk();


        if (count == 5){

            kinki=kinki/5;
            love=love/5;
            success=success/5;
            hard= hard/5;
            need= need/5;
            feel=feel/5;
            day=day/5;
            neww=neww/5;
            gone=gone/5;
            heart=heart/5;
            boy=boy/5;
            realli=realli/5;
            real=real/5;
            think=think/5;
            would=would/5;
            thing=thing/5;
            one=one/5;
            hold=hold/5;
            sdanger=sdanger/5;
            bitch=bitch/5;
            leav=leav/5;
            danger=danger/5;
            around=around/5;
            talk=talk/5;
            run=run/5;
            keep=keep/5;
            nigga=nigga/5;
            live=live/5;
            sweet=sweet/5;
            world=world/5;
            alway=alway/5;
            successt=successt/5;
            eye=eye/5;
            parti=parti/5;
            wait=wait/5;
            bodi=bodi/5;
            made=made/5;
            noth=noth/5;
            show=show/5;
            bad=bad/5;
            turn=turn/5;
            longg=longg/5;
            home=home/5;
            light=light/5;
            hand=hand/5;
            work=work/5;
            nigparti=nigparti/5;
            caheart=caheart/5;
            walk=walk/5;

            EmotionSent emotionSent = new EmotionSent();
            emotionSent.setKinki(kinki);
            emotionSent.setLove(love);
            emotionSent.setSuccess(success);
            emotionSent.setHard(hard);
            emotionSent.setNeed(need);
            emotionSent.setFeel(feel);
            emotionSent.setDay(day);
            emotionSent.setNeww(neww);
            emotionSent.setGone(gone);
            emotionSent.setHeart(heart);
            emotionSent.setBoy(boy);
            emotionSent.setRealli(realli);
            emotionSent.setReal(real);
            emotionSent.setThink(think);
            emotionSent.setWould(would);
            emotionSent.setThing(thing);
            emotionSent.setOne(one);
            emotionSent.setHold(hold);
            emotionSent.setSdanger(sdanger);
            emotionSent.setBitch(bitch);
            emotionSent.setLeav(leav);
            emotionSent.setDanger(danger);
            emotionSent.setAround(around);
            emotionSent.setTalk(talk);
            emotionSent.setRun(run);
            emotionSent.setKeep(keep);
            emotionSent.setNigga(nigga);
            emotionSent.setLive(live);
            emotionSent.setSweet(sweet);
            emotionSent.setWorld(world);
            emotionSent.setAlway(alway);
            emotionSent.setSuccesst(successt);
            emotionSent.setEye(eye);
            emotionSent.setParti(parti);
            emotionSent.setWait(wait);
            emotionSent.setBodi(bodi);
            emotionSent.setMade(made);
            emotionSent.setNoth(noth);
            emotionSent.setShow(show);
            emotionSent.setBad(bad);
            emotionSent.setTurn(turn);
            emotionSent.setLongg(longg);
            emotionSent.setHome(home);
            emotionSent.setLight(light);
            emotionSent.setHand(hand);
            emotionSent.setWork(work);
            emotionSent.setNigparti(nigparti);
            emotionSent.setCaheart(caheart);
            emotionSent.setWalk(walk);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:4000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            emotionService = retrofit.create(EmotionService.class);
            Call<EmotionResponse> call = emotionService.sendPost(emotionSent);
            call.enqueue(new Callback<EmotionResponse>() {
                @Override
                public void onResponse(Call<EmotionResponse> call, Response<EmotionResponse> response) {
                    Log.i("response",response.body().getTitle());
                    Snackbar snackbar=Snackbar.make(findViewById(R.id.rootlayout),"Te recomendamos escuchar "+response.body().getTitle(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                @Override
                public void onFailure(Call<EmotionResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                }

            });



            count=0;
            kinki=0;
            love=0;
            success=0;
            hard=0;
            need=0;
            feel=0;
            day=0;
            neww=0;
            gone=0;
            heart=0;
            boy=0;
            realli=0;
            real=0;
            think=0;
            would=0;
            thing=0;
            one=0;
            hold=0;
            sdanger=0;
            bitch=0;
            leav=0;
            danger=0;
            around=0;
            talk=0;
            run=0;
            keep=0;
            nigga=0;
            live=0;
            sweet=0;
            world=0;
            alway=0;
            successt=0;
            eye=0;
            parti=0;
            wait=0;
            bodi=0;
            made=0;
            noth=0;
            show=0;
            bad=0;
            turn=0;
            longg=0;
            home=0;
            light=0;
            hand=0;
            work=0;
            nigparti=0;
            caheart=0;
            walk=0;
        }


    }
}

