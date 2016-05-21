package com.buses;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.buses.adapters.AdapterGeneric;
import com.buses.adapters.Item;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvPathology;
    private List<Item> pathologyItems = new ArrayList<>();
    private EventBus eventBus;
    private List<Pathology> listPathologies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        eventBus = EventBus.getDefault();
        eventBus.register(this);


        /*Si la lista viene cargada en el bundle no llamo a la api*/
        if (savedInstanceState != null && savedInstanceState.getBoolean("already")) {
          listPathologies=  savedInstanceState.getParcelableArrayList("list");
            for (Pathology pathology : listPathologies) {
                PathologyItem item = new PathologyItem();
                item.setData(pathology);
                pathologyItems.add(item);
            }
            listView.setAdapter(new AdapterGeneric(MainActivity.this,pathologyItems));

        } else {
            getData();
        }


    }


    @Subscribe
    public void onEvent(OnRequestCallback call) {

        listPathologies = call.getList();

        for (Pathology pathology : call.getList()) {
            PathologyItem item = new PathologyItem();
            item.setData(pathology);
            pathologyItems.add(item);
        }

        listView.setAdapter(new AdapterGeneric(MainActivity.this,pathologyItems));

    }


    private void getData() {

        /*Metodo utilizando RX */
        Observable<List<Pathology>> observable = ApiClient.getServiceClient().getPathologiesRx();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Pathology>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("asd", "completado");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("asd", "error");
                    }

                    @Override
                    public void onNext(List<Pathology> pathologies) {
                        listPathologies = pathologies;
                        for (Pathology pathology : pathologies) {
                            PathologyItem item = new PathologyItem();
                            item.setData(pathology);
                            pathologyItems.add(item);
                        }
                        listView.setAdapter(new AdapterGeneric(MainActivity.this,pathologyItems));

                    }
                });


        /*****************************************************************************************************/

        /*Forma sin RX y haciendo el post event para llenar la lista*/

       /* final Call<List<Pathology>> pathologies = ApiClient.getServiceClient().getPathologies();
        pathologies.enqueue(new Callback<List<Pathology>>() {
            @Override
            public void onResponse(Call<List<Pathology>> call, Response<List<Pathology>> response) {
                eventBus.post(new OnRequestCallback(response.body()));
                listPathologies = response.body();
            }

            @Override
            public void onFailure(Call<List<Pathology>> call, Throwable t) {
                Log.e("ups", "uhh");
            }
        });*/

    }


    public void onSaveInstanceState(Bundle out) {
        out.putBoolean("already", true);
        out.putParcelableArrayList("list", (ArrayList<Pathology>) listPathologies);

        super.onSaveInstanceState(out);
    }


    private void initViews() {
        listView = (ListView) findViewById(R.id.listView);
        tvPathology = (TextView) findViewById(R.id.tv_pathology);


    }
}
