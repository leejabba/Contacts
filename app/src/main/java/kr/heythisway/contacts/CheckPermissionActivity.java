package kr.heythisway.contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 권한 처리를 하는 액티비티
 */
public class CheckPermissionActivity extends AppCompatActivity {

    private final int REQ_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_permission);

        // API 레벨이 23 이상일 경우만 실행
        // 설치 안드로이드폰의 API 레벨 가져오기 -> Build.VERSION.SDK_INT
        // Build.VERSION_CODES 아래에 상수로 각 버전별 API 레벨이 작성되어 있다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            // 아니면 그냥 run();
            run();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)   // 빌드버전이 머시멜로우 이상일때 실행하라고 알려줌
    private void checkPermission() {
        // 1. 권한체크 - 특정 권한이 있는지 시스템에 물어본다.
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            run();
        } else {
            // 2. 권한이 없으면 사용자에게 권한을 달라고 요청
            String[] permissions = {Manifest.permission.READ_CONTACTS};
            requestPermissions(permissions, REQ_PERMISSION);   // -> 권한을 요구하는 팝업이 사용자 화면에 뜬다.

        }
    }

    // 3. 사용자가 권한체크 팝업에서 권한을 승인, 또는 거절하면 onRequestPermissionsResult 메서드가 호출된다.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION) {
            // 3.1 사용자가 승인을 했으면
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) ;
            run();
            // 3.2 승인을 거절했으면
        } else {
            cancel();
        }
    }

    public void run() {
        Intent intent = new Intent(this, ContactAcitivity.class);
        startActivity(intent);
    }

    public void cancel() {
        Toast.makeText(this, "권한요청에 승인하셔야만 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
    }
}
