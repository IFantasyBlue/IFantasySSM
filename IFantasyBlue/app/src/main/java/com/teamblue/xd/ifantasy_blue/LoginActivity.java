package com.teamblue.xd.ifantasy_blue;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;


import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.User;
import entity.User_Info;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mPhoneView;
    private EditText mCheckPasswdView;
    private View mProgressView;
    private View mLoginFormView;
    private Context mContext;
    private int countSeconds = 300;//倒计时秒数

    private Button mPhoneCheckWdButton;
    private Intent intent;

    private Handler mCountHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(countSeconds > 0){
                --countSeconds;
                mPhoneCheckWdButton.setText("("+countSeconds+"s)后获取验证码");
                mCountHandler.sendEmptyMessageDelayed(0,1000);
            }
            else{
                countSeconds = 300;
                mPhoneCheckWdButton.setText(R.string.action_get_check_passwd);
            }
        }
    };

    private Handler progressHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if(msg.what == 0){
                showProgress(true);
            }else if(msg.what == 1){
                mCheckPasswdView.setText("");
                showProgress(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        progressHandler = new Handler();
        setContentView(R.layout.activity_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // Set up the login form.
        mPhoneCheckWdButton = (Button)findViewById(R.id.check_button);

        mPhoneView = (AutoCompleteTextView) findViewById(R.id.phone_number);
        populateAutoComplete();

        mCheckPasswdView = (EditText) findViewById(R.id.check_passwd);
        mCheckPasswdView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin(mPhoneView.getText().toString());
                    return true;
                }
                return false;
            }
        });

        Button mSignInButton = (Button) findViewById(R.id.login_button);


        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(mPhoneView.getText().toString());
            }
        });

        mPhoneCheckWdButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getCheckPasswd();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mPhoneView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * getCheckPasswd
     *
     */

    private void getCheckPasswd(){
        if(countSeconds == 300){
            String moblie =mPhoneView.getText().toString();
            Log.e("tag","mobile=="+moblie);
            getMobiile(moblie);
        }else{
            Toast.makeText(LoginActivity.this, "不能重复发送验证码", Toast.LENGTH_SHORT).show();
        }

    }
    private void getMobiile(String mobile) {
        if ("".equals(mobile)) {
            Log.e("tag", "mobile=" + mobile);
            new AlertDialog.Builder(mContext).setTitle("提示").setMessage("手机号码不能为空").setCancelable(true).show();
        } else if (isMobileNO(mobile) == false) {
            new AlertDialog.Builder(mContext).setTitle("提示").setMessage("请输入正确的手机号码").setCancelable(true).show();
        } else {
            Log.e("tag", "输入了正确的手机号");
            UserModel userinfo = new UserModel(null);
            SendSmsResponse ssr = new SendSmsResponse();
            try {
                ssr = userinfo.sendAKey(mobile);
                startCountBack();
            } catch (ClientException e) {
                e.printStackTrace();
                Log.e("tag", "发送失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isMobileNO(String tel) {
        Pattern p = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(tel);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    private void startCountBack() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPhoneCheckWdButton.setText(countSeconds + "s");
                mCountHandler.sendEmptyMessage(0);
            }
        });
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin(final String mobile) {

        new Thread(){
            public void run(){
                Message msg = new Message();
                msg.what = 0;
                progressHandler.sendMessage(msg);
                UserModel userHttp = new UserModel(null);
                int akey = 0;
                try{
                    try {


                        akey = Integer.parseInt(mCheckPasswdView.getText().toString());
                    }catch (NumberFormatException e){

                    }
                    JSONObject o = userHttp.userlogin(mobile,akey);
                    int status =o.getInt("status");
                    boolean current = o.getBoolean("akey");
                    Log.i("tag","status:"+ status);
                    Log.i("tag","akey:"+current);
                    if(current)
                    {
                        if(status==0){
                            Log.e("tag","找不到该id");
                            intent = new Intent(LoginActivity.this,SignActivity.class);
                            intent.putExtra("id",mobile);
                            startActivity(intent);
                        }else{
                            Log.e("tag","登录成功");
                            JSONObject e = o.getJSONObject("user");
                            JSONObject i = o.getJSONObject("userinfo");
                            String token = o.getJSONObject("token").getString("id");
                            SharedPreferences sp = getSharedPreferences("sp_token", Context.MODE_PRIVATE);
                            sp.edit().putString("token",token).commit();
                            intent = new Intent(LoginActivity.this,MainActivity.class);
                            User user = new User();
                            user.setId(e.getInt("id"));
                            user.setUserinfo(e.getInt("userinfo"));
                            user.setName(e.getString("name"));
                            user.setPhone(e.getString("phone"));
                            user.setSetting(e.getInt("setting"));
                            user.setPower(e.getInt("power"));
                            user.setMoney(e.getInt("money"));
                            user.setFriendsNum(e.getInt("friendsNum"));
                            user.setPackageNum(e.getInt("packageNum"));
                            user.setoTactics(e.getInt("oTactics"));
                            user.setdTactics(e.getInt("dTactics"));
                            intent.putExtra("user",user);

                            User_Info userinfo = new User_Info();
                            userinfo.setUserId(i.getInt("userId"));
                            userinfo.setLevel(i.getInt("level"));
                            userinfo.setVip(i.getInt("vip"));
                            userinfo.setBelongteam(i.getInt("belongteam"));
                            intent.putExtra("userinfo",userinfo);

                            startActivity(intent);
                            finish();
                        }

                    }
                    else
                    {
                        Log.e("tag","验证失败，验证码错误！");
                        Toast.makeText(LoginActivity.this, "验证失败，验证码错误！", Toast.LENGTH_SHORT).show();
                        msg.what = 1;
                        progressHandler.sendMessage(msg);
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }




        }.start();
    }



    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mPhoneView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


}

