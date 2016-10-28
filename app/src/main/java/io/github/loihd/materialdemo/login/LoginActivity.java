package io.github.loihd.materialdemo.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.loihd.materialdemo.R;
import io.github.loihd.materialdemo.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.edt_username) EditText edtUsername;
  @BindView(R.id.input_layout_username) TextInputLayout inputLayoutUsername;
  @BindView(R.id.edt_password) EditText edtPassword;
  @BindView(R.id.input_layout_password) TextInputLayout inputLayoutPassword;
  @BindView(R.id.btn_login) AppCompatButton btnLogin;

  @BindInt(R.integer.username_min_length) int minUsername;
  @BindInt(R.integer.password_min_length) int minPassword;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_login) void onLoginClicked() {
    startActivity(MainActivity.getIntent(this));
  }

  @OnTextChanged(R.id.edt_username) void onUsernameChanged(CharSequence text) {
    if (text.length() > 0 && text.length() < minUsername) {
      inputLayoutUsername.setError("Username is incorrect!");
      inputLayoutUsername.setErrorEnabled(true);
    } else {
      inputLayoutUsername.setErrorEnabled(false);
    }
  }

  @OnTextChanged(R.id.edt_password) void onPasswordChanged(CharSequence text) {
    if (text.length() > 0 && text.length() < minPassword) {
      inputLayoutPassword.setError("Password is incorrect!");
      inputLayoutPassword.setErrorEnabled(true);
    } else {
      inputLayoutPassword.setErrorEnabled(false);
    }
  }
}
