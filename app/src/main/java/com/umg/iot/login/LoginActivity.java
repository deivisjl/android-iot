package com.umg.iot.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.umg.iot.R;
import com.umg.iot.lib.MySharedPreference;
import com.umg.iot.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.til_Email)
    TextInputLayout tilEmail;
    @BindView(R.id.wrapperUsername)
    LinearLayout wrapperUsername;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @BindView(R.id.til_Password)
    TextInputLayout tilPassword;
    @BindView(R.id.wrapperPassword)
    LinearLayout wrapperPassword;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.layoutButtons)
    LinearLayout layoutButtons;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.loginContent)
    RelativeLayout container;

    private LoginPresenter loginPresenter;
    private MySharedPreference sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        sharedPreferences = MySharedPreference.getInstance(getApplicationContext());
        loginPresenter.onCreate();
        loginPresenter.checkForSession();
    }

    @OnClick(R.id.btnSignin)
    public void handleSignIn(){
        if (isOnline(this)) {

            loginPresenter.validateLogin(
                    editTxtEmail.getText().toString().trim(),
                    editTxtPassword.getText().toString().trim());
        } else {

            String message = "No hay conexión a internet";
            Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void enabledInputs() {
        setInputs(true);
    }

    private void setInputs(boolean b) {
        editTxtEmail.setEnabled(b);
        editTxtPassword.setEnabled(b);
        btnSignin.setEnabled(b);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void setCurrentUser(String email) {
        if (email != null) {
            sharedPreferences.saveData(email);
        }
    }

    @Override
    public void loginError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format("Imposible iniciar sesión", error);
        editTxtPassword.setError(msgError);
    }

    @Override
    public void navigateToMainScreen() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }
}
