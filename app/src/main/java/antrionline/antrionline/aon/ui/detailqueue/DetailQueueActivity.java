package antrionline.antrionline.aon.ui.detailqueue;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.presenter.DetailQueuePresenter;
import antrionline.antrionline.aon.util.Constant;
import antrionline.antrionline.aon.util.ShowAlert;

public class DetailQueueActivity extends AppCompatActivity implements DetailQueueView, View.OnClickListener {

    private TextView tvHospitalName, tvHospitalSpesialist, tvHospitalAddress, tvDate, tvTime;
    private TextView tvPatientName, tvResponsiblePatientName, tvAddress, tvPhoneNumber, tvAim, tvQueueNumber;
    private ImageView imgCall;
    private DetailQueuePresenter detailQueuePresenter;
    private Toolbar toolbar;
    private Queue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_queue);
        queue = getIntent().getParcelableExtra(Constant.QUEUE);

        initView();
        initPresenter();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Detail Antrian");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvHospitalName= findViewById(R.id.tv_hospital_name);
        tvHospitalAddress = findViewById(R.id.tv_hospital_address);
        tvHospitalSpesialist = findViewById(R.id.tv_hospital_spesialis);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        tvPatientName = findViewById(R.id.tv_patient_name);
        tvResponsiblePatientName = findViewById(R.id.tv_responsible_patient_name);
        tvAddress = findViewById(R.id.tv_address);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        tvAim = findViewById(R.id.tv_aim);
        tvQueueNumber = findViewById(R.id.tv_queue_number);
        imgCall = findViewById(R.id.img_call);
        imgCall.setOnClickListener(this);
    }

    public void initPresenter(){
        detailQueuePresenter = new DetailQueuePresenter(this);
        detailQueuePresenter.showQueue(queue);
    }

    @Override
    public void onSuccessShowQueue(Queue queue) {
        tvHospitalName.setText(queue.getHospitalName());
        tvHospitalSpesialist.setText(queue.getFaskesType());
        tvHospitalAddress.setText(queue.getFaskesAddress());
        tvDate.setText(queue.getDate());
        tvTime.setText(queue.getTime());
        tvPatientName.setText(queue.getPatientName());
        tvResponsiblePatientName.setText(queue.getResponsiblePersonName());
        tvAddress.setText(queue.getAddress());
        tvPhoneNumber.setText(queue.getPhoneNumber());
        tvAim.setText(queue.getAim());
        tvQueueNumber.setText(Integer.toString(queue.getQueueNumber()));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.img_call){
            int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);
            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Hubungi Dokter");
                alertDialog.setMessage("Apakah anda yakin ingin menghubungi "+ queue.getHospitalName() +" ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya",
                        new DialogInterface.OnClickListener() {
                            @SuppressLint("MissingPermission")
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + queue.getHospitalPhoneNumber()));
                                startActivity(intent);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak",(dialogInterface, i) -> alertDialog.dismiss());
                alertDialog.show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ShowAlert.showToast(this,"Tidak Diijinkan");
                }
                return;
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
