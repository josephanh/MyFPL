package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_WEEK;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ScheduleAdapter;
import nta.com.music.myfpl.dialog.DialogSchedule;
import nta.com.music.myfpl.interfaces.OnChangeScheduleWeek;
import nta.com.music.myfpl.model.Schedule;

public class ScheduleFragment extends Fragment implements OnChangeScheduleWeek {

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    LinearLayout layout_id;
    int dayNumber;
    List<Schedule> listSchedule = new ArrayList<>();
    List<Schedule> listScheduleTemps = new ArrayList<>();
    List<String> listCalendar = new ArrayList<>();
    ScheduleAdapter adapter;
    RecyclerView recyclerSchedule;
    LinearLayout no_task;
    int state, type;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            listSchedule = (List<Schedule>) msg.obj;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null) {
                        changeList(dayNumber);
                        adapter.updateData(listScheduleTemps);
                        if (listScheduleTemps.size() != 0) {
                            no_task.setVisibility(View.GONE);
                            recyclerSchedule.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
    };


//    state là trạng thái lịch học hay lịch thi
//    dayNumber là ngày trong tuần 0: thứ 2, 1: thứ 3, 2: thứ 4,...
//    listScheduleTemps là danh sách lịch học
//    types là loại lịch tuần hay lịch tháng

    public static ScheduleFragment newInstance(int dayNumber, int state) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt("dayNumber", dayNumber);
        args.putInt("state", state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dayNumber = getArguments().getInt("dayNumber");
            state = getArguments().getInt("state");
//            Log.d(">>> CLASS API", "onFailure: " + dayNumber);
        }
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);


        layout_id = view.findViewById(R.id.layout_id);
        no_task = view.findViewById(R.id.no_task);

//        Log.d(">>>>TAG", "onCreateView: "+dayNumber + " STATE: "+state);

        recyclerSchedule = view.findViewById(R.id.listSchedule);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSchedule.setLayoutManager(linearLayoutManager);
        adapter = new ScheduleAdapter(requireContext(), listScheduleTemps, CALENDAR_WEEK, schedule -> {
            DialogSchedule dialogSchedule = new DialogSchedule(requireContext(), schedule);
            dialogSchedule.Show();

        });
        recyclerSchedule.setAdapter(adapter);
        if (listScheduleTemps.size() == 0) {
            no_task.setVisibility(View.VISIBLE);
            recyclerSchedule.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listScheduleTemps.size() == 0 && adapter == null) {
            no_task.setVisibility(View.VISIBLE);
            recyclerSchedule.setVisibility(View.GONE);
        }
    }

    @Override
    @SuppressLint("NotifyDataSetChanged")
    public void onChangeSchedule(List<Schedule> list) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = list;
                handler.handleMessage(message);
            }
        });
    }

    private void setListSchedule(int dayNumber) {

    }

    private void changeList(int dayNumber) {

      try{
          if (listCalendar.size() == 0) {
              Date currentDate = new Date();

              Calendar calendar = Calendar.getInstance();
              calendar.setTime(currentDate);
              calendar.add(Calendar.DAY_OF_MONTH, 0);
              int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
              int diff = calendar.getFirstDayOfWeek() - (dayOfWeek-1);
              if (diff > 0) {
                  diff -= 7;
              }

              calendar.add(Calendar.DAY_OF_MONTH, diff);
              listCalendar.clear();
              for (int i = 0; i < 7; i++) {
                  Date date = calendar.getTime();
                  String[] arr = date.toString().split(" ");
                  listCalendar.add(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[5]);
                  calendar.add(Calendar.DAY_OF_MONTH, 1);
              }
          }
          listScheduleTemps.clear();
          for (int i = 0; i < listSchedule.size(); i++) {
              if(dayNumber == 1){
//                Log.d(">>> CLASS API", "onFailure: " + listSchedule.get(i).getDay());
              }

              if (state == 0) {
                  if (listSchedule.get(i).getDay().equals(convertDateFormat(listCalendar.get(dayNumber)))) {
                      if (listCalendar.get(dayNumber).contains(convertDate(dayNumber)) && Integer.parseInt(listSchedule.get(i).getType()) == 0) {
                          listScheduleTemps.add(listSchedule.get(i));
                          listSchedule.remove(i);
                      }
                  }

              } else{
                  if (listSchedule.get(i).getDay().equals(convertDateFormat(listCalendar.get(dayNumber)))) {
                      if (listCalendar.get(dayNumber).contains(convertDate(dayNumber)) && Integer.parseInt(listSchedule.get(i).getType()) == 1) {
                          listScheduleTemps.add(listSchedule.get(i));
                          listSchedule.remove(i);
                      }
                  }
              }
          }
//        Log.d(">>> CLASS API", dayNumber + " Ngày: "+ state);
          new Handler(Looper.getMainLooper()).post(() -> {
              if (listScheduleTemps.size() == 0) {
                  no_task.setVisibility(View.VISIBLE);
                  recyclerSchedule.setVisibility(View.GONE);
              }
          });
      } catch (Exception e){
          Log.d(">>>>TAG", "bay áp: "+e);
      }
    }


    private String convertDate(int dayNumber) {
        String date = null;
        switch (dayNumber) {
            case 0:
                return "Mon";
            case 1:
                return "Tue";
            case 2:
                return "Wed";
            case 3:
                return "Thu";
            case 4:
                return "Fri";
            case 5:
                return "Sat";
            case 6:
                return "Sun";
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    private String convertDateFormat(String inputDate) {
        String outputFormat = "yyyy-MM-dd";
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
            SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);

            // Chuyển chuỗi thành đối tượng Date
            Date date = inputFormatter.parse(inputDate);

            // Chuyển đổi tháng từ dạng chữ (Jul) sang số (7)
            SimpleDateFormat monthFormatter = new SimpleDateFormat("MM");
            assert date != null;
            String monthNumber = monthFormatter.format(date);

            String outputDate = outputFormatter.format(date);

            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }


}