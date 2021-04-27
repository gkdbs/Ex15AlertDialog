package com.milkywaylhy.ex15alertdialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] items = new String[]{"Apple", "Banana", "Orange"};
    boolean[] checked = new boolean[]{true, true, false};

    int checkedItemIndex = 0;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //AlertDialog 를 만들어주는 건축가(Builder)객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //건축가에게 원하는 AlertDialog 의 모양을 의뢰
        builder.setTitle("다이얼로그");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //1. 단순메세지
        //builder.setMessage("Do you wanna Quit?");

        //2.단순한 메세지 대신 항목 리스트를 보여주기
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
//            }
//        });

        //3. 항목 글씨 옆에 radiobutton 모양 보이도록
//        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                checkedItemIndex= which;
//            }
//        });

        //4. 항목 글씨 옆에 checkbox 모양 보이도록
//        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                checked[which]= isChecked;
//            }
//        });

        //5. Custom View 로 메세지 영역을 바꾸기
        // xml로 뷰 모양을 설계하고 이를 읽어서
        //자바 View객체로 만들어 설정!

        //xml문서를 읽어서 뷰 객체를 만들어주는(부풀리는)
        //객체를 Context 로 부터 소환하기..
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog, null);

        et= layout.findViewById(R.id.et);
        tv= layout.findViewById(R.id.tv);

        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[checkedItemIndex], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        //건축가에게 위 요구사항 대로 AlertDialog 객체를
        //만들어 달라고 요청!!
        dialog = builder.create();
        //dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //참조변수 - 멤버변수
    TextView tv;
    EditText et;

    //다이얼로그 안 Custom View의 버튼을 클릭했을 때
    public void clickDialogBtn(View v){
        //MainActivity 가 보여주고 있는 화면 activity_main.xml 에
        //EditText, TextView 가 있는 것이 아니어서
        //찾을 수(findViewById) 없음.
//        EditText et= this.findViewById(R.id.et);
//        TextView tv= this.findViewById(R.id.tv);
//        tv.setText(  et.getText().toString() );

        //이를 찾으려면..EditText, TextView 를
        //실제 가지고 있는 View 에게 찾아 달라고 해야함
        //저 위에 builder 에게 setView()할 때
        //설정했던 layout 객체 에게 찾아달라고...
        // 뷰를 만들때 참조해 놓아야 함.!!

        tv.setText( et.getText().toString() );

        dialog.dismiss();//다이얼로그 사라지기
    }


    //뒤로가기 버튼을 클릭했을때 자동으로 실행되는 메소드
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("진짜 나갈겁니까?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();//액티비티를 종료하는 명령 메소드!!
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog= builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
