package pe.edu.musicrecommender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pe.edu.musicrecommender.Model.Users;
import pe.edu.musicrecommender.Prevalent.Prevalent;

public class LoginActivity extends AppCompatActivity {

    private EditText InputEmail, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        InputEmail = (EditText) findViewById(R.id.login_email_input);
        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });
    }
    private void LoginUser()
    {
        String email = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Por favor, escriba su usuario...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Por favor, escriba su contraseña...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Iniciando sesión");
            loadingBar.setMessage("Por favor espere, estamos validando las credenciales");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(email, password);
        }
    }

    private void AllowAccessToAccount(final String email, final String password)
    {


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Users").child(email).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(email).getValue(Users.class);

                    if (usersData.getEmail().equals(email))
                    {
                        if (usersData.getPassword().equals(password))
                        {



                            Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);




                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                        }

                    }


                }
                else
                {
                    Toast.makeText(LoginActivity.this, "No existe una cuenta con nombre de usuario.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }


}
