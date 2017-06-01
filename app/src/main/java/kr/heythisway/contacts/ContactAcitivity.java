package kr.heythisway.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kr.heythisway.contacts.domain.Data;

public class ContactAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_acitivity);

        for (Data data : getContacts()) {
            Log.i("Contacts", "이름 = " + data.getName() + " 전화번호 = " + data.getTel());
        }
    }

    public List<Data> getContacts() {
        // 데이터베이스 혹은 content resolver를 통해 가져온 데이터를 적재할
        // 데이터 저장소를 먼저 정의한다.
        List<Data> datas = new ArrayList<>();

        // 일종의 데이터베이스 관리툴
        // 전화번호부에 이미 만들어져 있는 Content Provider를 통해서
        // 데이터를 가져올 수 있다.
        ContentResolver resolver = getContentResolver();

        // 1. 어떤 데이터를 가져올 것인지 데이터 컨텐츠 URI(자원의 주소)를 정의한다.
        // 전화번호 URI (테이블) 값을 담는다.
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 2. URI는 하나의 데이터 테이블이므로 데이터에서 가져올 컬럼명을 정의
        String[] projections = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        // 3. Content Resolver로 쿼리를 날려서 데이터를 가져온다. 하나의 데이터 묶음이 리턴된다.
        // 이것을 Cursor 형(database형) 변수에 담을 수 있다. ArrayList와 비슷하다고 생각하면 된다.
        // resolver.query(테이블명, 컬럼명, 옵션, 옵션, 옵션)
        Cursor cursor = resolver.query(phoneUri, projections, null, null, null);

        // 4. 반복문을 이용해 cursor의 데이터를 하나씩 추출한다.
        if (cursor != null) {
            // 데이터베이스 컬럼에 데이터가 없으면 false를 리턴한다.
            while (cursor.moveToNext()) {
                // 4.1 위에 정의한 프로젝션의 컬럼명으로 cursor에 있는 인덱스값을 조회하고
                int idIndex = cursor.getColumnIndex(projections[0]);
                // 4.2 해당 index를 사용해서 실제값을 가져온다.
                int id = cursor.getInt(idIndex);

                int nameIndex = cursor.getColumnIndex(projections[1]);
                String name = cursor.getString(nameIndex);

                int telIndex = cursor.getColumnIndex(projections[2]);
                String tel = cursor.getString(telIndex);

                // 5. 내가 설계한 데이터를 담는다.
                Data data = new Data();
                data.setId(id);
                data.setName(name);
                data.setTel(tel);

                // 6. 여러개의 객체를 담을 수 있는 저장소에 담는다.
                datas.add(data);
            }
        }
        return datas;
    }
}
