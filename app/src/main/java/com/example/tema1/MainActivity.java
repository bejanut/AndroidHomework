package com.example.tema1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null){
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        //setare de listener pentru Navigation drawer
        NavigationView nav = findViewById(R.id.navigation_drawer);
        if(nav != null)
            nav.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.SnackbarText, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_information) {
            functionShowDailog();
            return true;
        }
        if (id == R.id.action_toast) {
            MakeToast(getString(R.string.AlertDialog_Message) , 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Functie care transforma un String intr-un mesaj Toast
    private void MakeToast(String message, int time) {
        if(time == 0)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    //Functie in care creem si afisam alert dialogul
    private void functionShowDailog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.AlertDialog_Title);
        alertDialog.setMessage(R.string.AlertDialog_Message);
        alertDialog.setPositiveButton(R.string.PositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MakeToast(getString(R.string.PossitiveMessage), 0);
            }
        });
        alertDialog.setNegativeButton(R.string.NegativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MakeToast(getString(R.string.NeggativeMessage), 0);
                alertDialog.show();
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawerul = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (item.getItemId()) {
            case R.id.descendant1: {
                Intent i = new Intent(this, FirstDescendantActivity.class);
                startActivity(i);
                drawerul.closeDrawer(GravityCompat.START);
                return true;
            }
            case R.id.descendant2: {
                Intent i = new Intent(this, SecondDescendantActivity.class);
                startActivity(i);
                drawerul.closeDrawer(GravityCompat.START);
                return true;
            }
            case R.id.nav_maps: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                Uri location = Uri.parse("geo:44.435065, 26.047774");
                i.setData(location);

                if(i.resolveActivity(getPackageManager()) != null)
                    startActivity(i);
                else
                    Log.e("Intent Problems", "Can't find a suitable aplication");
                drawerul.closeDrawer(GravityCompat.START);
                return true;
            }
        }
        return false;
    }

    public void Log_current_state(View view) {
        RadioGroup mRadio = findViewById(R.id.radioGroup);
        CheckBox First, Second;
        ToggleButton Tbutton = findViewById(R.id.toggleButton);
        String log_message = "";
        EditText Tv = findViewById(R.id.editTextTextPersonName);

        First = findViewById(R.id.checkBox);
        Second = findViewById(R.id.checkBox2);

        int id = mRadio.getCheckedRadioButtonId();

        switch (id) {
            case R.id.radioButton: {
                log_message = "First RB selected";
            break;
            }
            case R.id.radioButton2: {
                log_message = "Second RB selected";
                break;
            }
            case R.id.radioButton3: {
                log_message = "Third RB selected";
                break;
            }
            default: log_message = "No RB selected";
        }

        if(First.isChecked()) {
            log_message += ", First CB checked";
        } else {
            log_message += ", First CB not checked";
        }
        if(Second.isChecked()) {
            log_message += ", Second CB checked";
        } else {
            log_message += ", Second CB not checked";
        }

        if(Tbutton.isChecked()) {
            log_message += ", ToggleButton on";
        } else {
            log_message += ", ToggleButton off";
        }
        log_message += ", Text is \"" + Tv.getText().toString() + "\"";
        Log.v("MainActicityState", log_message);
    }
}