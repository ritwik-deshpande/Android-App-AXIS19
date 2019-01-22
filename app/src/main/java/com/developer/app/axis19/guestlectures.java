package com.developer.app.axis19;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.List;

public class guestlectures extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterGL recyclerViewAdapter;

    List<Event> lst = new ArrayList<>() ;
    public guestlectures() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.guestlectures_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.gl_recyclerview);


        // runAnimation(recyclerView,0);

        lst.add(new Event("Japp Thearson,Father of Bluetooth","Guru","Chaitya","Apti","https://www.google.com/imgres?imgurl=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fquality%3D85%26image_uri%3Dhttps%253A%252F%252Fs.aolcdn.com%252Fdims-shared%252Fdims3%252FGLOB%252Fcrop%252F1600x1066%252B0%252B0%252Fresize%252F1600x1066%2521%252Fformat%252Fjpg%252Fquality%252F85%252Fhttps%253A%252F%252Fs.aolcdn.com%252Fhss%252Fstorage%252Fmidas%252Fec645ccc28773737e5f452f3c5b114ec%252F204687718%252FJaap%252BHaartsen%252BEPO%252Bresized.jpg%26client%3Damp-blogside-v2%26signature%3D5ab5c14ba030421900680f041c11382e84740bf1&imgrefurl=https%3A%2F%2Fwww.engadget.com%2F2016%2F12%2F09%2Fbluetooth-inventor-jaap-haartsen-interview%2F&docid=IUKBn0hB4ksL1M&tbnid=3GkgI4WxQA849M%3A&vet=10ahUKEwjAkrKp1IHgAhXTZCsKHWFcB74QMwhAKAEwAQ..i&w=1600&h=1066&bih=608&biw=1366&q=jaap%20haartsen&ved=0ahUKEwjAkrKp1IHgAhXTZCsKHWFcB74QMwhAKAEwAQ&iact=mrc&uact=8",9868696,896869868,"Chem","10/2/2019"));
        lst.add(new Event("Japp Thearson,Father of Bluetooth","Guru","Chaitya","Apti","https://www.google.com/imgres?imgurl=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fquality%3D85%26image_uri%3Dhttps%253A%252F%252Fs.aolcdn.com%252Fdims-shared%252Fdims3%252FGLOB%252Fcrop%252F1600x1066%252B0%252B0%252Fresize%252F1600x1066%2521%252Fformat%252Fjpg%252Fquality%252F85%252Fhttps%253A%252F%252Fs.aolcdn.com%252Fhss%252Fstorage%252Fmidas%252Fec645ccc28773737e5f452f3c5b114ec%252F204687718%252FJaap%252BHaartsen%252BEPO%252Bresized.jpg%26client%3Damp-blogside-v2%26signature%3D5ab5c14ba030421900680f041c11382e84740bf1&imgrefurl=https%3A%2F%2Fwww.engadget.com%2F2016%2F12%2F09%2Fbluetooth-inventor-jaap-haartsen-interview%2F&docid=IUKBn0hB4ksL1M&tbnid=3GkgI4WxQA849M%3A&vet=10ahUKEwjAkrKp1IHgAhXTZCsKHWFcB74QMwhAKAEwAQ..i&w=1600&h=1066&bih=608&biw=1366&q=jaap%20haartsen&ved=0ahUKEwjAkrKp1IHgAhXTZCsKHWFcB74QMwhAKAEwAQ&iact=mrc&uact=8",9868696,896869868,"Chem","10/2/2019"));
        lst.add(new Event("Japp Thearson,Father of Bluetooth","Guru","Chaitya","Apti","https://www.google.com/imgres?imgurl=https%3A%2F%2Fo.aolcdn.com%2Fimages%2Fdims%3Fquality%3D85%26image_uri%3Dhttps%253A%252F%252Fs.aolcdn.com%252Fdims-shared%252Fdims3%252FGLOB%252Fcrop%252F1600x1066%252B0%252B0%252Fresize%252F1600x1066%2521%252Fformat%252Fjpg%252Fquality%252F85%252Fhttps%253A%252F%252Fs.aolcdn.com%252Fhss%252Fstorage%252Fmidas%252Fec645ccc28773737e5f452f3c5b114ec%252F204687718%252FJaap%252BHaartsen%252BEPO%252Bresized.jpg%26client%3Damp-blogside-v2%26signature%3D5ab5c14ba030421900680f041c11382e84740bf1&imgrefurl=https%3A%2F%2Fwww.engadget.com%2F2016%2F12%2F09%2Fbluetooth-inventor-jaap-haartsen-interview%2F&docid=IUKBn0hB4ksL1M&tbnid=3GkgI4WxQA849M%3A&vet=10ahUKEwjAkrKp1IHgAhXTZCsKHWFcB74QMwhAKAEwAQ..i&w=1600&h=1066&bih=608&biw=1366&q=jaap%20haartsen&ved=0ahUKEwjAkrKp1IHgAhXTZCsKHWFcB74QMwhAKAEwAQ&iact=mrc&uact=8",9868696,896869868,"Chem","10/2/2019"));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewAdapter=new RecyclerViewAdapterGL(getContext(),lst);

        recyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }

    private void runAnimation(RecyclerView recyclerView, int type) {

        Context context = recyclerView.getContext();
        LayoutAnimationController controller=null;

        // 0 denotes fall_down animation
        if(type==0){
            controller= AnimationUtils.loadLayoutAnimation(context,R.anim.item_falldown_animation);

        }

        recyclerView.setLayoutAnimation(controller);
        //recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }
    public void updateUI()
    {

        // recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

}
