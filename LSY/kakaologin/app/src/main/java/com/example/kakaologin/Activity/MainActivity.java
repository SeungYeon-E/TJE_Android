package com.example.kakaologin.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;
import com.example.kakaologin.common.CommonInfo;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends Activity {
//    private ImageButton loginV1;
    private Button logout;
    private SessionCallback sessionCallback;
    Session session;
    private static final String TAG = "MainActivity";
    LoginButton loginV1;

    String urlAddr = null;
    public static String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlAddr = "http://" + CommonInfo.hostIP + ":8080/phonebook/userInsertReturn.jsp?";

        loginV1 = findViewById(R.id.loginV1);
        logout = findViewById(R.id.logout);
//        session = Session.getCurrentSession();
//        session.addCallback(sessionCallback);

        //카카오 로그인 콜백 초기화
        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        //앱 실행 시 로그인 토큰이 있으면 자동으로 로그인 수행
        Session.getCurrentSession().checkAndImplicitOpen();

        loginV1.setOnClickListener(v -> {
            if (Session.getCurrentSession().checkAndImplicitOpen()) {
                Log.d(TAG, "onClick: 로그인 세션살아있음");
                // 카카오 로그인 시도 (창이 안뜬다.)
                sessionCallback.requestMe();
            } else {
                Log.d(TAG, "onClick: 로그인 세션끝남");
                // 카카오 로그인 시도 (창이 뜬다.)
                session.open(AuthType.KAKAO_LOGIN_ALL, MainActivity.this);
            }
        });

        // 카카오 개발자 홈페이지에 등록할 해시키 구하기
//        getHashKey();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (session.equals("null") != true){
//            goList();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    //카카오로그인 구현
    public class SessionCallback implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        // 로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
        }




        // 사용자 정보 요청
        public void requestMe() {
            UserManagement.getInstance()
                    .me(new MeV2ResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                        }

                        @Override
                        public void onFailure(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                        }

                        @Override
                        public void onSuccess(MeV2Response result) {
                            Log.i("KAKAO_API", "사용자 아이디: " + result.getId());
                            userid = String.valueOf(result.getId());
                            UserAccount kakaoAccount = result.getKakaoAccount();
                            if (kakaoAccount != null) {

                                // 이메일
                                String email = kakaoAccount.getEmail();
                                Profile profile = kakaoAccount.getProfile();
                                if (profile == null) {
                                    Log.d("KAKAO_API", "onSuccess:profile null ");
                                } else {
                                    Log.d("KAKAO_API", "onSuccess:getProfileImageUrl " + profile.getProfileImageUrl());
                                    Log.d("KAKAO_API", "onSuccess:getNickname " + profile.getNickname());
                                    String imageUrl = String.valueOf(profile.getProfileImageUrl());
                                    String nickname = String.valueOf(profile.getNickname());
                                }
                                if (email != null) {

                                    Log.d("KAKAO_API", "onSuccess:email " + email);
                                } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 이메일 획득 가능
                                    // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.
                                    Log.d("KAKAO_API", "onSuccess: 동의 요청 후 이메일 획득 가능");
                                } else {
                                    // 이메일 획득 불가
                                    Log.d("KAKAO_API", "onSuccess: cannot get email");
                                }

                                // 프로필
                                Profile _profile = kakaoAccount.getProfile();

                                if (_profile != null) {

                                    Log.d("KAKAO_API", "nickname: " + _profile.getNickname());
                                    Log.d("KAKAO_API", "profile image: " + _profile.getProfileImageUrl());
                                    Log.d("KAKAO_API", "thumbnail image: " + _profile.getThumbnailImageUrl());

                                } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 프로필 정보 획득 가능

                                } else {
                                    // 프로필 획득 불가
                                }
                            } else {
                                Log.i("KAKAO_API", "onSuccess: kakaoAccount null");

                            }
                            urlAddr = urlAddr + "id=" + userid;
                            Log.v("message", "urlAddr="+urlAddr);
                            String result1 = connectInsertData();
                            //첫가입로그인때만 실행된다!! 대존잼
                            Log.v("message", "result="+result1);
                            if(result1.equals("1")){
                                Toast.makeText(MainActivity.this, "가입 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                            String id = String.valueOf(result.getId());
                            Intent intent = new Intent(MainActivity.this, SelectAllActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    });

        }
    }
    private String connectInsertData(){
        String result = null;
        try {
            NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr, "insert");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
        //잘끝났으면 1 아니면 에러
    }
}