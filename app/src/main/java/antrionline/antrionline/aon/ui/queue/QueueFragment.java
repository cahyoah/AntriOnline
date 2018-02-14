package antrionline.antrionline.aon.ui.queue;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import antrionline.antrionline.aon.R;
import antrionline.antrionline.aon.data.model.Hospital;
import antrionline.antrionline.aon.data.model.Queue;
import antrionline.antrionline.aon.data.model.User;
import antrionline.antrionline.aon.presenter.DetailQueuePresenter;
import antrionline.antrionline.aon.presenter.QueuePresenter;
import antrionline.antrionline.aon.ui.detailqueue.DetailQueueActivity;
import antrionline.antrionline.aon.ui.home.HomeActivity;
import antrionline.antrionline.aon.util.Constant;
import antrionline.antrionline.aon.util.ShowAlert;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueueFragment extends Fragment implements QueueView, View.OnClickListener {

    private TextView tvHospitalName, tvHospitalSpesialist, tvHospitalAddress, tvDate;
    private CheckBox cbUserMyData;
    private EditText etPatientName, etResponsiblePatientName, etPhoneNumber, etAddress;
    private Button btnQueue, btnChooseDate;
    private Spinner spTime;
    private RadioGroup rgAim;
    private Bundle bundle;
    private Hospital hospital;
    private QueuePresenter queuePresenter;
    private Calendar myCalendar;
    private  DatePickerDialog.OnDateSetListener date;
    private CoordinatorLayout clQueue;


    public QueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        tvHospitalName = view.findViewById(R.id.tv_hospital_name);
        tvHospitalSpesialist = view.findViewById(R.id.tv_hospital_spesialis);
        tvHospitalAddress = view.findViewById(R.id.tv_hospital_address);
        tvDate = view.findViewById(R.id.tv_date);
        cbUserMyData = view.findViewById(R.id.cb_use_my_data);
        etPatientName = view.findViewById(R.id.et_patient_name);
        etResponsiblePatientName = view.findViewById(R.id.et_responsible_patient_name);
        etPhoneNumber = view.findViewById(R.id.et_phone_number);
        etAddress = view.findViewById(R.id.et_address);
        spTime = view.findViewById(R.id.sp_time);
        rgAim = view.findViewById(R.id.rg_aim);
        btnChooseDate = view.findViewById(R.id.btn_choose_date);
        btnQueue = view.findViewById(R.id.btn_queue);
        clQueue = view.findViewById(R.id.cl_queue);
        initView();
        initPresenter();
        initCalendar();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStack();
            getFragmentManager().beginTransaction().remove(this).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initPresenter(){
        queuePresenter = new QueuePresenter(this);
    }

    public void initView(){
        getActivity().findViewById(R.id.navigation).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_logo).setVisibility(View.GONE);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Antri");
        ((HomeActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        setHasOptionsMenu(true);

        bundle = getArguments();
        hospital = bundle.getParcelable(Constant.HOSPITAL);
        tvHospitalName.setText(hospital.getHospitalName());
        tvHospitalSpesialist.setText(hospital.getFaskesType());
        tvHospitalAddress.setText(hospital.getFaskesAddress());
        btnQueue.setOnClickListener(this);
        btnChooseDate.setOnClickListener(this);
        cbUserMyData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    queuePresenter.showDataProfile();
                }else{
                    etPatientName.setText("");
                    etPhoneNumber.setText("");
                }
            }
        });

    }

    public void initCalendar(){

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                tvDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

    }

    @Override
    public void onFailureQueueInput(String message) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showSnackBar(clQueue, message);
    }

    @Override
    public void onSuccessQueueInput(Queue queue) {
        ShowAlert.closeProgresDialog();
        ShowAlert.showToast(getActivity(), "Pendaftaran Berhasil");
        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction().remove(this).commit();
        Intent intent = new Intent(getActivity(), DetailQueueActivity.class);
        intent.putExtra(Constant.QUEUE, queue);
        startActivity(intent);
    }

    @Override
    public void showDataProfile(User user) {
        etPatientName.setText(user.getName());
        etPhoneNumber.setText(user.getPhoneNumber());
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_queue){
            String patientName = etPatientName.getText().toString().trim();
            String responsiblePatientName = etResponsiblePatientName.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String date = tvDate.getText().toString().trim();
            String time = spTime.getSelectedItem().toString();
            RadioButton radioButton = getActivity().findViewById(rgAim.getCheckedRadioButtonId());
            String aim = radioButton.getText().toString();
            if(patientName.isEmpty()){
                etPatientName.setError(getResources().getString(R.string.text_cannot_empty));
                etPatientName.requestFocus();
            }else if(responsiblePatientName.isEmpty()){
                etResponsiblePatientName.setError(getResources().getString(R.string.text_cannot_empty));
                etResponsiblePatientName.requestFocus();
            }else if(phoneNumber.isEmpty()){
                etPhoneNumber.setError(getResources().getString(R.string.text_cannot_empty));
                etPhoneNumber.requestFocus();
            }else if(address.isEmpty()){
                etAddress.setError(getResources().getString(R.string.text_cannot_empty));
                etAddress.requestFocus();
            }else if(date.equals("Tanggal")){
                ShowAlert.showToast(getActivity(), "Anda belum memilih tanggal");
            }else{
                ShowAlert.showProgresDialog(getActivity());
                queuePresenter.queueInput(patientName, responsiblePatientName, address, phoneNumber, date, time, aim, hospital.getIdHospital());
            }
        }
        if(view.getId() == R.id.btn_choose_date){
            myCalendar = Calendar.getInstance();

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            myCalendar.add(Calendar.DAY_OF_YEAR,1);
            datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
            datePickerDialog.show();

        }
    }
}
